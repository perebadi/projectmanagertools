package com.pbc.pmtool.additional;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codehaus.groovy.control.Phases;
import org.hibernate.criterion.PropertyProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.Phased;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;
import com.pbc.pmtool.entity.ProjectComment;
import com.pbc.pmtool.entity.ProjectEscalation;
import com.pbc.pmtool.entity.ProjectNextStep;
import com.pbc.pmtool.entity.ProjectPhase;
import com.pbc.pmtool.entity.ProjectProblem;
import com.pbc.pmtool.repository.UserRepository;
import com.pbc.pmtool.service.ProjectAchievementService;
import com.pbc.pmtool.service.ProjectEscalationService;
import com.pbc.pmtool.service.ProjectNextStepService;
import com.pbc.pmtool.service.ProjectProblemService;

import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.document.images.ClassPathImageProvider;

public class Report {
	
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
	@Qualifier("projectEscalationServiceImpl")
	private ProjectEscalationService projectEscalationService;
	
	public void createTemplate(Project project) {
		try {
			// Freemarker : Hello ${name} !
			// Velocity : Hello $name !
			//InputStream in = new FileInputStream(new File("./docs/Platilla.odt"));
			InputStream in = new FileInputStream(new File(System.getProperty("user.dir")+ViewConstant.TEMPLATE_ODT));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

			// 2) Creamos las filas a pintar
			FieldsMetadata metadata = new FieldsMetadata();
			metadata.addFieldAsList("phases.summaryphase");
			metadata.addFieldAsList("phases.startdate");
			metadata.addFieldAsList("phases.enddate");
			metadata.addFieldAsList("phases.weekdelay");
			metadata.addFieldAsList("phases.newdate");
			metadata.addFieldAsList("phases.progress");
			metadata.addFieldAsList("phases.getRag().getStatusdescription()");
			
			metadata.addFieldAsList("achievements.summaryachievement");
			metadata.addFieldAsList("achievements.dateachievement");
			metadata.addFieldAsList("achievements.txtachievement");
			
			metadata.addFieldAsList("nextsteps.summarynextstep");
			metadata.addFieldAsList("nextsteps.week");
			metadata.addFieldAsList("nextsteps.txtnextstep");
			
			report.setFieldsMetadata(metadata);
			

			// 3) Cargamos el modelo
			IContext context = report.createContext();
			List<ProjectComment> list = new ArrayList<ProjectComment>(project.getComments());
			List<ProjectPhase> phases = new ArrayList<ProjectPhase>(project.getPhases());
			List<ProjectAchievement> achievements = new ArrayList<ProjectAchievement>(project.getAchievements());
			List<ProjectNextStep> nextsteps = new ArrayList<ProjectNextStep>(project.getNextsteps());

			Collections.sort(phases);
			
			context.put("project", project);
			context.put("phases", phases);
			
			//System.out.println("SIZE IN : "+ projectAchievementService.listTop5ProjectAchievements(project).size());
			//List<ProjectAchievement> achievements = projectAchievementService.listTop5ProjectAchievements(project);
			
			
			context.put("achievements", achievements);
			context.put("nextsteps", nextsteps);


			for (ProjectComment projectComment : list) {
				System.out.println(projectComment.getId());
				System.out.println(projectComment.getComment());
			}
				
			
			
			System.out.println("Antes FileOut");

			// 4) Generamos el odt
			OutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+ViewConstant.OUT));
			
			System.out.println("Antes report");

			report.process(context, out);

			System.out.println("FIN DE LA CREACIÓN DEL ODT");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		}
	}

}
