package com.vacation.dto;

import com.vacation.entity.STATUSOPTION;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationRequestDTO {
    private Long id;
    private Long employeeId;
    private STATUSOPTION status;  // enum type
    private Long resolvedBy;
    private LocalDateTime requestCreatedAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vacationStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vacationEndDate;

}