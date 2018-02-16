package com.pbc.pmtool.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pbc.pmtool.entity.Comment;
import com.pbc.pmtool.entity.Task;
import com.pbc.pmtool.model.CommentModel;
import com.pbc.pmtool.model.FormShowTaskModel;

@Component("formTaskShowConverter")
public class FormTaskShowConverter {

	@Autowired
	@Qualifier("commentConverter")
	private CommentConverter commentConverter;
	
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
		
		List<CommentModel> comments = new ArrayList<CommentModel>();
		
		for(Comment comment : task.getComments()) {
			comments.add(commentConverter.Comment2CommentModel(comment));
		}
		
		taskShowModel.setComments(comments);
		taskShowModel.setStatus(task.getStatus());
		
		return taskShowModel;
	}

}
