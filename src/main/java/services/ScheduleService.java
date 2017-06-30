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

import daos.iface.DAOAccionPublicitaria;
import daos.iface.DAOCampania;
import daos.impl.DAOAccionPublicitariaNeodatis;
import daos.impl.DAOCampaniaNeodatis;
import data.AccionPublicitaria;
import data.Campania;
import data.Tag;
import misc.MailsWaitingForSending;


public class ScheduleService extends Thread {
	private List<Process> procesos;
	
	DAOCampania daoc = new DAOCampaniaNeodatis();
	DAOAccionPublicitaria daoap = new DAOAccionPublicitariaNeodatis();
	private Scheduler sch;
	
	public ScheduleService() {
		procesos = new ArrayList<>();

		try {
			sch = StdSchedulerFactory.getDefaultScheduler();
			sch.start();
		}catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
			List<AccionPublicitaria> aps = getAllAccionesPublicitarias();
			aps.forEach(r -> procesos.add(new Process(r, sch)));
			procesos.forEach(p -> p.configure());
		
	}

	private List<AccionPublicitaria> getAllAccionesPublicitarias(){
		List<AccionPublicitaria> accionesTotales = new ArrayList<>();
		
		List<Campania> activas = daoc.getAllActiveCampanias();
		
		for (Campania c : activas){
			List<Tag> tags = c.getTags();
			for(Tag t : tags)
				accionesTotales.addAll(daoap.getAllActionsOf(t));
			accionesTotales.addAll(daoap.getAllActionsOf(c));
		}
		
		return accionesTotales;
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
