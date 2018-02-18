package com.pbc.pmtool.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pbc.pmtool.additional.Report;
import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;
import com.pbc.pmtool.entity.ProjectComment;
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
import com.pbc.pmtool.repository.ProjectRepository;
import com.pbc.pmtool.repository.UserRepository;
import com.pbc.pmtool.service.ProjectAchievementService;
import com.pbc.pmtool.service.ProjectEscalationService;
import com.pbc.pmtool.service.ProjectNextStepService;
import com.pbc.pmtool.service.ProjectPhaseService;
import com.pbc.pmtool.service.ProjectProblemService;
import com.pbc.pmtool.service.ProjectService;
import com.pbc.pmtool.service.ProjectStatusLightService;


@Controller
@RequestMapping("/projects")
public class ProjectController {
	
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
		
	
	@GetMapping("/")
	public ModelAndView Welcome() throws IllegalArgumentException, IllegalAccessException{
		ModelAndView mav = new ModelAndView(ViewConstant.WELCOME);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("user : " + user.getUsername());
		
		sessionuser=user.getUsername();
		
		
		mav.addObject("sumValuesModel", projectService.getActiveSum(user.getUsername()));
		
		
		mav.addObject("username", sessionuser);
		mav.addObject("numprojects",projectService.countRecords(userRepository.findByUsername(user.getUsername())));
		return mav;
	}
	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/createproject")
	public ModelAndView CreateProject(){
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTFORM);
		mav.addObject("username", sessionuser);
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

		Project newproject = projectService.addProject(project);
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date kickoffdate = df.parse(formNewProjectModel.getStart());
			Date golivedate = df.parse(formNewProjectModel.getEnd());
			
			projectPhaseKickOff.setSummaryphase("Kick-Off");
			projectPhaseKickOff.setStartdate(kickoffdate);
			projectPhaseKickOff.setEnddate(kickoffdate);
			projectPhaseKickOff.setProject(newproject);
			projectPhaseKickOff.setRag(projectStatusLightService.findProjectStatusLightById(1));
			projectPhaseKickOff.setWeekdelay(0);
			projectPhaseKickOff.setNewdate(kickoffdate);
			projectPhaseKickOff.setProgress(0);

			projectPhaseGoLive.setSummaryphase("Go-Live");
			projectPhaseGoLive.setStartdate(golivedate);
			projectPhaseGoLive.setEnddate(golivedate);
			projectPhaseGoLive.setProject(newproject);
			projectPhaseGoLive.setRag(projectStatusLightService.findProjectStatusLightById(1));
			projectPhaseGoLive.setWeekdelay(0);
			projectPhaseGoLive.setNewdate(golivedate);
			projectPhaseGoLive.setProgress(0);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		projectPhaseService.addProjectPhase(projectPhaseKickOff);
		projectPhaseService.addProjectPhase(projectPhaseGoLive);

	
		
		
		
	
		return "redirect:/projects/";
	}
	
	@GetMapping("/{username}/")
	public ModelAndView showProjects(@RequestParam(name="pageno", required=false, defaultValue="0") int pageno, @PathVariable String username){
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTS);
		
		mav.addObject("projects", projectService.listPageableProjects(pageno,userRepository.findByUsername(username)));
		mav.addObject("username", sessionuser);
		return mav;
	}
	
	@GetMapping("/{username}/report")
	public String reportProjects( @PathVariable String username) throws IOException{
		Report report = new Report();
		report.createTemplate(projectService.findProjectById(9));
		
		String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);
        
		return "redirect:/projects/"+username+"/";


	}
	
	
	@GetMapping("/pmo/{username}/")
	public ModelAndView showPmoProjects(@RequestParam(name="pageno", required=false, defaultValue="0") int pageno, @PathVariable String username){
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTS);
		
		mav.addObject("projects", projectService.listPageablePmoProjects(pageno,userRepository.findByUsername(username)));
		mav.addObject("username", sessionuser);
		return mav;
	}
	
	
	
	
	@GetMapping({"/project/{id}/","/project/{id}"})
	public ModelAndView editProject(@RequestParam(name="pageno", required=false, defaultValue="0") int pageno, @PathVariable int id){
		
		System.out.println("id : "+id);
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTFORMEDIT);
		
		System.out.println("id : "+id);
		Project project = projectService.findProjectById(id);
		
		mav.addObject("project", project);
		
		List<ProjectAchievement> achievements = new ArrayList<>(project.getAchievements());
		
		Collections.sort(achievements);
		
		mav.addObject("logros",achievements);
		
		List<ProjectNextStep> nextsteps = new ArrayList<>(project.getNextsteps());
		
		Collections.sort(nextsteps);
		
		mav.addObject("nextsteps",nextsteps);
		
		List<ProjectProblem> problems = new ArrayList<>(project.getProblems());
		
		Collections.sort(problems);
				
		mav.addObject("problems",problems);
		
		List<ProjectEscalation> escalations = new ArrayList<>(project.getEscalations());
		
		Collections.sort(escalations);
		
		mav.addObject("escalations",escalations);
		
		List<ProjectPhase> phases = new ArrayList<>(project.getPhases());
		
		Collections.sort(phases);
		
		mav.addObject("phases",phases);
		
		List<ProjectComment> comments = new ArrayList<>(project.getComments());
		
		Collections.sort(comments);
		
		mav.addObject("comments", comments);
		
		System.out.println(projectService.findProjectById(id).getProjectname());
		System.out.println("id : "+id);
		
		//*** RAG MODEL ***//
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
		mav.addObject("lights", projectStatusLightService.listProjectStatusLights());
		//*** END RAG MODEL***//
		
		//*** FINANCIAL MODEL***//
		FormFinancialModel formFinancialModel = new FormFinancialModel();
		
		formFinancialModel.setBudgettodate(projectService.findProjectById(id).getBudgettodate());
		formFinancialModel.setCertifiedprogress(projectService.findProjectById(id).getCertifiedprogress());
		formFinancialModel.setCostestimated(projectService.findProjectById(id).getCostestimated());
		formFinancialModel.setEACOP(projectService.findProjectById(id).getEACOP());
		formFinancialModel.setInvoiced(projectService.findProjectById(id).getInvoiced());
		formFinancialModel.setOP(projectService.findProjectById(id).getOP());
		formFinancialModel.setTIC(projectService.findProjectById(id).getTIC());
		formFinancialModel.setTVC(projectService.findProjectById(id).getTVC());
		formFinancialModel.setVariance(projectService.findProjectById(id).getVariance());
		
		mav.addObject("formFinancialModel",formFinancialModel);
		//*** END FINANCIAL MODEL***//
		
		mav.addObject("username", sessionuser);
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
		
		
		mav.addObject("username", sessionuser);

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
		
		mav.addObject("username", sessionuser);

		return mav;
	}	
	//******************************************************************END ACHIEVEMENTS

	//******************************************************************NEXT STEPS
	
	@GetMapping("/project/{id}/nextstep/")
	public ModelAndView editNextSteps(@PathVariable int id){
		ModelAndView mav = new ModelAndView(ViewConstant.NEXTSTEPSFORMEDIT);
		FormNextStepModel formNextStepModel = new FormNextStepModel();
		
		mav.addObject("project",projectService.findProjectById(id));
		mav.addObject("formNextStepModel",formNextStepModel);
		
		List<ProjectNextStep> nextsteps = new ArrayList<>(projectService.findProjectById(id).getNextsteps());
		mav.addObject("nextsteps",nextsteps);
		
		mav.addObject("username", sessionuser);

		return mav;
	}
	//******************************************************************END NEXTSTEPS
	
	
	//******************************************************************PROBLEMS *****************
	
	@GetMapping("/project/{id}/problem/")
	public ModelAndView editProblems(@PathVariable int id){
		ModelAndView mav = new ModelAndView(ViewConstant.PROBLEMFORMEDIT);
		FormProblemModel formProblemModel = new FormProblemModel();
		
		mav.addObject("project",projectService.findProjectById(id));
		mav.addObject("formProblemModel",formProblemModel);
		
		List<ProjectProblem> problems = new ArrayList<>(projectService.findProjectById(id).getProblems());
		mav.addObject("problems",problems);
		
		
		mav.addObject("username", sessionuser);

		return mav;
	}
	//******************************************************************END PROBLEMS
	
	
	
	//******************************************************************ESCALATIONS *****************
	
	@GetMapping("/project/{id}/escalation/")
	public ModelAndView editScalations(@PathVariable int id){
		ModelAndView mav = new ModelAndView(ViewConstant.ESCALATIONFORMEDIT);
		FormEscalationModel formEscalationModel = new FormEscalationModel();
		
		mav.addObject("project",projectService.findProjectById(id));
		mav.addObject("formEscalationModel",formEscalationModel);
		
		List<ProjectEscalation> escalations = new ArrayList<>(projectService.findProjectById(id).getEscalations());
		mav.addObject("escalations",escalations);
		mav.addObject("username", sessionuser);

		
		
		return mav;
	}
	//******************************************************************END ESCALATIONS
	
	
	

	//******************************************************************PHASE *****************
	
	@GetMapping("/project/{id}/phase/")
	public ModelAndView editPhases(@PathVariable int id){
		ModelAndView mav = new ModelAndView(ViewConstant.PHASEFORMEDIT);
		FormPhaseModel formPhaseModel = new FormPhaseModel();
		
		mav.addObject("project",projectService.findProjectById(id));
		mav.addObject("formPhaseModel",formPhaseModel);
		
		List<ProjectPhase> phases = new ArrayList<>(projectService.findProjectById(id).getPhases());
		mav.addObject("phases",phases);
		mav.addObject("lights", projectStatusLightService.listProjectStatusLights());


		mav.addObject("username", sessionuser);
		
		return mav;
	}
	
	@GetMapping("/project/{id}/phase/{idphase}/")
	public ModelAndView editPhase(@PathVariable int id, @PathVariable int idphase){
		ModelAndView mav = new ModelAndView(ViewConstant.PHASEFORMEDIT);
		
		System.out.println("id      : "+ id);
		System.out.println("idphase : "+ idphase);
		
		ProjectPhase projectPhase = projectPhaseService.findProjectPhaseById(idphase);
		FormPhaseModel formPhaseModel = new FormPhaseModel();
		
		System.out.println("progress : "+  projectPhase.getProgress());
		formPhaseModel.setIdphase(idphase);
		formPhaseModel.setProgress( projectPhase.getProgress());
		formPhaseModel.setRag( projectPhase.getRag().getId());
		formPhaseModel.setSummaryphase(projectPhase.getSummaryphase() );
		formPhaseModel.setWeekdelay( projectPhase.getWeekdelay());
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		formPhaseModel.setEnddate(df.format(projectPhase.getEnddate()));
		formPhaseModel.setNewdate( df.format(projectPhase.getNewdate()));
		formPhaseModel.setStartdate(df.format(projectPhase.getStartdate()));
		

		mav.addObject("project",projectService.findProjectById(id));
		mav.addObject("formPhaseModel",formPhaseModel);
		
		List<ProjectPhase> phases = new ArrayList<>(projectService.findProjectById(id).getPhases());
		mav.addObject("phases",phases);
		mav.addObject("lights", projectStatusLightService.listProjectStatusLights());
		
		mav.addObject("username", sessionuser);
		
		return mav;
	}
	//******************************************************************END PHASE
	
	

	//******************************************************************FINANCE *****************
	
	@GetMapping("/project/{id}/finance/edit")
	public ModelAndView editFinance(@PathVariable int id){
		ModelAndView mav = new ModelAndView(ViewConstant.FINANCEFORMEDIT);
		
		FormFinancialModel formFinancialModel = new FormFinancialModel();
		
		formFinancialModel.setBudgettodate(projectService.findProjectById(id).getBudgettodate());
		formFinancialModel.setCertifiedprogress(projectService.findProjectById(id).getCertifiedprogress());
		formFinancialModel.setCostestimated(projectService.findProjectById(id).getCostestimated());
		formFinancialModel.setEACOP(projectService.findProjectById(id).getEACOP());
		formFinancialModel.setInvoiced(projectService.findProjectById(id).getInvoiced());
		formFinancialModel.setOP(projectService.findProjectById(id).getOP());
		formFinancialModel.setTIC(projectService.findProjectById(id).getTIC());
		formFinancialModel.setTVC(projectService.findProjectById(id).getTVC());
		formFinancialModel.setVariance(projectService.findProjectById(id).getVariance());
		
				
		mav.addObject("formFinancialModel",formFinancialModel);

		mav.addObject("project",projectService.findProjectById(id));
		mav.addObject("username", sessionuser);
		
		return mav;
	}
	//******************************************************************END FINANCE

	
}	
	

	


