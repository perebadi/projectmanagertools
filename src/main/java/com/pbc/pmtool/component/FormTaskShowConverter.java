package com.pbc.pmtool.component;

import org.springframework.stereotype.Component;

import com.pbc.pmtool.entity.Task;
import com.pbc.pmtool.model.FormShowTaskModel;

@Component("formTaskShowConverter")
public class FormTaskShowConverter {

	public FormShowTaskModel task2FormTaskShow(Task task) {
		FormShowTaskModel taskShowModel = new FormShowTaskModel();

		taskShowModel.setId(task.getId());
		taskShowModel.setProject(task.getProject());
		taskShowModel.setDetails(task.getDetails());
		taskShowModel.setSummary(task.getSummary());
		taskShowModel.setHours(task.getHours());
		taskShowModel.setEstimatedhours(task.getEstimatedhours());
		taskShowModel.setUser(task.getUser());
		taskShowModel.setRealvsestimated((task.getHours() * 100) / task.getEstimatedhours());

		return taskShowModel;
	}

}
