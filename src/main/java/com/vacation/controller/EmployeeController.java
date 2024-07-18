package com.vacation.controller;

import com.vacation.dto.VacationRequestDTO;
import com.vacation.entity.Employee;
import com.vacation.entity.VacationRequest;
import com.vacation.service.EmployeeService;
import com.vacation.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")

public class EmployeeController {

    private final EmployeeService employeeService;
    private final VacationService vacationRequestService;

    public EmployeeController(EmployeeService employeeService, VacationService vacationRequestService) {
        this.employeeService = employeeService;
        this.vacationRequestService = vacationRequestService;
    }
    // if need create emp
    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestParam String employeeName) {
        Employee employee1 = employeeService.saveEmployee(employeeName);
        return  ResponseEntity.ok(employee1);
    }
   // request for vacation by employee
    @PostMapping("/{id}/vacation-requests")
    public ResponseEntity<?> createVacationRequest(@PathVariable("id") Long employeeId, @RequestBody VacationRequestDTO requestDTO) {
        VacationRequestDTO vacationRequest=vacationRequestService.createVacationRequest(employeeId, requestDTO);
        return  ResponseEntity.ok(vacationRequest);
    }
    // get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable ("id") Long employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }
 // employee by id and  status
    @GetMapping("/{id}/vacation-details")
    public ResponseEntity<?> getVacationRequests(@PathVariable ("id") Long employeeId, @RequestParam(required = false) String status) {
            return ResponseEntity.ok(vacationRequestService.getRequestsByAuthorAndStatus(employeeId, status));

    }


}