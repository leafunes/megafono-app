package misc;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import data.Mail;
import services.MailSenderService;

public class MailsWaitingForSending implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		MailSenderService mailSender = new MailSenderService();
		JobDataMap data = arg0.getMergedJobDataMap();
		
		
		String to = data.getString("to");
		String subject = data.getString("subject");
		String body = data.getString("body");
		
		System.out.println(to);
		System.out.println(subject);
		System.out.println(body);
		
		Mail mail = new Mail(to, subject, body);
		mailSender.send(mail);
	}


}
