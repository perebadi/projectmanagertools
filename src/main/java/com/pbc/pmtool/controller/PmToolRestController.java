package com.pbc.pmtool.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.model.FormAssignToProjectModel;
import com.pbc.pmtool.model.Response;
import com.pbc.pmtool.service.ProjectService;
import com.pbc.pmtool.service.UserService;

@RestController
@RequestMapping("/api/")
public class PmToolRestController {
	
	@Autowired
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
 
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	
	@GetMapping(value = "/projects/all")
	public List<Project> getProject(){
			return projectService.listProjects();
	}
	
	@PostMapping(value = "/assign/")
	public Response addToProject( @RequestBody FormAssignToProjectModel formAssignToProjectModel) {
		

		Project project = projectService.findProjectById(formAssignToProjectModel.getProjectid());
		
		/*List<User> users = new ArrayList<>(projectService.findProjectById(formAssignToProjectModel.getProjectid()).getAssigneds() );
		users.add(userService.getUser(formAssignToProjectModel.getUsername()));
		
		Set<User> assigneds = new HashSet<User>(users);

		
		project.setAssigneds(assigneds);
		*/
		User  user = userService.getUser(formAssignToProjectModel.getUsername());
		
		
		
		user.getProjects().add(project);
		project.getAssigneds().add(userService.getUser(formAssignToProjectModel.getUsername()));
		
		System.out.println(userService.getUser(formAssignToProjectModel.getUsername()).getName());
		
		projectService.addProject(project);
		userService.addUser(user);
		
		Response res = new Response("Done", "Done");
		return res;
	}
	
	
	

}




	