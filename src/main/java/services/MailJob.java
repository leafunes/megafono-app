package services;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import data.Mail;

public class MailJob implements Job{
	
	private String destinatario;
	private String asunto;
	private String mensaje;
	
	private MailSenderService sender;
	
	public MailJob() {
		
		sender = MailSenderService.getCurrent();
		
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		
		destinatario = dataMap.getString("destinatario");
		asunto = dataMap.getString("asunto");
		mensaje = dataMap.getString("mensaje");
		
		sender.send(new Mail(destinatario, asunto, mensaje));
	}

}
