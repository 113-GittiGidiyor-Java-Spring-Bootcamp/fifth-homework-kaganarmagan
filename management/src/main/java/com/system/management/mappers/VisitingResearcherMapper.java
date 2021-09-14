package com.system.management.mappers;

import com.system.management.dto.VisitingResearcherDTO;
import com.system.management.entity.VisitingResearcher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class VisitingResearcherMapper {
    public abstract VisitingResearcher mapFromVisitingResearcherDTOtoVisitingResearcher(VisitingResearcherDTO visitingResearcherDTO);
    public abstract VisitingResearcherDTO mapFromVisitingResearcherToVisitingResearcherDTO(VisitingResearcher visitingResearcher);

}