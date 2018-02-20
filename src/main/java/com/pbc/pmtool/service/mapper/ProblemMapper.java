package com.pbc.pmtool.service.mapper;

import org.mapstruct.Mapper;

import com.pbc.pmtool.entity.Problem;
import com.pbc.pmtool.model.FormProblemModel;

@Mapper(componentModel = "spring")
public interface ProblemMapper {

	public FormProblemModel toModel(Problem problem);
	
}
