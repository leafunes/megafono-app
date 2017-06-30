package services;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import data.AccionPublicitaria;
import data.Campania;
import misc.MailsWaitingForSending;


public class ScheduleService {
	private List<Process> procesos;
	private AccionPublicitariaService apService;
	private Scheduler sch;
	Campania c;
	
	public ScheduleService(Campania c) {
		procesos = new ArrayList<>();
		apService = AccionPublicitariaService.getService();
		this.c = c;

		try {
			sch = StdSchedulerFactory.getDefaultScheduler();
			sch.start();
		}catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void execute(){
			List<AccionPublicitaria> aps = apService.getAllActionsOf(c);
			aps.forEach(r -> procesos.add(new Process(r, sch)));
			procesos.forEach(p -> p.configure());
		
	}


	
	private static class Process{
		private AccionPublicitaria ap;
		private JobDetail job;
		private Trigger trigger;
		private Scheduler sch;
		
		public Process(AccionPublicitaria ap, Scheduler sch) {
			this.ap = ap;
			this.job = createJob();
			this.trigger = createTrigger();
			this.sch = sch;
		}

		public void configure(){
			try {
				sch.scheduleJob(job, trigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		
		private Trigger createTrigger() {
			return TriggerBuilder.newTrigger()
					.withIdentity("Trigger: " + ap.hashCode())
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInMinutes(ap.getPeriodicidad().getMinutes())
							.repeatForever()
							)
					.build();
		}

		private JobDetail createJob() {
			return JobBuilder.newJob(MailsWaitingForSending.class)
					.withIdentity(ap.hashCode() + "")
					.usingJobData("to", ap.getDestinatario())
					.usingJobData("subject", ap.getTag().getNombre())
					.usingJobData("body",ap.getCamp().getDescripcion())
					.build();
		}
	}

	
}
