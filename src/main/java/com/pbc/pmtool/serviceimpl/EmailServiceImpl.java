package com.pbc.pmtool.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.service.EmailService;
import com.pbc.pmtool.threads.SendEmailThread;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
    private ApplicationContext applicationContext;
	
    @Autowired
    private TaskExecutor taskExecutor;

	@Override
	public void sendEmail(String text, String subject, List<String> recipients) {
		taskExecutor.execute(new SendEmailThread(text, subject, recipients));
	}
	
}
