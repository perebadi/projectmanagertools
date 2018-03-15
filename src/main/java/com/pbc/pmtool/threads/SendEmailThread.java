package com.pbc.pmtool.threads;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailThread implements Runnable {

	private String text;
	private String subject;
	private List<String> recipients;
	
	public SendEmailThread(String text, String subject, List<String> recipients) {
		super();
		this.text = text;
		this.subject = subject;
		this.recipients = recipients;
	}

	@Override
	public void run() {
		if(recipients.size() > 0) {
			
			ResourceBundle prop = ResourceBundle.getBundle("email");
			
			//Enviamos un email con la nueva pwd generada
		    Properties props = System.getProperties();
		    props.put("mail.smtp.host", prop.getString("smtp"));  
		    props.put("mail.smtp.user", prop.getString("email_from"));
		    props.put("mail.smtp.clave", prop.getString("email_from_pwd"));    
		    props.put("mail.smtp.auth", prop.getObject("smtp_auth"));    
		    props.put("mail.smtp.starttls.enable", prop.getObject("smtp_starttls")); 
		    props.put("mail.smtp.port", prop.getString("smtp_port"));
	
		    Session session = Session.getDefaultInstance(props);
		    MimeMessage message = new MimeMessage(session);
	
		    try {
		        message.setFrom(new InternetAddress(prop.getString("email_from")));
		        
		        for(String recipient : recipients) {
		        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		        }	
		        	
		        message.setSubject(subject);
		        message.setContent(text, "text/html; charset=utf-8");
		        
		        Transport transport = session.getTransport("smtp");
		        transport.connect(prop.getString("smtp"), prop.getString("email_from"), prop.getString("email_from_pwd"));
		        transport.sendMessage(message, message.getAllRecipients());
		        transport.close();
		    }
		    catch (MessagingException me) {
		        me.printStackTrace();
		    }
		}
	}
	
}
