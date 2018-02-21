package com.pbc.pmtool.threads;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailThread implements Runnable {

	private String text;
	private String subject;
	private String recipient;
	
	public SendEmailThread(String text, String subject, String recipient) {
		super();
		this.text = text;
		this.subject = subject;
		this.recipient = recipient;
	}

	@Override
	public void run() {
		//Enviamos un email con la nueva pwd generada
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.live.com");  
	    props.put("mail.smtp.user", "josep_hpe@outlook.com");
	    props.put("mail.smtp.clave", "Dxc20182018");    
	    props.put("mail.smtp.auth", "true");    
	    props.put("mail.smtp.starttls.enable", "true"); 
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress("josep_hpe@outlook.com"));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
	        message.setSubject(subject);
	        message.setText(text);
	        
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.live.com", "josep_hpe@outlook.com", "Dxc20182018");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();
	    }
	}
	
}
