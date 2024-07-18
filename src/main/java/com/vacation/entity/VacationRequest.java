package com.vacation.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee author;

    @Enumerated(EnumType.STRING)
    private STATUSOPTION status;

    private Long resolvedBy;     // manager id

    private LocalDateTime requestCreatedAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vacationStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vacationEndDate;

}