package services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import data.AccionPublicitaria;
import data.Campania;
import data.Mail;
import data.MensajeCampania;
import data.TipoAccionPublicitaria;

import org.quartz.SimpleTrigger;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


public class ScheduleService {
	
	private static ScheduleService singleton;
	
	private Scheduler scheduler;
	
	private CampaniaService campService;
	private AccionPublicitariaService accionService;
	private long id = 0;
	
	public static ScheduleService getService(){
		if(singleton == null)
			singleton = new ScheduleService();
		return singleton;
	}
	
	public ScheduleService() {
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			
			campService = CampaniaService.getService();
			accionService = AccionPublicitariaService.getService();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addAcciones(Campania camp, List<AccionPublicitaria> acciones){
		
		DateTime end = campService.getEndOf(camp);
		for(AccionPublicitaria a : acciones)if(a.getTipo() == TipoAccionPublicitaria.MAIL){
			
			JobDetail job = newJob(MailJob.class)
				      .withIdentity("mail" + id, "g1")
				      .usingJobData("destinatario",a.getDestinatario())
				      .usingJobData("asunto", "Publicidad By Megafono")
				      .usingJobData("mensaje", camp.getMensaje().getMensaje())
				      .build();
			  
			Trigger trigger = TriggerBuilder.newTrigger()
				      .withIdentity("trigger" + id, "g1")
				      .startAt(camp.getInicio().toDate())
				      .withSchedule(simpleSchedule()
				          .withIntervalInMilliseconds(a.getPeriodicidad().toStandardDuration().getMillis())
				          .repeatForever())
				      .endAt(end.toDate())
				      .build();
			
			try {
				scheduler.scheduleJob(job, trigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
			
			
			id++;
		}
		
	}
	
	public void stop(){
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void setScheduleFor(Campania campania) {

		List<AccionPublicitaria> acciones = accionService.getAllActionsOf(campania);
		
		addAcciones(campania, acciones);
		
	}

	
}
