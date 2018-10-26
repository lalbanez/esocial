package br.gov.esocial.servicos.empregador.envio;

import javax.xml.bind.JAXBException;

import br.gov.esocial.servicos.empregador.envio.EnviarLoteEventos.LoteEventos;
import br.gov.esocial.servicos.empregador.envio.EnviarLoteEventosResponse.EnviarLoteEventosResult;
import br.gov.esocial.util.EsocialUtil;

public class EnvioClient {

	public String envioEsocial(String xml) throws JAXBException {

		ServicoEnviarLoteEventos_Service enviarLoteFactory = new ServicoEnviarLoteEventos_Service();

		EsocialUtil util = new EsocialUtil();

		ServicoEnviarLoteEventos servicoEnviarLoteEventos = enviarLoteFactory.getWsEnviarLoteEventos();
		LoteEventos loteEventos = new LoteEventos();
		loteEventos.setAny(util.toDocument(xml).getDocumentElement());
		EnviarLoteEventosResult result = servicoEnviarLoteEventos.enviarLoteEventos(loteEventos);
		
		return util.toObject(result);
	}
}
