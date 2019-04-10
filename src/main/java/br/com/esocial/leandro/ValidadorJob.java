package br.com.esocial.leandro;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class ValidadorJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// Simula valida��es
		
		for (int i = 0; i < 100000000; i++) {
			System.out.println(arg0.getFireInstanceId() + " --- " + i);
			System.out.println("Validando dados duplicados no banco. At " + new Date());
			System.out.println("Deletando registros com mais de 10 dias sem uso. At " + new Date());
		}

	}

}