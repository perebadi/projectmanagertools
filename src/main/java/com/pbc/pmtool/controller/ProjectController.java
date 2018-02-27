package com.pbc.pmtool.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pbc.pmtool.additional.Report;
import com.pbc.pmtool.component.CustomerConverter;
import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Problem;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;
import com.pbc.pmtool.entity.ProjectComment;
import com.pbc.pmtool.entity.ProjectEscalation;
import com.pbc.pmtool.entity.ProjectNextStep;
import com.pbc.pmtool.entity.ProjectPhase;
import com.pbc.pmtool.entity.Risk;
import com.pbc.pmtool.model.FormFinancialModel;
import com.pbc.pmtool.model.FormNewProjectModel;
import com.pbc.pmtool.model.FormRagModel;
import com.pbc.pmtool.repository.UserRepository;
import com.pbc.pmtool.service.CustomerService;
import com.pbc.pmtool.service.ProjectAchievementService;
import com.pbc.pmtool.service.ProjectEscalationService;
import com.pbc.pmtool.service.ProjectNextStepService;
import com.pbc.pmtool.service.ProjectPhaseService;
import com.pbc.pmtool.service.ProjectProblemService;
import com.pbc.pmtool.service.ProjectService;
import com.pbc.pmtool.service.ProjectStatusLightService;
import com.pbc.pmtool.service.UserService;


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
		
	@Autowired
	@Qualifier("customerServiceImpl")
	private CustomerService customerServiceImpl;
	
	@Autowired
	@Qualifier("customerConverter")
	private CustomerConverter customerConverter;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_PMO') or hasRole('ROLE_ADMIN')")
	@GetMapping("/")
	public ModelAndView Welcome() throws IllegalArgumentException, IllegalAccessException{
		ModelAndView mav = new ModelAndView(ViewConstant.WELCOME);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("user : " + user.getUsername());
		
		mav.addObject("sumValuesModel", projectService.getActiveSum(user.getUsername(), 
				SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_PMO"))));
		
		
		mav.addObject("numprojects",projectService.countRecords(userRepository.findByUsername(user.getUsername())));
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_PMO')")
	@GetMapping("/project/{id}/e3t/download")
	public void getE3T(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Project project = projectService.findProjectById(id);
		
		if(project != null) {
			if(project.getE3t() != null) {
				ServletContext context = request.getServletContext();
				
		        // construct the complete absolute path of the file
		        String fullPath = System.getProperty("user.dir")+ViewConstant.E3TFOLDER+project.getE3t();
		        File downloadFile = new File(fullPath);
		        FileInputStream inputStream = new FileInputStream(downloadFile);
		        
		        // get MIME type of the file
		        String mimeType = context.getMimeType(fullPath);
		        if (mimeType == null) {
		            // set to binary type if MIME mapping not found
		            mimeType = "application/octet-stream";
		        }
		 
		        // set content attributes for the response
		        response.setContentType(mimeType);
		        response.setContentLength((int) downloadFile.length());
		 
		        // set headers for the response
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"",
		                downloadFile.getName());
		        response.setHeader(headerKey, headerValue);
		 
		        // get output stream of the response
		        OutputStream outStream = response.getOutputStream();
		 
		        byte[] buffer = new byte[4096];
		        int bytesRead = -1;
		 
		        // write bytes read from the input stream into the output stream
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		 
		        inputStream.close();
		        outStream.close();
			}
		}
 
    }
	
	@PreAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_PMO')")
	@PostMapping("/project/{id}/e3t/upload")
	public String uploadE3T(@PathVariable int id, MultipartFile file) {
		if(file.getSize() > 0) {
			if(projectService.uploadE3T(file, id)) {
				return "redirect:/projects/project/" + id + "/?e3tuploadsucessful";
			}else {
				return "redirect:/projects/project/" + id + "/?e3tuploaderror";
			}
		}else {
			return "redirect:/projects/project/" + id + "/?e3tuploaderror";
		}
	}
	
	@PreAuthorize("hasRole('ROLE_PM')")
	@GetMapping("/createproject")
	public ModelAndView CreateProject(){
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTFORM);
		
		mav.addObject("customers", customerServiceImpl.getAll());
		mav.addObject("pmos", userService.getUsersByRole("ROLE_PMO"));
		
		return mav;		
	}
	
	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@PostMapping("/saveproject")
	public String SaveProject(@ModelAttribute("formNewProjectModel") FormNewProjectModel formNewProjectModel) {
		
		System.out.println(formNewProjectModel.getProjectname());
		Project project = new Project();
		
		project.setPmo(userService.getUser(formNewProjectModel.getPmo()));
		project.setProjectname(formNewProjectModel.getProjectname());
		project.setObjectives(formNewProjectModel.getObjectives());
		project.setWbs(formNewProjectModel.getWbs());
		project.setEACOP(formNewProjectModel.getEACOP());
		project.setInvoiced(formNewProjectModel.getInvoiced());
		project.setTIC(formNewProjectModel.getTIC());
		project.setTVC(formNewProjectModel.getTVC());
		project.setOP(formNewProjectModel.getOP());
		project.setVariance(formNewProjectModel.getVariance());
		project.setBudgettodate(formNewProjectModel.getBudgettodate());
		project.setCertifiedprogress(formNewProjectModel.getCertifiedprogress());
		project.setCostestimated(formNewProjectModel.getCostestimated());
		project.setCustomer(customerConverter.CustomerModel2Customer(
				customerServiceImpl.getById(formNewProjectModel.getCustomer_id())));
		
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
	
	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@GetMapping("/{username}/")
	public ModelAndView showProjects(@RequestParam(name="pageno", required=false, defaultValue="0") int pageno, @PathVariable String username){
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTS);
		
		mav.addObject("users", userService.getActiveUsers());
		mav.addObject("projects", projectService.listPageableProjects(pageno,userRepository.findByUsername(username)));
		mav.addObject("username", sessionuser);
		return mav;
	}
	
	
	
	@PreAuthorize("hasAuthority('ROLE_PMO')")
	@GetMapping("/pmo/{username}/")
	public ModelAndView showPmoProjects(@RequestParam(name="pageno", required=false, defaultValue="0") int pageno, @PathVariable String username){
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTS);
		
		mav.addObject("projects", projectService.listPageablePmoProjects(pageno,userRepository.findByUsername(username)));
		mav.addObject("pms", userService.getUsersByRole("ROLE_PM"));
		return mav;
	}
	
	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@GetMapping({"/project/{id}/print/","/project/{id}/print"})
	public void reportProjects( @PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		Report report = new Report();
		File projectReport = report.createTemplate(projectService.findProjectById(id));
		
		FileInputStream inputStream = new FileInputStream(projectReport);
		ServletContext context = request.getServletContext();
        // get MIME type of the file
        String mimeType = context.getMimeType(projectReport.getName());
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) projectReport.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                projectReport.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
        projectReport.delete();
	}
	
	@PreAuthorize("hasAuthority('ROLE_PM') or hasAuthority('ROLE_PMO')")
	@GetMapping({"/project/{id}/","/project/{id}"})
	public ModelAndView editProject(@RequestParam(name="pageno", required=false, defaultValue="0") int pageno, @PathVariable int id, 
			@RequestParam(name="e3tuploadsucessful", required=false) String e3tuploadsucessful,
			@RequestParam(name="e3tuploaderror", required=false) String e3tuploaderror){
		
		System.out.println("id : "+id);
		ModelAndView mav = new ModelAndView(ViewConstant.PROJECTFORMEDIT);
		
		System.out.println(e3tuploadsucessful);
		
		mav.addObject("e3tuploadsucessful", e3tuploadsucessful);
		mav.addObject("e3tuploaderror", e3tuploaderror);
		
		System.out.println("id : "+id);
		Project project = projectService.findProjectById(id);
		
		mav.addObject("project", project);
		
		List<ProjectAchievement> achievements = new ArrayList<>(project.getAchievements());
		
		Collections.sort(achievements);
		
		mav.addObject("logros",achievements);
		
		List<ProjectNextStep> nextsteps = new ArrayList<>(project.getNextsteps());
		
		Collections.sort(nextsteps);
		
		mav.addObject("nextsteps",nextsteps);
		
		List<Problem> problems = new ArrayList<>(project.getProjectProblems());
		
		Collections.sort(problems);
				
		mav.addObject("problems",problems);
		
		List<Risk> risks = new ArrayList<>(project.getProjectRisks());
		
		Collections.sort(risks);
		
		mav.addObject("risks", risks);
		
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
	
}	
	

	


