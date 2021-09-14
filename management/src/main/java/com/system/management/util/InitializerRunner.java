package com.system.management.util;


import com.system.management.entity.*;
import com.system.management.repository.CourseRepository;
import com.system.management.repository.PermanentInstructorRepository;
import com.system.management.repository.StudentRepository;
import com.system.management.repository.VisitingResearcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class InitializerRunner implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    PermanentInstructorRepository permanentRepository;
    @Autowired
    VisitingResearcherRepository visitingRepository;

    @Override
    public void run(String... args) throws Exception {
        courseRepository.deleteAll();
        visitingRepository.deleteAll();
        permanentRepository.deleteAll();
        studentRepository.deleteAll();

        courseRepository.save(Course.builder().courseName("Math").creditScore(4).prefix("MAT101").build());
        courseRepository.save(Course.builder().courseName("Turkish").creditScore(3).prefix("TUR101").build());
        courseRepository.save(Course.builder().courseName("Physic").creditScore(4).prefix("PHY101").build());
        courseRepository.save(Course.builder().courseName("Chem").creditScore(4).prefix("CHM101").build());
        courseRepository.save(Course.builder().courseName("History").creditScore(4).prefix("HIS101").build());


       visitingRepository.save(VisitingResearcher.builder().name("Sumner Cann").address("13695 Canary Park").phoneNumber(5047721899L).hourlySalary(120).build());
       visitingRepository.save(VisitingResearcher.builder().name("Damita McInulty").address("1086 Cardinal Park").phoneNumber(4688746769L).hourlySalary(145).build());
       visitingRepository.save(VisitingResearcher.builder().name("Melloney Bourges").address("3109 Laurel Terrace").phoneNumber(5603111493L).hourlySalary(150).build());
       visitingRepository.save(VisitingResearcher.builder().name("Rodie Waring").address("89 Pawling Parkway").phoneNumber(4041932160L).hourlySalary(200).build());

       permanentRepository.save(PermanentInstructor.builder().name("Shaun Edger").address("6141 Loftsgordon Place").phoneNumber(8172347209L).fixedSalary(3340).build());
       permanentRepository.save(PermanentInstructor.builder().name("Heloise Biswell").address("685 Merchant Avenue").phoneNumber(9165139716L).fixedSalary(2910).build());
       permanentRepository.save(PermanentInstructor.builder().name("Starlin Websdale").address("38472 Tomscot Circle").phoneNumber(3101609956L).fixedSalary(3860).build());
       permanentRepository.save(PermanentInstructor.builder().name("Cortney Puncher").address("8419 Dakota Trail").phoneNumber(9095889017L).fixedSalary(3020).build());

        studentRepository.save(Student.builder().birthDate(LocalDate.of(1995, Month.DECEMBER,27)).gender(Gender.Male).name("Manya Thirwell").address("8 Gale Center").build());
        studentRepository.save(Student.builder().birthDate(LocalDate.of(2000, Month.NOVEMBER,26)).gender(Gender.Female).name("Rowland Vowell").address("266 Forest Run Hill").build());
        studentRepository.save(Student.builder().birthDate(LocalDate.of(1996, Month.FEBRUARY,5)).gender(Gender.Female).name("Sharity Pickance").address("55745 Marcy Place").build());
        studentRepository.save(Student.builder().birthDate(LocalDate.of(1999, Month.JULY,1)).gender(Gender.Male).name("Tresa Tosdevin").address("504 Summerview Crossing").build());
        studentRepository.save(Student.builder().birthDate(LocalDate.of(1997, Month.APRIL,27)).gender(Gender.Male).name("Martainn Minshaw").address("07 Linden Point").build());
        studentRepository.save(Student.builder().birthDate(LocalDate.of(1999, Month.MAY,24)).gender(Gender.Female).name("Anstice Enticknap").address("3 Chinook Avenue").build());
        studentRepository.save(Student.builder().birthDate(LocalDate.of(2000, Month.JULY,3)).gender(Gender.Male).name("Tristan Winscomb").address("695 Anthes Junction").build());
        studentRepository.save(Student.builder().birthDate(LocalDate.of(1999, Month.FEBRUARY,3)).gender(Gender.Female).name("Emile Werrett").address("10 Scoville Park").build());
        studentRepository.save(Student.builder().birthDate(LocalDate.of(1996, Month.JULY,28)).gender(Gender.Female).name("Sullivan Gelling").address("39 Mockingbird Alley").build());
        studentRepository.save(Student.builder().birthDate(LocalDate.of(1998, Month.JANUARY,14)).gender(Gender.Female).name("Brent Dankov").address("59104 Duke Point").build());
        studentRepository.save(Student.builder().birthDate(LocalDate.of(1997, Month.SEPTEMBER,5)).gender(Gender.Male).name("Carmelina Delafont").address("51419 Heffernan Park").build());






    }
}
