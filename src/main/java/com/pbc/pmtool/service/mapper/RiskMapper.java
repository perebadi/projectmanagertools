package com.pbc.pmtool.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.pbc.pmtool.entity.Risk;
import com.pbc.pmtool.model.FormRiskModel;

@Mapper(componentModel = "spring")
public interface RiskMapper {
	
	@Mappings({
	      @Mapping(target="idproblem", source="risk.id")
	})
	public FormRiskModel toModel(Risk risk);
}
