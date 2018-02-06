package com.pbc.pmtool.controller;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.notNull;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;
import com.pbc.pmtool.entity.ProjectEscalation;
import com.pbc.pmtool.entity.ProjectNextStep;
import com.pbc.pmtool.entity.ProjectPhase;
import com.pbc.pmtool.entity.ProjectProblem;
import com.pbc.pmtool.entity.ProjectStatusLight;
import com.pbc.pmtool.model.FormAchievementModel;
import com.pbc.pmtool.model.FormEscalationModel;
import com.pbc.pmtool.model.FormFinancialModel;
import com.pbc.pmtool.model.FormNewProjectModel;
import com.pbc.pmtool.model.FormNextStepModel;
import com.pbc.pmtool.model.FormPhaseModel;
import com.pbc.pmtool.model.FormProblemModel;
import com.pbc.pmtool.model.FormRagModel;
import com.pbc.pmtool.repository.ProjectEscalationRepository;
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
		mav.addObject("todo2", projectTaskService.listProjectTasks(2));
		mav.addObject("progresses", projectTaskService.listProjectTasks(3));
		mav.addObject("dones", projectTaskService.listProjectTasks(4));
		

		return mav;
	}
	
	
	
}	
	

	


