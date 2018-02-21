package com.pbc.pmtool.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.pbc.pmtool.entity.Problem;
import com.pbc.pmtool.model.FormProblemModel;

@Mapper(componentModel = "spring")
public interface ProblemMapper {

	@Mappings({
	      @Mapping(target="idproblem", source="problem.id")
	})
	public FormProblemModel toModel(Problem problem);
	
}
