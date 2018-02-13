package com.pbc.pmtool.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;
import com.pbc.pmtool.entity.ProjectEscalation;
import com.pbc.pmtool.entity.ProjectNextStep;
import com.pbc.pmtool.entity.ProjectProblem;
import com.pbc.pmtool.entity.ProjectStatusLight;
import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.model.FormAchievementModel;
import com.pbc.pmtool.model.FormAssignToProjectModel;
import com.pbc.pmtool.model.FormEscalationModel;
import com.pbc.pmtool.model.FormFinancialModel;
import com.pbc.pmtool.model.FormNextStepModel;
import com.pbc.pmtool.model.FormProblemModel;
import com.pbc.pmtool.model.FormRagModel;
import com.pbc.pmtool.model.FormResetPasswordModel;
import com.pbc.pmtool.model.Response;
import com.pbc.pmtool.service.ProjectAchievementService;
import com.pbc.pmtool.service.ProjectEscalationService;
import com.pbc.pmtool.service.ProjectNextStepService;
import com.pbc.pmtool.service.ProjectProblemService;
import com.pbc.pmtool.service.ProjectService;
import com.pbc.pmtool.service.ProjectStatusLightService;
import com.pbc.pmtool.service.UserService;

@RestController
@RequestMapping("/api/")
public class PmToolRestController {
	
	@Autowired
	@Qualifier("projectProblemServiceImpl")
	private ProjectProblemService projectProblemService;
	
	@Autowired
	@Qualifier("projectAchievementServiceImpl")
	private ProjectAchievementService projectAchievementService;
	
	@Autowired
	@Qualifier("projectNextStepServiceImpl")
	private ProjectNextStepService projectNextStepService;
	
	@Autowired
	@Qualifier("projectEscalationServiceImpl")
	private ProjectEscalationService projectEscalationService;
	
	@Autowired
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired
	@Qualifier("projectStatusLightServiceImpl")
	private ProjectStatusLightService projectStatusLightService;
 
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
	
	@PostMapping("/project/{id}/finance/save")
	public Response SaveFinance(@PathVariable int id, @RequestBody FormFinancialModel formFinancialModel) {
		
		Logger.getGlobal().info("ID: " + id);
		Logger.getGlobal().info("OP: " + formFinancialModel.getOP());
		
		Project project = projectService.findProjectById(id);
		project.setBudgettodate(formFinancialModel.getBudgettodate());
		project.setCertifiedprogress(formFinancialModel.getCertifiedprogress());
		project.setCostestimated(formFinancialModel.getCostestimated());
		project.setEACOP(formFinancialModel.getEACOP());
		project.setInvoiced(formFinancialModel.getInvoiced());
		project.setOP(formFinancialModel.getOP());
		project.setTIC(formFinancialModel.getTIC());
		project.setTVC(formFinancialModel.getTVC());
		project.setVariance(formFinancialModel.getVariance());
		
		projectService.addProject(project);
		
		return new Response("Done", "Done");
		
	}
	
	@PostMapping("/project/{id}/rag/save/")
	public Response saveRAG(@PathVariable int id,@RequestBody FormRagModel formRagModel){
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
		
		return new Response("Done", "Done");
	}
	
	@PostMapping("/project/{id}/escalation/save/")
	public Response saveScalation(@PathVariable int id,@RequestBody FormEscalationModel formEscalationModel){
		
		Project  project = projectService.findProjectById(id);
		ProjectEscalation projectEscalation  = new ProjectEscalation();
		
		projectEscalation.setProject(project);
		projectEscalation.setSummaryescalation(formEscalationModel.getSummaryescalation());
		projectEscalation.setTxtescalation(formEscalationModel.getTxtescalation());
		projectEscalation.setWeek(formEscalationModel.getWeek());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString =formEscalationModel.getDateescalation();
        try {

            Date date = formatter.parse(dateInString);
            projectEscalation.setDateescalation(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
      
        projectEscalationService.addProjectEscalation(projectEscalation);
		
		return new Response("Done", "Done");
	}	
	
	@PostMapping("/project/{id}/problem/save/")
	public Response saveNextSteps(@PathVariable int id,@RequestBody FormProblemModel formProblemModel){
		
		Project  project = projectService.findProjectById(id);
		
		ProjectProblem projectProblem  = new ProjectProblem();
	
		
		projectProblem.setProject(project);
		projectProblem.setSummaryproblem(formProblemModel.getSummaryproblem());
		projectProblem.setTxtproblem(formProblemModel.getTxtproblem());
		projectProblem.setWeek(formProblemModel.getWeek());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString =formProblemModel.getDateproblem();

        try {

            Date date = formatter.parse(dateInString);
            projectProblem.setDateproblem(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
        
        System.out.println(projectProblem.getSummaryproblem());
      
        projectProblemService.addProjectProblem(projectProblem);
		
		return new Response("Done", "Done");
	}	
	
	@PostMapping("/project/{id}/nextstep/save/")
	public Response saveNextSteps(@PathVariable int id,@RequestBody FormNextStepModel formNextStepModel){
		
		Project  project = projectService.findProjectById(id);
		
		ProjectNextStep projectNextStep  = new ProjectNextStep();
	
		
		projectNextStep.setProject(project);
		projectNextStep.setSummarynextstep(formNextStepModel.getSummarynextstep());
		projectNextStep.setTxtnextstep(formNextStepModel.getTxtnextstep());
		projectNextStep.setWeek(formNextStepModel.getWeek());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString =formNextStepModel.getDatenextstep();

        try {

            Date date = formatter.parse(dateInString);
            projectNextStep.setDatenextstep(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
      
        projectNextStepService.addProjectNextStep(projectNextStep);
		
		return new Response("Done", "Done");
	}	
	
	@PostMapping("/project/{id}/achievement/save/")
	public Response saveAchievement(@PathVariable int id,@RequestBody FormAchievementModel formAchievementModel){
		
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
		

		
		return new Response("Done", "Done");
	}
	
	@PostMapping("/resetpassword")
	public Response resetPassword(@Valid @RequestBody FormResetPasswordModel resetPassword, 
			BindingResult bindingResult) {		
		
		//Comprovamos que no hayan errores
		if(!(bindingResult.hasErrors())) {
			//Obtenemos el usuario logeado
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			//Asignamos al modelo de reset password el nombre del usuario
			resetPassword.setUsername(auth.getName());
			
			//Guardamos las credenciales nuevas
			userService.resetPassword(resetPassword);
			
			//Redirigimos
			return new Response("Done", "Done");
		}else {
			//Redirigimos
			return new Response("Fail", "Fail");
		}
	}

}




	