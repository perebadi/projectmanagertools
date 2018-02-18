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
import org.springframework.context.Phased;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectComment;
import com.pbc.pmtool.entity.ProjectPhase;

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
	
	public void createTemplate(Project project) {
		try {
			// Freemarker : Hello ${name} !
			// Velocity : Hello $name !
			//InputStream in = new FileInputStream(new File("./docs/Platilla.odt"));
			InputStream in = new FileInputStream(new File(System.getProperty("user.dir")+ViewConstant.TEMPLATE_ODT));

			
			System.out.println(System.getProperty("user.dir")+"/docs/green.png");
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

			
			report.setFieldsMetadata(metadata);
			

			// 3) Cargamos el modelo
			IContext context = report.createContext();
			//IImageProvider projectstatus = new ClassPathImageProvider(Report.class, System.getProperty("user.dir")+ViewConstant.GREEN);			
			System.out.println(System.getProperty("user.dir")+ViewConstant.GREENY);
			
			List<ProjectComment> list = new ArrayList<ProjectComment>(project.getComments());
			List<ProjectPhase> phases = new ArrayList<ProjectPhase>(project.getPhases());
			Collections.sort(phases);
			
			
			
			/*IImageProvider projectstatus ;
			 switch (project.getProjectStatus().getStatusname()) {
				case "G":
					 projectstatus = new ClassPathImageProvider( Report.class, "logo.png", true );
					 break;
				case "Y":
					 projectstatus = new ClassPathImageProvider( Report.class, "logo.png", true );
					break;
				case "R":
					 projectstatus = new ClassPathImageProvider( Report.class, "logo.png", true );
					break;
				default:
					 projectstatus = new ClassPathImageProvider( Report.class, "logo.png", true );
					break;
				}*/
			
				context.put("project", project);
				context.put("phases", phases);


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
