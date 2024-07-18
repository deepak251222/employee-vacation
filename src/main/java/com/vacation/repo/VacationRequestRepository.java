package com.vacation.repo;

import com.vacation.entity.STATUSOPTION;
import com.vacation.entity.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {

    @Query("SELECT v FROM VacationRequest v WHERE v.author.id = :employeeId")
    List<VacationRequest> findByEmployeeId(@Param("employeeId") Long employeeId);

}