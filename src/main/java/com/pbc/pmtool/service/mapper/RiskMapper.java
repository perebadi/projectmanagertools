package com.pbc.pmtool.service.mapper;

import org.mapstruct.Mapper;

import com.pbc.pmtool.entity.Problem;
import com.pbc.pmtool.entity.Risk;
import com.pbc.pmtool.model.FormRiskModel;

@Mapper(componentModel = "spring")
public interface RiskMapper {
	
	public FormRiskModel toModel(Risk risk);
}
