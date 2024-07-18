package com.vacation.controller;

import com.vacation.entity.VacationRequest;
import com.vacation.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    @Autowired
    private VacationService vacationService;
   // for approving request
    @PostMapping("/vacation-requests/{requestId}")
    public ResponseEntity<VacationRequest> processVacationRequest(@PathVariable Long requestId, @RequestParam String status,
            @RequestParam Long managerId) {
        VacationRequest processedRequest = vacationService.processRequest(requestId, status, managerId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(processedRequest);
    }
   // for get all data with filter
    @GetMapping("/vacation-requests")
    public ResponseEntity<List<VacationRequest>> getAllVacationRequests(@RequestParam(required = false) String status) {
        return ResponseEntity.status(HttpStatus.OK).body(vacationService.getRequestsByStatus(status));

    }


}