package com.system.management.service;





import com.system.management.dto.VisitingResearcherDTO;
import com.system.management.entity.InstructorSalaryLogger;
import com.system.management.entity.PermanentInstructor;
import com.system.management.entity.VisitingResearcher;
import com.system.management.exceptions.InstructorAlreadyExistsException;
import com.system.management.mappers.VisitingResearcherMapper;
import com.system.management.repository.InstructorRepository;
import com.system.management.repository.SalaryExchangeLoggerRepository;
import com.system.management.repository.VisitingResearcherRepository;
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
public class VisitingResearcherService implements BaseService<VisitingResearcher> {
    @Autowired
    private VisitingResearcherRepository repository;
    @Autowired
    private  VisitingResearcherMapper mapper;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private ClientRequestInfo clientRequestInfo;
    @Autowired
    SalaryExchangeLoggerRepository salaryExchangeLoggerRepository;
    @Override
    @Transactional(readOnly = true)
    public List<VisitingResearcher> findAll() {
        List<VisitingResearcher>researcherList=new ArrayList<>();
        Iterable<VisitingResearcher>researcherIter=repository.findAll();
        researcherIter.iterator().forEachRemaining(researcherList::add);
        return  researcherList;
    }

    @Override
    @Transactional(readOnly = true)
    public VisitingResearcher findById(int id) {
        Optional<VisitingResearcher> visitingResearcher=repository.findById(id);
        return visitingResearcher.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<VisitingResearcher> findBottom3InstructorOrderBySalary() {
        return repository.findTop3ByOrderByHourlySalaryAsc();
    }

    @Transactional(readOnly = true)
    public List<VisitingResearcher> findByNameContaining(String name) {
        return repository.findByNameContaining(name);
    }

    @Transactional(readOnly = true)
    public boolean existsById(int id){
        return repository.existsById(id);
    }
    @Transactional
    public VisitingResearcher save(VisitingResearcherDTO object) {
        if(instructorRepository.existsInstructorByPhoneNumber(object.getPhoneNumber())){
            throw new InstructorAlreadyExistsException("Phone number already is used by a Instructor");
        }

        VisitingResearcher visitingResearcher=mapper.mapFromVisitingResearcherDTOtoVisitingResearcher(object);
        return repository.save(visitingResearcher);
    }


    @Transactional
    public VisitingResearcher update(VisitingResearcherDTO object) {
        if(instructorRepository.existsInstructorByPhoneNumber(object.getPhoneNumber())){
            throw new InstructorAlreadyExistsException("Phone number already is used by a Instructor");
        }
        VisitingResearcher visitingResearcher=mapper.mapFromVisitingResearcherDTOtoVisitingResearcher(object);

        return repository.save(visitingResearcher);
    }

    @Override
    @Transactional
    public void delete(VisitingResearcher object) {
        repository.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String name){
        repository.deleteByName(name);
    }

    @Transactional
    public VisitingResearcher updateSalary(int id, double exchangeRate) {
        VisitingResearcher instructor=repository.findById(id).get();
        double salaryBefore=instructor.getHourlySalary();
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
        instructor.setHourlySalary(salaryAfter);

        return repository.save(instructor);
    }
}
