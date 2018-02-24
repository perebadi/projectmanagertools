package com.pbc.pmtool.additional;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.velocity.tools.generic.NumberTool;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Problem;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;
import com.pbc.pmtool.entity.ProjectComment;
import com.pbc.pmtool.entity.ProjectEscalation;
import com.pbc.pmtool.entity.ProjectNextStep;
import com.pbc.pmtool.entity.ProjectPhase;
import com.pbc.pmtool.entity.ProjectProblem;
import com.pbc.pmtool.entity.Risk;

import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class Report {
	
	private  void parseOdtToPdf(String file, String namefile) {
		Options options = Options.getFrom(DocumentKind.DOCX).to(ConverterTypeTo.PDF);
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
	
	public File createTemplate(Project project) {
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
			
			metadata.addFieldAsList("risks.summaryproblem");
			metadata.addFieldAsList("risks.txtproblem");
			metadata.addFieldAsList("risks.dateproblem");
			metadata.addFieldAsList("risks.week");
			metadata.addFieldAsList("risks.status");
			metadata.addFieldAsList("risks.responsable");
			metadata.addFieldAsList("risks.impact");
			metadata.addFieldAsList("risks.type");
			metadata.addFieldAsList("risks.actions");
			metadata.addFieldAsList("risks.dateclose");
			metadata.addFieldAsList("risks.probability");
			metadata.addFieldAsList("risks.strategy");
			
			metadata.addFieldAsList("problems.summaryproblem");
			metadata.addFieldAsList("problems.txtproblem");
			metadata.addFieldAsList("problems.dateproblem");
			metadata.addFieldAsList("problems.week");
			metadata.addFieldAsList("problems.status");
			metadata.addFieldAsList("problems.responsable");
			metadata.addFieldAsList("problems.impact");
			metadata.addFieldAsList("problems.type");
			metadata.addFieldAsList("problems.actions");
			metadata.addFieldAsList("problems.dateclose");
			metadata.addFieldAsList("problems.estimatedclosingdate");
			
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
			
			List<Risk> risks = new ArrayList<Risk>();
			List<Problem> problems = new ArrayList<Problem>();
			
			for(ProjectProblem problem : project.getProblems()) {
				if(problem instanceof Risk) {
					risks.add((Risk) problem);
				}else {
					problems.add((Problem) problem);
				}
			}
			
			Collections.sort(risks);
			
			context.put("risks", risks);
			
			Collections.sort(problems);
			
			context.put("problems", problems);
			
			List<ProjectEscalation> escalation = new ArrayList<ProjectEscalation>(project.getEscalations());
			
			Collections.sort(escalation);
			
			context.put("escalation", escalation);
			
			List<ProjectComment> comments = new ArrayList<ProjectComment>(project.getComments());
			
			Collections.sort(comments);
			
			context.put("comment", comments);
			
			context.put("numberTool", new NumberTool());
			
			// 4) Generamos el odt
			Options options = Options.getFrom(DocumentKind.DOCX).to(ConverterTypeTo.PDF);
			File tempFile = File.createTempFile("projectReport", ".pdf");
			OutputStream out = new FileOutputStream(tempFile);
			//OutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"/docs/"+project.getProjectname()+".pdf"));
			
			//report.process(context, out);
			report.convert(context, options, out);
			
			out.close();
			in.close();
			
			return tempFile;
			
			//parseOdtToPdf(System.getProperty("user.dir")+"/docs/"+project.getProjectname()+".docx", System.getProperty("user.dir")+"/docs/"+project.getProjectname()+".pdf");
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		} catch (XDocReportException e) {
			e.printStackTrace();
			
			return null;
		}
	}

}
