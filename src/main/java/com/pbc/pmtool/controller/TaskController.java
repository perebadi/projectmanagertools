package com.pbc.pmtool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Task;
import com.pbc.pmtool.repository.UserRepository;
import com.pbc.pmtool.service.ProjectAchievementService;
import com.pbc.pmtool.service.ProjectEscalationService;
import com.pbc.pmtool.service.ProjectNextStepService;
import com.pbc.pmtool.service.ProjectPhaseService;
import com.pbc.pmtool.service.ProjectProblemService;
import com.pbc.pmtool.service.ProjectService;
import com.pbc.pmtool.service.ProjectStatusLightService;
import com.pbc.pmtool.service.ProjectTaskService;


@Controller
@RequestMapping("/tasks")
@PreAuthorize("hasAuthority('ROLE_PM')")
public class TaskController {
	
	static String sessionuser;
	
	@Autowired
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired
	@Qualifier("projectStatusLightServiceImpl")
	private ProjectStatusLightService projectStatusLightService;
	
	@Autowired
	@Qualifier("projectAchievementServiceImpl")
	private ProjectAchievementService projectAchievementService;
	
	@Autowired
	@Qualifier("projectNextStepServiceImpl")
	private ProjectNextStepService projectNextStepService;
	
	@Autowired
	@Qualifier("projectProblemServiceImpl")
	private ProjectProblemService projectProblemService;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("projectEscalationServiceImpl")
	private ProjectEscalationService projectEscalationService;
	
	@Autowired
	@Qualifier("projectPhaseServiceImpl")
	private ProjectPhaseService projectPhaseService;

	@Autowired
	@Qualifier("projectTaskServiceImpl")
	private ProjectTaskService projectTaskService;
	
	@GetMapping("/")
	public ModelAndView Welcome() throws IllegalArgumentException, IllegalAccessException{
		ModelAndView mav = new ModelAndView(ViewConstant.TASKFORMEDIT);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		sessionuser=user.getUsername();
		mav.addObject("username", sessionuser);
		mav.addObject("numprojects",projectService.countRecords(userRepository.findByUsername(user.getUsername())));
		
		mav.addObject("projects", projectService.listProjectByUser(userRepository.findByUsername(user.getUsername())));
		

		mav.addObject("backlogs", projectTaskService.listProjectTasks(1));
		mav.addObject("todos", projectTaskService.listProjectTasks(2));
		mav.addObject("progresses", projectTaskService.listProjectTasks(3));
		mav.addObject("dones", projectTaskService.listProjectTasks(4));
		

		return mav;
	}
	
	@GetMapping("/project/{idproject}/move/{idtask}/{status}/")
	public String Movetask(@PathVariable int idproject,@PathVariable int idtask, @PathVariable String status) {
		
		
		Task task = projectTaskService.findProjectTaskById(idtask);
		

		int defualtidstatus = task.getStatus();
		
		switch (status) {
		case "backlog":
			task.setStatus(1);
			break;
		case "todo":
			task.setStatus(2);
			break;
		case "progress":
			task.setStatus(3);
			break;	
		case "done":
			task.setStatus(4);
			break;
		default:
			task.setStatus(defualtidstatus);
			break;
		}
		
		projectTaskService.addProjectTask(task);

		System.out.println("id project : " + idproject);
		System.out.println("id tarea : " + idtask);
		System.out.println("id status: " + status);
		
		System.out.println("result  task id: " + task.getId() + "status : " + task.getStatus());
		
		return "redirect:/tasks/project/"+idproject+"/";
	
	}
	
	@GetMapping("/project/yourprojects/")
	public ModelAndView viewProject(@RequestParam(name="pageno", required=false, defaultValue="0") int pageno) {
		ModelAndView mav = new ModelAndView(ViewConstant.TASKPROJECTS);

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		sessionuser=user.getUsername();
		
		
		mav.addObject("numprojects",projectService.countRecords(userRepository.findByUsername(sessionuser)));

		mav.addObject("projects",projectTaskService.countRecordsByProject(sessionuser));
		mav.addObject("username", sessionuser);
		return mav;
	}
	
	@GetMapping("/project/{idproject}/")
	public ModelAndView Projecttask(@PathVariable int idproject) {
		
		ModelAndView mav = new ModelAndView(ViewConstant.TASKFORMEDIT);
		
		mav.addObject("numprojects",projectService.countRecords(userRepository.findByUsername(sessionuser)));
		mav.addObject("projects", projectService.listProjectByUser(userRepository.findByUsername(sessionuser)));
		
		/*
		mav.addObject("backlogs", projectTaskService.listProjectTasks(projectService.findProjectById(idproject), 1));
		mav.addObject("todos", projectTaskService.listProjectTasks(projectService.findProjectById(idproject),2));
		mav.addObject("progresses", projectTaskService.listProjectTasks(projectService.findProjectById(idproject),3));
		mav.addObject("dones", projectTaskService.listProjectTasks(projectService.findProjectById(idproject),4));
		*/
		
		mav.addObject("backlogs", projectTaskService.listProjectTasksShow(projectService.findProjectById(idproject), 1));
		mav.addObject("todos", projectTaskService.listProjectTasksShow(projectService.findProjectById(idproject),2));
		mav.addObject("progresses", projectTaskService.listProjectTasksShow(projectService.findProjectById(idproject),3));
		mav.addObject("dones", projectTaskService.listProjectTasksShow(projectService.findProjectById(idproject),4));
		
		mav.addObject("assigneds",projectService.findProjectById(idproject).getAssigneds());
		mav.addObject("projectid", idproject);
		
		return mav;
	}
	
	
	
	
	
}	
	

	


