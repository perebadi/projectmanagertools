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
import com.pbc.pmtool.repository.ProjectRepository;
import com.pbc.pmtool.repository.UserRepository;
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
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("projectRepository")
	private ProjectRepository projectRepository;
	
	@GetMapping(value = "/projects/all")
	public List<Project> getProject(){
			return projectService.listProjects();
	}
	
	@PostMapping(value = "/assign/")
	public Response addToProject( @RequestBody FormAssignToProjectModel formAssignToProjectModel) {
		
		
		User  user = userService.getUser(formAssignToProjectModel.getUsername());
		Project project = projectService.findProjectById(formAssignToProjectModel.getProjectid());

		
		/*subjectRepository.save(math);
		subjectRepository.save(computer);*/
		
		List<Project> projects = user.getAssigneds()	;
		projects.add(project);
		user.setAssigneds(projects);
		userRepository.save(user);
		
		
	
		
	/*	List<Project> projects = new ArrayList<Project>();	

		Set<User> users = new HashSet<User>();
		users.add(user);
		project.setAssigneds(users);
		projectRepository.save(project);*/
	
		
		Response res = new Response("Done", "Done");
		return res;
	}
	
	
	

}




	