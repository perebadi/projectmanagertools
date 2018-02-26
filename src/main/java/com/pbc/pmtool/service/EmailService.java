package com.pbc.pmtool.service;

import java.util.List;

public interface EmailService {

	public void sendEmail(String text, String subject, List<String> recipients);
	
}
