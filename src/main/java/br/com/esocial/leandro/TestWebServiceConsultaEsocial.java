package br.com.esocial.leandro;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.gov.esocial.servicos.empregador.consulta.ConsultaClient;

@DisallowConcurrentExecution
@Component
public class TestWebServiceConsultaEsocial  {
	
	@Autowired
	private LoteDAO loteDAO;
//	@Scheduled(cron = "0 0-59/1 * * * ?")
	public void consultaEsocial() {
		Date dataInicio = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Logger log = LogManager.getLogger(WebServiceConsultaEsocial.class);
		log.info(">>>>>>> Inicio Processamento Consulta de Lote de Eventos <<<<<<<<<<" + dateFormat.format(dataInicio));
		System.out.println(">>>>>>> Inicio Processamento Consulta de Lote de Eventos <<<<<<<<<<" + dateFormat.format(dataInicio));
		ConsultaClient consulta = new ConsultaClient();
		List<LoteVO> listaLotes = loteDAO.consultarLote("6","AR");
		try {

			Properties prop = EsocialUtil.getProp();
			for (LoteVO lote : listaLotes) {
				try {
					System.setProperty("javax.net.ssl.trustStoreType", prop.getProperty("config.cert.trustStoreType"));
					System.setProperty("javax.net.ssl.trustStore", prop.getProperty("config.cert.trustStore"));
					System.setProperty("javax.net.ssl.trustStorePassword", prop.getProperty("config.cert.trustStorePassword"));
					System.setProperty("javax.net.ssl.keyStore", prop.getProperty("config.cert.keyStore"));
					System.setProperty("javax.net.ssl.keyStorePassword", prop.getProperty("config.cert.keyStorePassword"));
					
					lote.setXML_WS_RETORNO(consulta.consultaEsocial(lote.getXML_LOTE()));
					EsocialUtil.tofile(consulta.consultaEsocial(lote.getXML_LOTE()));
					loteDAO.atualizaConsultaEsocial(lote);
					long difStart = System.currentTimeMillis() - dataInicio.getTime();
					if (difStart / 60000 >= Long.parseLong(lote.getCOUNT_CHECK_OPEN_ROW())
							&& lote.getFLG_STATUS().equals("C")) {
						
					} 
					
				} catch (JAXBException e) {
					lote.setRETURN_ERRO(e.getCause().getMessage());
					System.err.println(">>> Erro processamento >>>> " + e.getCause().getMessage());
					e.printStackTrace();
					loteDAO.atualizaLoteEsocial(lote);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			log.info(">>>>>>> Fim Processamento Consulta de Lote de Eventos <<<<<<<<<<");
			System.out.println(">>>>>>> Fim Processamento Consulta de Lote de Eventos <<<<<<<<<<" + dateFormat.format(new Date()));
		}

	}
}
