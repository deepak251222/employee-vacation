package com.vacation.service;

import com.vacation.dto.VacationRequestDTO;
import com.vacation.entity.Employee;
import com.vacation.entity.VacationRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface VacationService {
  //  List<VacationRequest> getRequestsByAuthorAndStatus(Long employeeId, String status);
  Map<Employee, List<VacationRequestDTO>> getRequestsByAuthorAndStatus(Long employeeId, String status);
    List<VacationRequest> getRequestsByStatus(String status);
    List<VacationRequest> getRequestsByAuthor(Long employeeId);
    VacationRequestDTO createVacationRequest(Long authorId, VacationRequestDTO requestDTO);
    VacationRequest processRequest(Long requestId, String status, Long managerId);

}
