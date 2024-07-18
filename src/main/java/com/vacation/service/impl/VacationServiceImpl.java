package com.vacation.service.impl;

import com.vacation.dto.VacationRequestDTO;
import com.vacation.entity.Employee;
import com.vacation.entity.STATUSOPTION;
import com.vacation.entity.VacationRequest;
import com.vacation.exception.ResourceNotFoundException;
import com.vacation.repo.EmployeeRepository;
import com.vacation.repo.VacationRequestRepository;
import com.vacation.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.management.ManagementPermission;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VacationServiceImpl implements VacationService {

    private final VacationRequestRepository vacationRequestRepository;
    private final EmployeeRepository employeeRepository;

    public VacationServiceImpl(VacationRequestRepository vacationRequestRepository, EmployeeRepository employeeRepository) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public VacationRequestDTO createVacationRequest(Long employeeId, VacationRequestDTO requestDTO) {
       Employee author= employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Employee Not Found Exception"));
        VacationRequest vacationRequest=null;
        if (author != null && author.getRemainingVacationDays() >= requestDTO.getVacationEndDate().toEpochDay() - requestDTO.getVacationStartDate().toEpochDay() + 1) {
            VacationRequest  request = toEntity(requestDTO, author);
            vacationRequest = vacationRequestRepository.save(request);
        }
        return  toDto(vacationRequest);
    }

    @Override
    public VacationRequest processRequest(Long requestId, String status, Long managerId) {
        VacationRequest request = vacationRequestRepository.findById(requestId).orElseThrow(() ->
                new ResourceNotFoundException("Vacation Not Found Exception"));
        if(!status.isBlank() ) {
            STATUSOPTION newStatus = STATUSOPTION.valueOf(status.toUpperCase());
            request.setStatus(STATUSOPTION.valueOf(status.toUpperCase()));
            request.setResolvedBy(managerId);
            if (STATUSOPTION.APPROVED.equals(request.getStatus())) {
                Employee author = request.getAuthor();
                int daysRequested = (int) (request.getVacationEndDate().toEpochDay() - request.getVacationStartDate().toEpochDay() + 1);
                author.setRemainingVacationDays(author.getRemainingVacationDays() - daysRequested);
                employeeRepository.save(author);
            }
           else  if (STATUSOPTION.PENDING.equals(request.getStatus()) || STATUSOPTION.REJECTED.equals(request.getStatus())) {
                Employee author = request.getAuthor();
                author.setRemainingVacationDays(author.getRemainingVacationDays());
                employeeRepository.save(author);
            }
            return  vacationRequestRepository.save(request);
        }
        return request;
    }

    public Map<Employee, List<VacationRequestDTO>> getRequestsByAuthorAndStatus(Long employeeId, String status) {
        List<VacationRequest> requests = vacationRequestRepository.findByEmployeeId(employeeId);

        if (requests.isEmpty()) {
            throw new ResourceNotFoundException("Vacation Requests not found for employeeId: " + employeeId);
        }
        // Filter by status if provided
        if (status != null && !status.isBlank()) {
            requests = requests.stream()
                    .filter(v -> v.getStatus().toString().equalsIgnoreCase(status))
                    .toList();}
        Map<Employee, List<VacationRequestDTO>> map = new HashMap<>();
        for (VacationRequest request : requests) {
            Employee author = request.getAuthor();
            if (!map.containsKey(author)) {
                List<VacationRequestDTO> dtoList = requests.stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
                map.put(author, dtoList);
            }
        }return map;
    }


   @Override
    public List<VacationRequest> getRequestsByStatus(String status) {
       List<VacationRequest> allRequests = vacationRequestRepository.findAll();
       return status != null && !status.isBlank()
               ? allRequests.stream()
               .filter(v -> v.getStatus().toString().equalsIgnoreCase(status))
               .collect(Collectors.toList())
               : allRequests;
    }
    @Override
    public List<VacationRequest> getRequestsByAuthor(Long employeeId) {
        return vacationRequestRepository.findByEmployeeId(employeeId);
    }

    // MAPPING for DTO AND ENTITY
    public  VacationRequestDTO toDto(VacationRequest vacationRequest) {
        VacationRequestDTO dto = new VacationRequestDTO();
        dto.setId(vacationRequest.getId());
        dto.setEmployeeIdvacationRequest.getAuthor().getId());
        dto.setStatus(vacationRequest.getStatus());
        dto.setResolvedBy(vacationRequest.getResolvedBy());
        dto.setRequestCreatedAt(vacationRequest.getRequestCreatedAt());
        dto.setVacationStartDate(vacationRequest.getVacationStartDate());
        dto.setVacationEndDate(vacationRequest.getVacationEndDate());
        return dto;
    }

    public  VacationRequest toEntity(VacationRequestDTO dto, Employee author) {
        VacationRequest vacationRequest = new VacationRequest();
        vacationRequest.setId(dto.getId());
        vacationRequest.setAuthor(author);
        vacationRequest.setStatus(STATUSOPTION.PENDING);
        vacationRequest.setResolvedBy(dto.getResolvedBy());
        vacationRequest.setRequestCreatedAt(LocalDateTime.now());
        vacationRequest.setVacationStartDate(dto.getVacationStartDate());
        vacationRequest.setVacationEndDate(dto.getVacationEndDate());
        return vacationRequest;
    }
}
