package com.pbc.pmtool.controller;


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
import com.pbc.pmtool.entity.ProjectNextStep;
import com.pbc.pmtool.entity.ProjectPhase;
import com.pbc.pmtool.entity.ProjectStatusLight;
import com.pbc.pmtool.model.FormAchievementModel;
import com.pbc.pmtool.model.FormNewProjectModel;
import com.pbc.pmtool.model.FormRagModel;
import com.pbc.pmtool.repository.UserRepository;
import com.pbc.pmtool.service.ProjectAchievementService;
import com.pbc.pmtool.service.ProjectNextStepService;
import com.pbc.pmtool.service.ProjectService;
import com.pbc.pmtool.service.ProjectStatusLightService;


@Controller
@RequestMapping("/projects")
public class ProjectController {
	
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
	@Qualifier("userRepository")
	private UserRepository userRepository;
		
	//@PreAuthorize("hashRole('ROLE_USER')")
	@GetMapping("/")
	public ModelAndView Welcome(){
		ModelAndView mav = new ModelAndView(ViewConstant.WELCOME);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("user : " + user.getUsername());
		mav.addObject("username", user.getUsername());
		mav.addObject("numprojects",projectService.countRecords(userRepository.findByUsername(user.getUsername())));
		return mav;
	}
	
	@GetMapping("/createproject")
	public ModelAndView CreateProject(){
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTFORM);
		return mav;		
	}
	
	@PostMapping("/saveproject")
	public String SaveProject(@ModelAttribute("formNewProjectModel") FormNewProjectModel formNewProjectModel) {
		
		System.out.println(formNewProjectModel.getProjectname());
		Project project = new Project();
		
		project.setProjectname(formNewProjectModel.getProjectname());
		project.setObjectives(formNewProjectModel.getObjectives());
		project.setEACOP(formNewProjectModel.getEACOP());
		project.setInvoiced(formNewProjectModel.getInvoiced());
		project.setTIC(formNewProjectModel.getTIC());
		project.setTVC(formNewProjectModel.getTVC());
		project.setOP(formNewProjectModel.getOP());
		project.setVariance(formNewProjectModel.getVariance());
		project.setBudgettodate(formNewProjectModel.getBudgettodate());
		project.setCertifiedprogress(formNewProjectModel.getCertifiedprogress());
		project.setCostestimated(formNewProjectModel.getCostestimated());
		
		ProjectPhase projectPhaseKickOff = new ProjectPhase();
		ProjectPhase projectPhaseGoLive = new ProjectPhase();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date kickoffdate = df.parse(formNewProjectModel.getStart());
			Date golivedate = df.parse(formNewProjectModel.getEnd());
			
			projectPhaseKickOff.setSummaryphase("Kick-Off");
			projectPhaseKickOff.setStartdate(kickoffdate);
			projectPhaseKickOff.setEnddate(kickoffdate);
			projectPhaseKickOff.setProject(project);
			
			projectPhaseGoLive.setSummaryphase("Go-Live");
			projectPhaseGoLive.setStartdate(golivedate);
			projectPhaseGoLive.setEnddate(golivedate);
			projectPhaseGoLive.setProject(project);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		Set<ProjectPhase> phases = new HashSet<>();
		phases.add(projectPhaseGoLive);
		phases.add(projectPhaseKickOff);
		project.setPhases(phases);
		
		project.setProjectBenefitsRealisation(projectStatusLightService.findProjectStatusLightByStatusname("G"));
		project.setProjectBusinessChange(projectStatusLightService.findProjectStatusLightByStatusname("G"));
		project.setProjectDeliveryConfidence(projectStatusLightService.findProjectStatusLightByStatusname("G"));
		project.setProjectDependency(projectStatusLightService.findProjectStatusLightByStatusname("G"));
		project.setProjectGovernance(projectStatusLightService.findProjectStatusLightByStatusname("G"));
		project.setProjectScope(projectStatusLightService.findProjectStatusLightByStatusname("G"));
		project.setProjectStatusConfidence(projectStatusLightService.findProjectStatusLightByStatusname("G"));
		project.setProjectResourcing(projectStatusLightService.findProjectStatusLightByStatusname("G"));
		project.setProjectStatus(projectStatusLightService.findProjectStatusLightByStatusname("G"));
		
		
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		project.setUser(userRepository.findByUsername(user.getUsername()));

		
		projectService.addProject(project);
	
		return "redirect:/projects/";
	}
	
	@GetMapping("/{username}/")
	public ModelAndView showProjects(@RequestParam(name="pageno", required=false, defaultValue="0") int pageno, @PathVariable String username){
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTS);
		
		mav.addObject("projects", projectService.listPageableProjects(pageno,userRepository.findByUsername(username)));
		
		return mav;
	}
	
	@GetMapping("/project/{id}/")
	public ModelAndView editProject(@RequestParam(name="pageno", required=false, defaultValue="0") int pageno, @PathVariable int id){
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTFORMEDIT);
		
	
		System.out.println("id : "+id);

		mav.addObject("project",projectService.findProjectById(id));
		
		List<ProjectAchievement> achievements = new ArrayList<>(projectService.findProjectById(id).getAchievements());
		mav.addObject("logros",achievements);
		
		List<ProjectNextStep> nextsteps = new ArrayList<>(projectService.findProjectById(id).getNextsteps());
		mav.addObject("nextsteps",nextsteps);
		
		System.out.println(projectService.findProjectById(id).getProjectname());
		System.out.println("id : "+id);
		return mav;
	}
	
	@GetMapping("/project/{id}/rag/")
	public ModelAndView editRAG(@PathVariable int id){
		ModelAndView mav = new ModelAndView(ViewConstant.RAGFORMEDIT);
		
		FormRagModel formRagModel = new FormRagModel();
		
		formRagModel.setId(id);;
		formRagModel.setProjectBenefitsRealisation(projectService.findProjectById(id).getProjectBenefitsRealisation().getId());
		formRagModel.setProjectScope(projectService.findProjectById(id).getProjectScope().getId());
		formRagModel.setProjectBusinessChange(projectService.findProjectById(id).getProjectBusinessChange().getId());
		formRagModel.setProjectDeliveryConfidence(projectService.findProjectById(id).getProjectDeliveryConfidence().getId());
		formRagModel.setProjectDependency(projectService.findProjectById(id).getProjectDependency().getId());
		formRagModel.setProjectGovernance(projectService.findProjectById(id).getProjectGovernance().getId());
		formRagModel.setProjectResourcing(projectService.findProjectById(id).getProjectResourcing().getId());
		formRagModel.setProjectStatus(projectService.findProjectById(id).getProjectStatus().getId());
		
		mav.addObject("formRagModel",formRagModel);
		mav.addObject("project",projectService.findProjectById(id));
		mav.addObject("lights", projectStatusLightService.listProjectStatusLights());

		return mav;
	}
	
 
	@PostMapping("/project/{id}/rag/save/")
	public ModelAndView saveRAG(@PathVariable int id,@ModelAttribute("formRagModel") FormRagModel formRagModel){
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTFORMEDIT);
		
		Project  project = projectService.findProjectById(id);
		
		project.setProjectStatus(projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectStatus()));
		project.setProjectDeliveryConfidence(projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectDeliveryConfidence()));
		project.setProjectGovernance(projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectGovernance()));
		project.setProjectBusinessChange(projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectBusinessChange()));
		project.setProjectBenefitsRealisation(projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectBenefitsRealisation()));
		project.setProjectDependency(projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectDependency()));
		project.setProjectResourcing(projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectResourcing()));
		project.setProjectScope(projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectScope()));
		
		ProjectStatusLight projectStatusLight =projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectStatus());

		projectService.updateProject(project);
		
		mav.addObject("project",projectService.findProjectById(id));
		mav.addObject("lights", projectStatusLightService.listProjectStatusLights());
		mav.addObject("formRagModel", formRagModel);
		
		return mav;
	}
	
	
	//******************************************************************ACHIEVEMENTS
	
	@GetMapping("/project/{id}/achievement/")
	public ModelAndView editAchievement(@PathVariable int id){
		ModelAndView mav = new ModelAndView(ViewConstant.ACHIEVEMENTFORMEDIT);
		FormAchievementModel formAchievementModel = new FormAchievementModel();
		
		mav.addObject("project",projectService.findProjectById(id));
		mav.addObject("formAchievementModel",formAchievementModel);
		
		projectService.findProjectById(id).getAchievements();
		List<ProjectAchievement> achievements = new ArrayList<>(projectService.findProjectById(id).getAchievements());
		mav.addObject("logros",achievements);

		return mav;
	}
	
	@PostMapping("/project/{id}/achievement/save/")
	public String saveAchievement(@PathVariable int id,@ModelAttribute("formAchievementModel") FormAchievementModel formAchievementModel){
		
		Project  project = projectService.findProjectById(id);
		
		ProjectAchievement projectAchievement  = new ProjectAchievement();
	
		
		projectAchievement.setProject(project);
		projectAchievement.setSummaryachievement(formAchievementModel.getSummaryachievement());
		projectAchievement.setTxtachievement(formAchievementModel.getTxtachievement());
		projectAchievement.setWeek(formAchievementModel.getWeek());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString =formAchievementModel.getDateachievement();

        try {

            Date date = formatter.parse(dateInString);
            projectAchievement.setDateachievement(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
        System.out.println("Project     :" + project.getProjectname());
        System.out.println("Achievement :" + projectAchievement.getSummaryachievement());
        
        projectAchievementService.addProjectAchievement(projectAchievement);
		

		
		return "redirect:/projects/project/"+id+"/achievement/";
	}
	
	//******************************************************************END ACHIEVEMENTS

	
	//******************************************************************NEXT STEPS
	
	@GetMapping("/project/{id}/nextstep/")
	public ModelAndView editNextSteps(@PathVariable int id){
		ModelAndView mav = new ModelAndView(ViewConstant.NEXTSTEPSFORMEDIT);
		FormAchievementModel formAchievementModel = new FormAchievementModel();
		
		mav.addObject("project",projectService.findProjectById(id));
		mav.addObject("formAchievementModel",formAchievementModel);
		
		List<ProjectNextStep> nextsteps = new ArrayList<>(projectService.findProjectById(id).getNextsteps());
		mav.addObject("nextsteps",nextsteps);

		return mav;
	}
	
	@PostMapping("/project/{id}/nextstep/save/")
	public String saveNextSteps(@PathVariable int id,@ModelAttribute("formAchievementModel") FormAchievementModel formAchievementModel){
		
		Project  project = projectService.findProjectById(id);
		
		ProjectNextStep projectNextStep  = new ProjectNextStep();
	
		
		projectNextStep.setProject(project);
		projectNextStep.setSummarynextstep(formAchievementModel.getSummaryachievement());
		projectNextStep.setTxtnextstep(formAchievementModel.getTxtachievement());
		projectNextStep.setWeek(formAchievementModel.getWeek());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString =formAchievementModel.getDateachievement();

        try {

            Date date = formatter.parse(dateInString);
            projectNextStep.setDatenextstep(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
      
        
        projectNextStepService.addProjectNextStep(projectNextStep);
		

		
		return "redirect:/projects/project/"+id+"/nextstep/";
	}
	
	//******************************************************************END NEXTSTEPS

}	
	

	


