package com.pbc.pmtool.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.pbc.pmtool.entity.Sprint;
import com.pbc.pmtool.model.FormSprint;

@Mapper(componentModel = "spring")
public interface SprintMapper {

	
	public Sprint toEntity(FormSprint sprintModel);
	
	public FormSprint toModel(Sprint sprintEntity);
	
}
