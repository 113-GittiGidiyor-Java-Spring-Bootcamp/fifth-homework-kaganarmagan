package com.system.management.mappers;

import com.system.management.dto.PermanentInstructorDTO;
import com.system.management.entity.PermanentInstructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PermanentInstructorMapper {
    public abstract PermanentInstructor mapFromPermanentInstructorDTOtoPermanentInstructor(PermanentInstructorDTO permanentInstructorDTO);
    public abstract PermanentInstructorDTO mapFromPermanentInstructorToPermanentInstructorDTO(PermanentInstructor permanentInstructor);
}
