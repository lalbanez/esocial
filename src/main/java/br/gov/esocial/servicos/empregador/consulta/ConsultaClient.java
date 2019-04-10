package br.gov.esocial.servicos.empregador.consulta;

import javax.xml.bind.JAXBException;

import br.com.esocial.leandro.EsocialUtil;
import br.gov.esocial.servicos.empregador.consulta.ConsultarLoteEventos.Consulta;
import br.gov.esocial.servicos.empregador.consulta.ConsultarLoteEventosResponse.ConsultarLoteEventosResult;

public class ConsultaClient {

	public String consultaEsocial(String xml) throws JAXBException {

		EsocialUtil util = new EsocialUtil();
//		util.getConfiguracao();

		ServicoConsultarLoteEventos_Service consultarLoteFactory = new ServicoConsultarLoteEventos_Service();

		ServicoConsultarLoteEventos servicoConsultarLoteEventos = consultarLoteFactory
				.getServicosEmpregadorServicoConsultarLoteEventos();
		Consulta consulta = new Consulta();
		consulta.setAny(util.toDocument(xml).getDocumentElement());
		ConsultarLoteEventosResult result = servicoConsultarLoteEventos.consultarLoteEventos(consulta);

		return util.toObject(result);
	}

}
