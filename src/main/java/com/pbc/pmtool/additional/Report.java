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

import org.apache.velocity.tools.generic.NumberTool;
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
	
	private  void parseOdtToPdf(String file, String namefile) {
		Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
		IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
		try {
			InputStream in = new FileInputStream(new File(file));
			OutputStream out = new FileOutputStream(new File(namefile));
			converter.convert(in, out, options);
			System.out.println("FIN DE LA CREACIÃ“N DEL PDF");
			in.close();
			out.close();
		} catch (Exception e) {
			System.out.println("Error parse to PDF" + e);
		}
	}
	
	public void createTemplate(Project project) {
		try {
			// Freemarker : Hello ${name} !
			// Velocity : Hello $name !
			//InputStream in = new FileInputStream(new File("./docs/Platilla.odt"));
			InputStream in = new FileInputStream(new File(System.getProperty("user.dir")+ViewConstant.TEMPLATE_ODT));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

			FieldsMetadata metadata = new FieldsMetadata();
			
			metadata.addFieldAsList("achievement.summaryachievement");
			metadata.addFieldAsList("achievement.dateachievement");
			metadata.addFieldAsList("achievement.txtachievement");
			
			metadata.addFieldAsList("phase.summaryphase");
			metadata.addFieldAsList("phase.startdate");
			metadata.addFieldAsList("phase.enddate");
			metadata.addFieldAsList("phase.weekdelay");
			metadata.addFieldAsList("phase.newdate");
			metadata.addFieldAsList("phase.progress");
			metadata.addFieldAsList("phase.rag");
			
			metadata.addFieldAsList("nextstep.summarynextstep");
			metadata.addFieldAsList("nextstep.week");
			metadata.addFieldAsList("nextstep.txtnextstep");
			
			metadata.addFieldAsList("problem.summaryproblem");
			metadata.addFieldAsList("problem.dateproblem");
			metadata.addFieldAsList("problem.txtproblem");
			
			metadata.addFieldAsList("escalation.summaryescalation");
			metadata.addFieldAsList("escalation.dateescalation");
			metadata.addFieldAsList("escalation.txtescalation");
			
			metadata.addFieldAsList("comment.createdOn");
			metadata.addFieldAsList("comment.modifiedOn");
			metadata.addFieldAsList("comment.comment");
			
			report.setFieldsMetadata(metadata);
			
			// 3) Cargamos el modelo
			IContext context = report.createContext();
			
			context.put("project", project);
			
			List<ProjectAchievement> achievements = new ArrayList<ProjectAchievement>(project.getAchievements());
			
			Collections.sort(achievements);
			
			context.put("achievement", achievements);
			
			List<ProjectPhase> phases = new ArrayList<ProjectPhase>(project.getPhases());
			
			Collections.sort(phases);
			
			context.put("phase", phases);
			
			List<ProjectNextStep> nextsteps = new ArrayList<ProjectNextStep>(project.getNextsteps());
			
			Collections.sort(nextsteps);
			
			context.put("nextstep", nextsteps);
			
			List<ProjectProblem> problems = new ArrayList<ProjectProblem>(project.getProblems());
			
			Collections.sort(problems);
			
			context.put("problem", problems);
			
			List<ProjectEscalation> escalation = new ArrayList<ProjectEscalation>(project.getEscalations());
			
			Collections.sort(escalation);
			
			context.put("escalation", escalation);
			
			List<ProjectComment> comments = new ArrayList<ProjectComment>(project.getComments());
			
			Collections.sort(comments);
			
			context.put("comment", comments);
			
			context.put("numberTool", new NumberTool());
			
			// 4) Generamos el odt
			OutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"/docs/"+project.getProjectname()+".docx"));
			
			report.process(context, out);
			
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		}
	}

}
