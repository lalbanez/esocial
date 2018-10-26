package br.gov.esocial.util;
import java.rmi.RemoteException;

import br.gov.esocial.servicos.empregador.consulta.ws.ConsultaClientProxy;

public class TesteClient {
	private static final String XML_CONSULTA = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
			+ "<eSocial xmlns= \"http://www.esocial.gov.br/schema/lote/eventos/envio/consulta/retornoProcessamento/v1_0_0\" >\r\n"
			+ "	<consultaLoteEventos>\r\n" + "		<protocoloEnvio>1.2.201809.0000000000004516078</protocoloEnvio>\r\n"
			+ "	</consultaLoteEventos>\r\n" + "</eSocial>";

	public static void main(String[] args) throws RemoteException {
		ConsultaClientProxy proxy = new ConsultaClientProxy();
		long tmpInicio = System.currentTimeMillis();
		proxy.setEndpoint("http://localhost:8085/EnvioConsultaEsocial/services/ConsultaClient");
		System.out.println(proxy.consultaEsocial(XML_CONSULTA));
		long tmpFim = System.currentTimeMillis();
		System.out.println(tmpFim - tmpInicio);
	}
}
