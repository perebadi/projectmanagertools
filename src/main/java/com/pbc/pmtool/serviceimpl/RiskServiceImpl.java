package com.pbc.pmtool.serviceimpl;

import java.text.SimpleDateFormat;

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
		FormRiskModel riskModel = riskMapper.toModel(riskRepository.findByIdAndProject_id(id, project));
		
		if(riskModel != null) {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
			String date = DATE_FORMAT.format(riskModel.getDateproblem());
			
			riskModel.setDateproblemstr(date);
			
			try {
				date = DATE_FORMAT.format(riskModel.getDateclose());
			
				riskModel.setDateclosestr(date);
			}catch (NullPointerException e) {
				riskModel.setDateclosestr("");
			}
			
			return riskModel;
		}
		
		return null;
	}

}
