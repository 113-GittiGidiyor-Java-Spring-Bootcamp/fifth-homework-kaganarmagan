package com.system.management.controller;



import com.system.management.dto.PermanentInstructorDTO;
import com.system.management.dto.VisitingResearcherDTO;
import com.system.management.entity.Instructor;
import com.system.management.entity.InstructorSalaryLogger;
import com.system.management.entity.PermanentInstructor;
import com.system.management.entity.VisitingResearcher;
import com.system.management.exceptions.InstructorNotFoundException;
import com.system.management.service.PermanentInstructorService;
import com.system.management.service.VisitingResearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final PermanentInstructorService permanentInstructorService;
    private final VisitingResearcherService visitingResearcherService;




//permanents  instructor methods
    @GetMapping("/permanent")
    public ResponseEntity<List<PermanentInstructor>> findPermanentInstructors(@RequestParam(required = false,defaultValue = "")String name){
        if(name.length()!=0){
            return new ResponseEntity<>(permanentInstructorService.findByNameContaining(name),HttpStatus.OK);

        }else {
            return new ResponseEntity<>(permanentInstructorService.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/permanent/{id}")
    public ResponseEntity<PermanentInstructor> findPermanentInstructorById(@PathVariable int id){
        return new ResponseEntity<>(permanentInstructorService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/permanent")
    public ResponseEntity<PermanentInstructor> savePermanentInstructor(@RequestBody PermanentInstructorDTO permenantInstructorDTO){


        return new ResponseEntity<>(permanentInstructorService.save(permenantInstructorDTO),HttpStatus.CREATED);
    }

    @PutMapping("/permanent")
    public ResponseEntity<PermanentInstructor> updatePermanentInstructor(@RequestBody PermanentInstructorDTO permanentInstructorDTO){
        return new ResponseEntity<>(permanentInstructorService.update(permanentInstructorDTO),HttpStatus.OK);
    }

    @DeleteMapping("/permanent/{id}")
    public ResponseEntity<String> deletePermanentInstructorById(@PathVariable int id){
        permanentInstructorService.deleteById(id);
        return new ResponseEntity<>("Deleted...",HttpStatus.OK);
    }

    @DeleteMapping("/permanent")
    public ResponseEntity<String> deletePermanentInstructor(@RequestBody PermanentInstructor permanentInstructor){
        permanentInstructorService.delete(permanentInstructor);
        return new ResponseEntity<>("Deleted...",HttpStatus.OK);
    }

    @DeleteMapping("/permanent/name/{name}")
    public ResponseEntity<String> deletePermanentInstructorByName(@PathVariable String name){
        permanentInstructorService.deleteBYName(name);
        return new ResponseEntity<>("Deleted...",HttpStatus.OK);
    }

    @PutMapping("/permanent/{id}/{exchangeRate}")
    public ResponseEntity<PermanentInstructor> changePermanentInstructorSalary(@PathVariable int id, @PathVariable double exchangeRate){
        if(permanentInstructorService.existsById(id)){
            return new ResponseEntity<>(permanentInstructorService.updateSalary(id,exchangeRate),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
     //# Permanent Instructor
    //Visiting researcher methods
    @GetMapping("/visiting")
    public ResponseEntity<List<VisitingResearcher>> findVisitingResearchers(@RequestParam(required = false,defaultValue = "") String name){
        if(name.length()!=0){
            return new ResponseEntity<>(visitingResearcherService.findByNameContaining(name),HttpStatus.OK);

        }else {
            return new ResponseEntity<>(visitingResearcherService.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/visiting/{id}")
    public ResponseEntity<VisitingResearcher> findVisitingResearcherById(@PathVariable int id){
        return new ResponseEntity<>(visitingResearcherService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/visiting")
    public ResponseEntity<VisitingResearcher> saveVisitingResearcher(@RequestBody VisitingResearcherDTO visitingResearcherDTO){
        return new ResponseEntity<>(visitingResearcherService.save(visitingResearcherDTO),HttpStatus.CREATED);
    }

    @PutMapping("/visiting")
    public ResponseEntity<VisitingResearcher> updateVisitingResearcher(@RequestBody VisitingResearcherDTO visitingResearcherDTO){
        return new ResponseEntity<>(visitingResearcherService.update(visitingResearcherDTO),HttpStatus.OK);
    }

    @DeleteMapping("/visiting/{id}")
    public ResponseEntity<String> deleteVisitingResearcherById(@PathVariable int id){
        visitingResearcherService.deleteById(id);
        return new ResponseEntity<>("Deleted...",HttpStatus.OK);
    }

    @DeleteMapping("/visiting")
    public ResponseEntity<String> deleteVisitingResearcher(@RequestBody VisitingResearcher visitingResearcher){
        visitingResearcherService.delete(visitingResearcher);
        return new ResponseEntity<>("Deleted...",HttpStatus.OK);
    }

    @DeleteMapping("/visiting/name/{name}")
    public ResponseEntity<String> deleteVisitingResearcherByName(@PathVariable String name){
        visitingResearcherService.deleteByName(name);
        return new ResponseEntity<>("Deleted...",HttpStatus.OK);
    }
    @PutMapping("/visiting/{id}/{exchangeRate}")
    public ResponseEntity<VisitingResearcher> changeVisitingResearcherSalary(@PathVariable int id, @PathVariable double exchangeRate){
        if(visitingResearcherService.existsById(id)){
            return new ResponseEntity<>(visitingResearcherService.updateSalary(id,exchangeRate),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/salary/top")
    public ResponseEntity<List<PermanentInstructor>> getTop3InstructorOrderBySalary(){
        return new ResponseEntity<>(permanentInstructorService.findTop3ByOrderByFixedSalaryDesc(),HttpStatus.OK);
    }
    @GetMapping("/salary/bottom")
    public ResponseEntity<List<VisitingResearcher>> getBottom3InstructorOrderBySalary(){
        return new ResponseEntity<>(visitingResearcherService.findBottom3InstructorOrderBySalary(),HttpStatus.OK);
    }

    @GetMapping("/salary/exchange")
    public ResponseEntity<List<InstructorSalaryLogger>> findExchangeLoggers(@RequestParam(required = false,defaultValue = "-1")String id,@RequestParam(required = false,defaultValue = "2200-12-31")String date){
        int intId=Integer.parseInt(id);
        LocalDate localDate=LocalDate.parse(date);
        if(intId==-1){
            if(date.equals("2200-12-31")){
                return new ResponseEntity<>(permanentInstructorService.findAllSalaryLogger(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(permanentInstructorService.findSalaryLoggerByDate(localDate), HttpStatus.OK);
            }
        }else {
            if(date.equals("2200-12-31")){
                return new ResponseEntity<>(permanentInstructorService.findSalaryLoggerById(intId), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(permanentInstructorService.findSalaryLoggerByIdAndByDate(intId,localDate), HttpStatus.OK);
            }
        }
    }

}
