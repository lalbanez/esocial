package br.com.esocial.leandro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaReagendamento {

	@Autowired
	private LoteDAO loteDAO;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	public void valida() {
		System.out.println(">>>>>>> Reagendamento de Lote de Eventos <<<<<<<<<< " + dateFormat.format(new Date()));
		List<LoteVO> crontabList = loteDAO.consultarCrontab("5");
		for (LoteVO crontab : crontabList) {
//			if (crontab != null)
//				EsocialUtil.rescheduleCronJob(crontab.getCRONTAB_FORMAT(),
//						"envioLote" + crontab.getID_CTR_PROCESSO() + "TRIGGER");
		}

		crontabList = loteDAO.consultarCrontab("6");
		for (LoteVO crontab : crontabList) {
//			if (crontab != null)
//				EsocialUtil.rescheduleCronJob(crontab.getCRONTAB_FORMAT(),
//						"consultaLote" + crontab.getID_CTR_PROCESSO() + "TRIGGER");
		}
	}

}
