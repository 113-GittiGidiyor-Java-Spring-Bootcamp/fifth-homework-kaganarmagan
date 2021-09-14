package com.system.management.service;



import com.system.management.dto.PermanentInstructorDTO;
import com.system.management.entity.InstructorSalaryLogger;
import com.system.management.entity.PermanentInstructor;
import com.system.management.exceptions.InstructorAlreadyExistsException;
import com.system.management.mappers.PermanentInstructorMapper;
import com.system.management.repository.InstructorRepository;
import com.system.management.repository.PermanentInstructorRepository;
import com.system.management.repository.SalaryExchangeLoggerRepository;
import com.system.management.util.ClientRequestInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermanentInstructorService implements BaseService<PermanentInstructor> {
    @Autowired
    private  PermanentInstructorRepository repository;
    @Autowired
    private  PermanentInstructorMapper mapper;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    ClientRequestInfo clientRequestInfo;
    @Autowired
    SalaryExchangeLoggerRepository salaryExchangeLoggerRepository;



    @Override
    @Transactional(readOnly = true)
    public List<PermanentInstructor> findAll() {
        List<PermanentInstructor> permanentInstructors=new ArrayList<>();
        Iterable<PermanentInstructor> permanentIter=repository.findAll();
        permanentIter.iterator().forEachRemaining(permanentInstructors::add);
        return permanentInstructors;
    }

    @Override
    @Transactional(readOnly = true)
    public PermanentInstructor findById(int id) {
        Optional<PermanentInstructor> permanentInstructor=repository.findById(id);
        return permanentInstructor.orElse(null);
    }
    @Transactional(readOnly = true)
    public boolean existsById(int id){
        return repository.existsById(id);
    }
    @Transactional(readOnly = true)
    public List<PermanentInstructor> findByNameContaining(String name){
        return repository.findByNameContaining(name);
    }
    @Transactional(readOnly = true)
    public List<PermanentInstructor> findTop3ByOrderByFixedSalaryDesc(){
        return repository.findTop3ByOrderByFixedSalaryDesc();
    }

    @Transactional
    public PermanentInstructor updateSalary(int id,double exchangeRate){
        PermanentInstructor instructor=repository.findById(id).get();
        double salaryBefore=instructor.getFixedSalary();
        double salaryAfter=salaryBefore+(salaryBefore*exchangeRate);

        InstructorSalaryLogger logger=new InstructorSalaryLogger();
        logger.setInstructorId(instructor.getID());
        logger.setExchangeDateTime(LocalDate.now());
        logger.setSalaryBefore(salaryBefore);
        logger.setSalaryAfter(salaryAfter);
        logger.setExchangeRatio(exchangeRate);
        logger.setClientUrl(clientRequestInfo.getClientUrl());
        logger.setClientIpAdress(clientRequestInfo.getClientIpAdress());
        logger.setSessionActivityId(clientRequestInfo.getSessionActivityId());
        salaryExchangeLoggerRepository.save(logger);
        instructor.setFixedSalary(salaryAfter);

        return repository.save(instructor);
    }

    @Transactional
    public PermanentInstructor save(PermanentInstructorDTO object) {

        if(instructorRepository.existsInstructorByPhoneNumber(object.getPhoneNumber())){
            throw new InstructorAlreadyExistsException("Phone number already is used by a Instructor");
        }
        PermanentInstructor permanentInstructor= mapper.mapFromPermanentInstructorDTOtoPermanentInstructor(object);
        return repository.save(permanentInstructor);
    }


    @Transactional
    public PermanentInstructor update(PermanentInstructorDTO object) {
        if(instructorRepository.existsInstructorByPhoneNumber(object.getPhoneNumber())){
            throw new InstructorAlreadyExistsException("Phone number already is used by a Instructor");
        }
       PermanentInstructor permanentInstructor= mapper.mapFromPermanentInstructorDTOtoPermanentInstructor(object);
        return repository.save(permanentInstructor);
    }

    @Override
    @Transactional
    public void delete(PermanentInstructor object) {
        repository.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteBYName(String name){
        repository.deleteByName(name);
    }

    @Transactional(readOnly = true)
    public List<InstructorSalaryLogger> findAllSalaryLogger() {
        List<InstructorSalaryLogger> loggers=new ArrayList<>();
        Iterable<InstructorSalaryLogger> loggerIter=salaryExchangeLoggerRepository.findAll();
        loggerIter.iterator().forEachRemaining(loggers::add);
        return loggers;
    }
    @Transactional(readOnly = true)
    public List<InstructorSalaryLogger> findSalaryLoggerByDate(LocalDate localDate) {
        List<InstructorSalaryLogger> loggers=new ArrayList<>();
        Iterable<InstructorSalaryLogger> loggerIter=salaryExchangeLoggerRepository.findByExchangeDateTime(localDate);
        loggerIter.iterator().forEachRemaining(loggers::add);
        return loggers;
    }
    @Transactional
    public  List<InstructorSalaryLogger> findSalaryLoggerById(int intId) {
        List<InstructorSalaryLogger> loggers=new ArrayList<>();
        Iterable<InstructorSalaryLogger> loggerIter=salaryExchangeLoggerRepository.findByInstructorId(intId);
        loggerIter.iterator().forEachRemaining(loggers::add);
        return loggers;
    }
    @Transactional
    public  List<InstructorSalaryLogger> findSalaryLoggerByIdAndByDate(int intId, LocalDate localDate) {
        List<InstructorSalaryLogger> loggers=new ArrayList<>();
        Iterable<InstructorSalaryLogger> loggerIter=salaryExchangeLoggerRepository.findByInstructorIdAndExchangeDateTime(intId,localDate);
        loggerIter.iterator().forEachRemaining(loggers::add);
        return loggers;
    }
}
