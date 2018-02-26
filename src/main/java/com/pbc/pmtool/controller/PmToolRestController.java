package com.pbc.pmtool.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.pbc.pmtool.component.FormCustomerAddConverter;
import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Comment;
import com.pbc.pmtool.entity.Problem;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;
import com.pbc.pmtool.entity.ProjectComment;
import com.pbc.pmtool.entity.ProjectEscalation;
import com.pbc.pmtool.entity.ProjectNextStep;
import com.pbc.pmtool.entity.ProjectPhase;
import com.pbc.pmtool.entity.ProjectStatusLight;
import com.pbc.pmtool.entity.Risk;
import com.pbc.pmtool.entity.Task;
import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.model.CustomerModel;
import com.pbc.pmtool.model.FormAchievementModel;
import com.pbc.pmtool.model.FormAssignToProjectModel;
import com.pbc.pmtool.model.FormChangeProjectPM;
import com.pbc.pmtool.model.FormCommentModel;
import com.pbc.pmtool.model.FormCreateTaskModel;
import com.pbc.pmtool.model.FormCustomerAddModel;
import com.pbc.pmtool.model.FormEscalationModel;
import com.pbc.pmtool.model.FormFinancialModel;
import com.pbc.pmtool.model.FormNextStepModel;
import com.pbc.pmtool.model.FormPhaseModel;
import com.pbc.pmtool.model.FormProblemModel;
import com.pbc.pmtool.model.FormRagModel;
import com.pbc.pmtool.model.FormResetPasswordModel;
import com.pbc.pmtool.model.FormRiskModel;
import com.pbc.pmtool.model.FormSaveBacklogModel;
import com.pbc.pmtool.model.FormSaveTaskModel;
import com.pbc.pmtool.model.FormSprint;
import com.pbc.pmtool.model.Response;
import com.pbc.pmtool.repository.ProjectRepository;
import com.pbc.pmtool.repository.SprintRepository;
import com.pbc.pmtool.repository.UserRepository;
import com.pbc.pmtool.service.CustomerService;
import com.pbc.pmtool.service.EmailService;
import com.pbc.pmtool.service.ProblemService;
import com.pbc.pmtool.service.ProjectAchievementService;
import com.pbc.pmtool.service.ProjectCommentService;
import com.pbc.pmtool.service.ProjectEscalationService;
import com.pbc.pmtool.service.ProjectNextStepService;
import com.pbc.pmtool.service.ProjectPhaseService;
import com.pbc.pmtool.service.ProjectProblemService;
import com.pbc.pmtool.service.ProjectService;
import com.pbc.pmtool.service.ProjectStatusLightService;
import com.pbc.pmtool.service.ProjectTaskService;
import com.pbc.pmtool.service.RiskService;
import com.pbc.pmtool.service.SprintService;
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
	@Qualifier("projectPhaseServiceImpl")
	private ProjectPhaseService projectPhaseService;

	@Autowired
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;

	@Autowired
	@Qualifier("projectStatusLightServiceImpl")
	private ProjectStatusLightService projectStatusLightService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Autowired
	@Qualifier("projectRepository")
	private ProjectRepository projectRepository;

	@Autowired
	@Qualifier("projectCommentServiceImpl")
	private ProjectCommentService projectCommentServiceImpl;

	@Autowired
	@Qualifier("projectTaskServiceImpl")
	private ProjectTaskService projectTaskServiceImpl;

	@Autowired
	@Qualifier("formCustomerAddConverter")
	private FormCustomerAddConverter formCustomerAddConverter;

	@Autowired
	@Qualifier("customerServiceImpl")
	private CustomerService customerServiceImpl;

	@Autowired
	@Qualifier("problemServiceImpl")
	private ProblemService problemServiceImpl;
	
	@Autowired
	@Qualifier("riskServiceImpl")
	private RiskService riskServiceImpl;
	
	@Autowired
	@Qualifier("emailService")
	private EmailService emailServiceImpl;
	
	@Autowired
	@Qualifier("sprintServiceImpl")
	private SprintService sprintServiceImpl;
	
	@Autowired
	@Qualifier("sprintRepository")
	private SprintRepository sprintRepository;

	@PreAuthorize("hasAuthority('ROLE_PMO')")
	@PostMapping(value = "/project/{id}/changepm")
	public Response updatePMProject(@PathVariable int id, @Valid @RequestBody FormChangeProjectPM formChangeProjectPM, BindingResult bindingResult) {
		if(!(bindingResult.hasErrors())) {
			projectService.changePMProject(id, formChangeProjectPM);
			
			return new Response("Done", "Done");
		}else {
			return new Response("Error", "Error");
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping(value = "/projects/all")
	public List<Project> getProject() {
		return projectService.listProjects();
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping(value = "/assign/")
	public Response addToProject(@RequestBody FormAssignToProjectModel formAssignToProjectModel) {

		Response res = null;
		User user = userService.getUser(formAssignToProjectModel.getUsername());

		if (user.getAssignedsMap().get(formAssignToProjectModel.getProjectid()) == null) {
			Project project = projectService.findProjectById(formAssignToProjectModel.getProjectid());
			List<Project> projects = user.getAssigneds();
			projects.add(project);
			user.setAssigneds(projects);
			userRepository.save(user);

			res = new Response("Done", "Done");
		} else {
			res = new Response("AlreadyInProj", "AlreadyInProj");
		}

		return res;
	}

	@PreAuthorize("hasAuthority('ROLE_PM')")
	@PostMapping(value = "/project/{id}/createsprint")
	public Response createSprint(@PathVariable int id, @Valid @RequestBody FormSprint sprintAddModel, 
			BindingResult bindingResult) {
		if(!(bindingResult.hasErrors())) {
			FormSprint newSprint = sprintServiceImpl.add(sprintAddModel, id);
			
			return new Response("Done", newSprint.getId());
		}else {
			return new Response("Error", "Error");
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_PM')")
	@PostMapping(value = "/createcustomer/")
	public Response createCustomer(@Valid @RequestBody FormCustomerAddModel customerAddModel,
			BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
			CustomerModel newCustomer = customerServiceImpl
					.save(formCustomerAddConverter.FormCustomerAdd2CustomerModel(customerAddModel));

			return new Response("Done", newCustomer.getId());
		} else {
			return new Response("Error", "Error");
		}
	}

	/**
	 * Guarda una tarea en backlog
	 * 
	 * @param formSaveBacklogModel
	 * @param bindingResult
	 * @return Response
	 */
	@PreAuthorize("hasAuthority('ROLE_PM')")
	@PostMapping(value = "/savebacklog/")
	public Response saveBacklog(@Valid @RequestBody FormSaveBacklogModel formSaveBacklogModel,
			BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
			Task task = projectTaskServiceImpl.findProjectTaskById(formSaveBacklogModel.getTaskId());

			if (!(formSaveBacklogModel.getComment().equals(""))) {
				Comment comment = new Comment();

				comment.setDatecomment(new Date());
				comment.setDetail(formSaveBacklogModel.getComment());
				comment.setTask(task);

				task.getComments().add(comment);
			}

			if (!(formSaveBacklogModel.getUsername().equals("nobody"))) {				
				if(task.getUser() == null) {
					Project project = projectService.findProjectById(task.getProject().getId());
					
					String emailText = "You have a new task in a project.";
					
					emailText += "<br/><br/>";
					emailText += "<strong>Project: </strong> " + project.getProjectname() + " <br/>";
					emailText += "<strong>Objectives: </strong> " + project.getObjectives() + " <br/>";
					emailText += "<strong>Customer: </strong> " + project.getCustomer().getCustomer() + " <br/>";
					emailText += "<strong>Task: </strong> " + task.getSummary() + " <br/>";
					emailText += "<strong>Task details: </strong> " + task.getDetails() + " <br/>";
					
					List<String> recipients = new ArrayList<String>();
					
					recipients.add(formSaveBacklogModel.getUsername());
					
					emailServiceImpl.sendEmail(emailText, "PMTOOL: You have a new task in a project.", recipients);
				}
				
				task.setUser(userService.getUser(formSaveBacklogModel.getUsername()));

				task.setStatus(2);
				
			} else if (formSaveBacklogModel.getUsername().equals("nobody")) {
				task.setUser(null);
			}

			projectTaskServiceImpl.addProjectTask(task);

			return new Response("Done", "Done");
		} else {
			return new Response("Error", "Error");
		}
	}

	/**
	 * Guarda los cambios en una tarea
	 * 
	 * @param formSaveTaskModel
	 * @param bindingResult
	 * @return Response
	 */
	@PreAuthorize("hasAuthority('ROLE_PM')")
	@PostMapping(value = "/savetask/")
	public Response saveTask(@Valid @RequestBody FormSaveTaskModel formSaveTaskModel, BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
			Task task = projectTaskServiceImpl.findProjectTaskById(formSaveTaskModel.getTaskId());

			if (formSaveTaskModel.getTime() > 0) {
				task.setHours(task.getHours() + (formSaveTaskModel.getTime() * formSaveTaskModel.getUnit()));
			}

			if (!(formSaveTaskModel.getComment().equals(""))) {
				Comment comment = new Comment();

				comment.setDatecomment(new Date());
				comment.setDetail(formSaveTaskModel.getComment());
				comment.setTask(task);

				task.getComments().add(comment);
			}

			projectTaskServiceImpl.addProjectTask(task);

			return new Response("Done", "Done");
		} else {
			return new Response("Error", "Error");
		}
	}

	/**
	 * Crea una tarea
	 * 
	 * @param formCreateTaskModel
	 * @param bindingResult
	 * @return Response
	 */
	@PreAuthorize("hasAuthority('ROLE_PM')")
	@PostMapping(value = "/createtask/")
	public Response createTask(@Valid @RequestBody FormCreateTaskModel formCreateTaskModel,
			BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
			Task newTask = new Task();
			Project project = projectService.findProjectById(formCreateTaskModel.getProjectid());
			
			newTask.setSummary(formCreateTaskModel.getSummary());
			newTask.setDetails(formCreateTaskModel.getDetails());
			newTask.setDatecreation(new Date());
			newTask.setDatestatus(new Date());
			newTask.setProject(project);
			newTask.setSprint(sprintRepository.findById(formCreateTaskModel.getSprint()));			
			
			if (!(formCreateTaskModel.getUsername().equals("nobody"))) {
				newTask.setUser(userService.getUser(formCreateTaskModel.getUsername()));
				
				String emailText = "You have a new task in a project.";
				
				emailText += "<br/><br/>";
				emailText += "<strong>Project: </strong> " + project.getProjectname() + " <br/>";
				emailText += "<strong>Objectives: </strong> " + project.getObjectives() + " <br/>";
				emailText += "<strong>Customer: </strong> " + project.getCustomer().getCustomer() + " <br/>";
				emailText += "<strong>Task: </strong> " + formCreateTaskModel.getSummary() + " <br/>";
				emailText += "<strong>Task details: </strong> " + formCreateTaskModel.getDetails() + " <br/>";
				
				List<String> recipients = new ArrayList<String>();
				
				recipients.add(formCreateTaskModel.getUsername());
				
				emailServiceImpl.sendEmail(emailText, "PMTOOL: You have a new task in a project.", recipients);
				
			}

			newTask.setStatus(1);

			newTask.setEstimatedunit(formCreateTaskModel.getUnit());

			newTask.setEstimatedtime(formCreateTaskModel.getTime());

			newTask.setEstimatedhours(formCreateTaskModel.getUnit() * formCreateTaskModel.getTime());
			newTask.setHours(0);

			projectTaskServiceImpl.addProjectTask(newTask);
			
			return new Response("Done", "Done");
		} else {
			return new Response("Error", "Error");
		}
	}

	/**
	 * Guarda un comentario sobre un proyecto
	 * 
	 * @param id
	 * @param formCommentModel
	 * @return Response
	 */
	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@PostMapping("/project/{id}/comment/save")
	public Response saveComment(@PathVariable int id, @Valid @RequestBody FormCommentModel formCommentModel,
			BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
			ProjectComment projectComment = projectCommentServiceImpl.findCommentById(formCommentModel.getIdcomment());

			if (formCommentModel.getIdcomment() == 0) {
				projectComment = new ProjectComment();

				// Nuevo comentario
				projectComment.setComment(formCommentModel.getComment());
				projectComment.setProject(projectService.findProjectById(id));
				projectComment
						.setPm(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
				projectComment.setCreatedOn(new Date());
				projectComment.setTags(formCommentModel.getTags());
			} else {
				// Actualización del comentario
				if (projectComment.getPm().getUsername()
						.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
					projectComment.setComment(formCommentModel.getComment());
					projectComment.setTags(formCommentModel.getTags());
					projectComment.setModifiedOn(new Date());
				} else {
					return new Response("Error", "Error");
				}
			}

			projectCommentServiceImpl.addProjectComment(projectComment);

			return new Response("Done", "Done");
		} else {
			return new Response("Error", "Error");
		}
	}

	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@PostMapping("/project/{id}/phase/save/")
	public Response savePhase(@PathVariable int id, @Valid @RequestBody FormPhaseModel formPhaseModel,
			BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
			Project project = projectService.findProjectById(id);
			ProjectPhase projectPhase = new ProjectPhase();

			projectPhase.setProject(project);
			projectPhase.setSummaryphase(formPhaseModel.getSummaryphase());
			projectPhase.setWeekdelay(formPhaseModel.getWeekdelay());
			projectPhase.setProgress(formPhaseModel.getProgress());
			projectPhase.setRag(projectStatusLightService.findProjectStatusLightById(formPhaseModel.getRag()));

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateInString;
			Date sdate;
			try {

				dateInString = formPhaseModel.getStartdate();
				sdate = formatter.parse(dateInString);
				projectPhase.setStartdate(sdate);

				dateInString = formPhaseModel.getEnddate();
				sdate = formatter.parse(dateInString);
				projectPhase.setEnddate(sdate);

				dateInString = formPhaseModel.getNewdate();
				sdate = formatter.parse(dateInString);
				projectPhase.setNewdate(sdate);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			projectPhase.setId(formPhaseModel.getIdphase());

			projectPhaseService.addProjectPhase(projectPhase);

			return new Response("Done", "Done");
		} else {
			return new Response("Error", "Error");
		}
	}

	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@PostMapping("/project/{id}/finance/save")
	public Response SaveFinance(@PathVariable int id, @Valid @RequestBody FormFinancialModel formFinancialModel,
			BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
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
		} else {
			return new Response("Error", "Error");
		}
	}

	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@PostMapping("/project/{id}/rag/save/")
	public Response saveRAG(@PathVariable int id, @Valid @RequestBody FormRagModel formRagModel,
			BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
			ModelAndView mav = new ModelAndView(ViewConstant.PROJECTFORMEDIT);

			Project project = projectService.findProjectById(id);

			project.setProjectStatus(
					projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectStatus()));
			project.setProjectDeliveryConfidence(
					projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectDeliveryConfidence()));
			project.setProjectGovernance(
					projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectGovernance()));
			project.setProjectBusinessChange(
					projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectBusinessChange()));
			project.setProjectBenefitsRealisation(
					projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectBenefitsRealisation()));
			project.setProjectDependency(
					projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectDependency()));
			project.setProjectResourcing(
					projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectResourcing()));
			project.setProjectScope(
					projectStatusLightService.findProjectStatusLightById(formRagModel.getProjectScope()));

			ProjectStatusLight projectStatusLight = projectStatusLightService
					.findProjectStatusLightById(formRagModel.getProjectStatus());

			projectService.updateProject(project);

			mav.addObject("project", projectService.findProjectById(id));
			mav.addObject("lights", projectStatusLightService.listProjectStatusLights());
			mav.addObject("formRagModel", formRagModel);

			return new Response("Done", "Done");
		} else {
			return new Response("Error", "Error");
		}
	}

	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@PostMapping("/project/{id}/escalation/save/")
	public Response saveScalation(@PathVariable int id, @Valid @RequestBody FormEscalationModel formEscalationModel,
			BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
			Project project = projectService.findProjectById(id);
			ProjectEscalation projectEscalation = new ProjectEscalation();

			projectEscalation.setProject(project);
			projectEscalation.setSummaryescalation(formEscalationModel.getSummaryescalation());
			projectEscalation.setTxtescalation(formEscalationModel.getTxtescalation());
			projectEscalation.setWeek(formEscalationModel.getWeek());
			projectEscalation.setId(formEscalationModel.getIdescalation());

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateInString = formEscalationModel.getDateescalation();
			try {

				Date date = formatter.parse(dateInString);
				projectEscalation.setDateescalation(date);

			} catch (ParseException e) {
				e.printStackTrace();
			}

			projectEscalationService.addProjectEscalation(projectEscalation);

			return new Response("Done", "Done");
		} else {
			return new Response("Error", "Error");
		}
	}

	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@PostMapping("/project/{id}/risk/save/")
	public Response saveRisk(@PathVariable int id, @Valid @RequestBody FormRiskModel formRiskModel, 
			BindingResult bindingResult) {
		
		if(!(bindingResult.hasErrors())) {
			Project project = projectService.findProjectById(id);
	
			Risk projectRisk = new Risk();
			
			projectRisk.setProject(project);
			projectRisk.setSummaryproblem(formRiskModel.getSummaryproblem());
			projectRisk.setTxtproblem(formRiskModel.getTxtproblem());
			projectRisk.setWeek(formRiskModel.getWeek());
			projectRisk.setId(formRiskModel.getIdproblem());
			projectRisk.setDateproblem(formRiskModel.getDateproblem());
			projectRisk.setStatus(formRiskModel.getStatus());
			projectRisk.setResponsable(formRiskModel.getResponsable());
			projectRisk.setType(formRiskModel.getType());
			projectRisk.setImpact(formRiskModel.getImpact());
			projectRisk.setProbability(formRiskModel.getProbability());
			projectRisk.setStrategy(formRiskModel.getStrategy());
			projectRisk.setDateclose(formRiskModel.getDateclose());
			projectRisk.setActions(formRiskModel.getActions());
			
			projectProblemService.addProjectProblem(projectRisk);
			
			return new Response("Done", "Done");
		}else {
			return new Response("Error", "Error");
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@GetMapping("/project/{id}/risk/{risk}/")
	public Response getRisk(@PathVariable int id, @PathVariable int risk){
		FormRiskModel riskModel = riskServiceImpl.getRisk(risk, id);
		
		if(riskModel == null) {
			riskModel = new FormRiskModel();
		}
		
		Gson json = new Gson();
		
		return new Response("Done", json.toJson(riskModel));
	}
	
	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@GetMapping("/project/{id}/problem/{problem}/")
	public Response getProblem(@PathVariable int id, @PathVariable int problem) {
		FormProblemModel problemModel = problemServiceImpl.getProblem(problem, id);
		
		if(problemModel == null) {
			problemModel = new FormProblemModel();
		}
		
		Gson json = new Gson();
		
		return new Response("Done", json.toJson(problemModel));
	}
	
	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@PostMapping("/project/{id}/problem/save/")
	public Response saveProblem(@PathVariable int id, @Valid @RequestBody FormProblemModel formProblemModel, 
			BindingResult bindingResult) {

		if(!(bindingResult.hasErrors())) {
			Project project = projectService.findProjectById(id);
	
			Problem projectProblem = new Problem();
	
			projectProblem.setProject(project);
			projectProblem.setSummaryproblem(formProblemModel.getSummaryproblem());
			projectProblem.setTxtproblem(formProblemModel.getTxtproblem());
			projectProblem.setWeek(formProblemModel.getWeek());
			projectProblem.setId(formProblemModel.getIdproblem());
			projectProblem.setDateproblem(formProblemModel.getDateproblem());
			projectProblem.setStatus(formProblemModel.getStatus());
			projectProblem.setResponsable(formProblemModel.getResponsable());
			projectProblem.setType(formProblemModel.getType());
			projectProblem.setImpact(formProblemModel.getImpact());
			projectProblem.setEstimatedclosingdate(formProblemModel.getEstimatedclosingdate());
			projectProblem.setDateclose(formProblemModel.getDateclose());
			projectProblem.setActions(formProblemModel.getActions());
			
			System.out.println(projectProblem.getSummaryproblem());
	
			projectProblemService.addProjectProblem(projectProblem);
	
			return new Response("Done", "Done");
		}else {
			return new Response("Error", "Error");
		}

	}

	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@PostMapping("/project/{id}/nextstep/save/")
	public Response saveNextSteps(@PathVariable int id, @Valid @RequestBody FormNextStepModel formNextStepModel,
			BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
			Project project = projectService.findProjectById(id);

			ProjectNextStep projectNextStep = new ProjectNextStep();

			projectNextStep.setProject(project);
			projectNextStep.setSummarynextstep(formNextStepModel.getSummarynextstep());
			projectNextStep.setTxtnextstep(formNextStepModel.getTxtnextstep());
			projectNextStep.setWeek(formNextStepModel.getWeek());
			projectNextStep.setId(formNextStepModel.getIdnextstep());

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateInString = formNextStepModel.getDatenextstep();

			try {

				Date date = formatter.parse(dateInString);
				projectNextStep.setDatenextstep(date);

			} catch (ParseException e) {
				e.printStackTrace();
			}

			projectNextStepService.addProjectNextStep(projectNextStep);

			return new Response("Done", "Done");
		} else {
			return new Response("Error", "Error");
		}
	}

	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@PostMapping("/project/{id}/achievement/save/")
	public Response saveAchievement(@PathVariable int id, @Valid @RequestBody FormAchievementModel formAchievementModel,
			BindingResult bindingResult) {
		if (!(bindingResult.hasErrors())) {
			Project project = projectService.findProjectById(id);

			ProjectAchievement projectAchievement = new ProjectAchievement();

			projectAchievement.setProject(project);
			projectAchievement.setSummaryachievement(formAchievementModel.getSummaryachievement());
			projectAchievement.setTxtachievement(formAchievementModel.getTxtachievement());
			projectAchievement.setWeek(formAchievementModel.getWeek());
			projectAchievement.setId(formAchievementModel.getIdachievement());

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateInString = formAchievementModel.getDateachievement();

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
		} else {
			return new Response("Error", "Error");
		}
	}

	@PostMapping("/resetpassword")
	public Response resetPassword(@Valid @RequestBody FormResetPasswordModel resetPassword,
			BindingResult bindingResult) {

		// Comprovamos que no hayan errores
		if (!(bindingResult.hasErrors())) {
			// Obtenemos el usuario logeado
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			// Asignamos al modelo de reset password el nombre del usuario
			resetPassword.setUsername(auth.getName());

			// Guardamos las credenciales nuevas
			userService.resetPassword(resetPassword);

			// Redirigimos
			return new Response("Done", "Done");
		} else {
			// Redirigimos
			return new Response("Fail", "Fail");
		}
	}

}
