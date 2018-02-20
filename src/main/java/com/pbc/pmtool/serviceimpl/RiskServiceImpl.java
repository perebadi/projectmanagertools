package com.pbc.pmtool.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.model.FormRiskModel;
import com.pbc.pmtool.repository.RiskRepository;
import com.pbc.pmtool.service.RiskService;
import com.pbc.pmtool.service.mapper.RiskMapper;

@Service("riskServiceImpl")
public class RiskServiceImpl implements RiskService {

	@Autowired
	@Qualifier("riskRepository")
	private RiskRepository riskRepository;
	
	@Autowired
	private RiskMapper riskMapper;
	
	@Override
	public FormRiskModel getRisk(int id, int project) {
		return riskMapper.toModel(riskRepository.findByIdAndProject_id(id, project));
	}

}
