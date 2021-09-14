package com.system.management.repository;

import com.system.management.entity.InstructorSalaryLogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryExchangeLoggerRepository extends CrudRepository<InstructorSalaryLogger,Long> {
    List<InstructorSalaryLogger> findByInstructorId(long id);
    List<InstructorSalaryLogger> findByExchangeDateTime(LocalDate localDate);
    List<InstructorSalaryLogger> findByInstructorIdAndExchangeDateTime(long id, LocalDate localDate);

}
