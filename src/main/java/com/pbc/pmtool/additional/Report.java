package com.pbc.pmtool.additional;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Project;

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
	
	public void createTemplate() {
		try {
			// Freemarker : Hello ${name} !
			// Velocity : Hello $name !
			InputStream in = new FileInputStream(new File(ViewConstant.TEMPLATE_ODT));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

			// 2) Creamos las filas a pintar
			FieldsMetadata metadata = new FieldsMetadata();
			metadata.addFieldAsList("persons.Name");
			metadata.addFieldAsList("persons.LastName");
			report.setFieldsMetadata(metadata);

			// 3) Cargamos el modelo
			IContext context = report.createContext();
			Project project = new Project();

			context.put("project", project);
			context.put("persons", project.getAchievements());

			// 4) Generamos el odt
			OutputStream out = new FileOutputStream(new File(ViewConstant.OUT));
			report.process(context, out);

			System.out.println("FIN DE LA CREACIÓN DEL ODT");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		}
	}

	private static void parseOdtToPdf(String file) {
		Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
		IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
		try {
			InputStream in = new FileInputStream(new File(file));
			OutputStream out = new FileOutputStream(new File(ViewConstant.OUTPDF));
			converter.convert(in, out, options);
			System.out.println("FIN DE LA CREACIÓN DEL PDF");
		} catch (Exception e) {
			System.out.println("Error parse to PDF" + e);
		}
	}
	

}
