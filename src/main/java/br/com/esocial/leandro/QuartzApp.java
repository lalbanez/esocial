package br.com.esocial.leandro;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class QuartzApp {

	@Autowired
	private LoteDAO loteDAO;

	@Autowired
	Environment env;

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(100);
	}

	@Bean
	public ValidaReagendamento validaReagendamento() {
		return new ValidaReagendamento();
	}

	public void startSchedulers() {
//		Logger LOGGER = LoggerFactory.getLogger(QuartzApp.class);
//
//		System.out.println(new Date());
//		SchedulerFactory shedFact = new StdSchedulerFactory();
//		List<LoteVO> crontabList = new ArrayList<LoteVO>();
		try {
//			Scheduler scheduler = shedFact.getScheduler();
//			scheduler.start();
//
//			LOGGER.info("Start scheduler Envio");
//			crontabList = loteDAO.consultarCrontab("5");
//			for (LoteVO crontab : crontabList) {
//				if (!crontab.getCRONTAB_FORMAT().isEmpty()) {
//					JobDetail envioLoteJob = JobBuilder.newJob(WebServiceEnvioEsocial.class)
//							.withIdentity("envioLote" + crontab.getID_CTR_PROCESSO() + "JOB", "grupo01").build();
//					Trigger envioLoteTrigger = TriggerBuilder.newTrigger()
//							.withIdentity("envioLote" + crontab.getID_CTR_PROCESSO() + "TRIGGER", "grupo01")
//							.withSchedule(CronScheduleBuilder.cronSchedule(crontab.getCRONTAB_FORMAT())).build();
//					scheduler.scheduleJob(envioLoteJob, envioLoteTrigger);
//				}
//			}
//			LOGGER.info("Start scheduler Consulta");
//			crontabList = loteDAO.consultarCrontab("6");
//			for (LoteVO crontab : crontabList) {
//				if (!crontab.getCRONTAB_FORMAT().isEmpty()) {
//
//					JobDetail consultaLoteJob = JobBuilder.newJob(WebServiceConsultaEsocial.class)
//							.withIdentity("consultaLote" + crontab.getID_CTR_PROCESSO() + "JOB", "grupo01").build();
//					Trigger consultaLoteTrigger = TriggerBuilder.newTrigger()
//							.withIdentity("consultaLote" + crontab.getID_CTR_PROCESSO() + "TRIGGER", "grupo01")
//							.withSchedule(CronScheduleBuilder.cronSchedule(crontab.getCRONTAB_FORMAT())).build();
//					scheduler.scheduleJob(consultaLoteJob, consultaLoteTrigger);
//
//				}
//			}

//			LOGGER.info("Start scheduler de verificação de reagendamento");
//			System.out.println("Start scheduler de verificação de reagendamento");
//			JobDetail reagendamentoLoteJob = JobBuilder.newJob(ValidaReagendamento.class)
//					.withIdentity("validaReagendamento", "grupo01").build();
//			Trigger reagendamentoLoteTrigger = TriggerBuilder.newTrigger()
//					.withIdentity("reagendamentoLoteTRIGGER", "grupo01")
//					.withSchedule(CronScheduleBuilder.cronSchedule("0 */1 * * * ? *")).build();
//			scheduler.scheduleJob(reagendamentoLoteJob, reagendamentoLoteTrigger);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//		taskRegistrar.setScheduler(taskExecutor());
//
//		taskRegistrar.addTriggerTask(new Runnable() {
//
//			@Override
//			public void run() {
//				validaReagendamento().valida();
//			}
//		}, new org.springframework.scheduling.Trigger() {
//
//			@Override
//			public Date nextExecutionTime(TriggerContext triggerContext) {
//				System.out.println("nextExecutionTime" );
//				Calendar nextExecutionTime = new GregorianCalendar();
//				Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
//				nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
//				nextExecutionTime.add(Calendar.MILLISECOND, env.getProperty("myRate", Integer.class));
//				return nextExecutionTime.getTime();
//			}
//		});
//
//	}

}