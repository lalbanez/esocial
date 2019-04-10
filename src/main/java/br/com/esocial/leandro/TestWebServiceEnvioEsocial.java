package br.com.esocial.leandro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.esocial.servicos.empregador.envio.EnvioClient;

@DisallowConcurrentExecution
@Component
public class TestWebServiceEnvioEsocial {
	
	@Autowired
	private LoteDAO loteDAO;
	
//	@Scheduled(cron = "0 0-59/1 * * * ?")
	public void enviaEsocial() {
		Date dataInicio = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Logger log = LogManager.getLogger(WebServiceEnvioEsocial.class);
		log.info(">>>>>>> Inicio Processamento Teste Envio de Lote de Eventos <<<<<<<<<<" + dateFormat.format(dataInicio));
		System.out.println(">>>>>>> Inicio Processamento Teste Envio de Lote de Eventos <<<<<<<<<<" + dateFormat.format(new Date()));
		EnvioClient envio = new EnvioClient();
//		List<LoteVO> listaLotes = loteDAO.consultarLote("5","A");
		try {
			Properties prop = EsocialUtil.getProp();
//				for (LoteVO lote : listaLotes) {
					try {
						System.setProperty("javax.net.ssl.trustStoreType", prop.getProperty("config.cert.trustStoreType"));
						System.setProperty("javax.net.ssl.trustStore", prop.getProperty("config.cert.trustStore"));
						System.setProperty("javax.net.ssl.trustStorePassword", prop.getProperty("config.cert.trustStorePassword"));
						System.setProperty("javax.net.ssl.keyStore", prop.getProperty("config.cert.keyStore"));
						System.setProperty("javax.net.ssl.keyStorePassword", prop.getProperty("config.cert.keyStorePassword"));

//						lote.setXML_WS_RETORNO(envio.envioEsocial(lote.getXML_LOTE()));
						String retorno = envio.envioEsocial(XML_ENVIO);
						System.out.println(retorno);
//						loteDAO.atualizaLoteEsocial(lote);
//						long difStart = System.currentTimeMillis() - dataInicio.getTime();
//						if (difStart / 60000 >= Long.parseLong(lote.getCOUNT_CHECK_OPEN_ROW())
//								&& lote.getFLG_STATUS().equals("C")) {
//							break;
//						}
					} catch (JAXBException e) {
//						lote.setRETURN_ERRO(e.getCause().getMessage());
						log.info(">>> Erro processamento >>>> " + e.getCause().getMessage());
						e.printStackTrace();
//						loteDAO.atualizaLoteEsocial(lote);
					}
//				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			log.info(">>>>>>> Fim Processamento Envio de Lote de Eventos <<<<<<<<<<");
			System.out.println(">>>>>>> Fim Processamento Envio de Lote de Eventos <<<<<<<<<<" + dateFormat.format(new Date()));
		}

	}
	
	public static final String XML_ENVIO = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<eSocial xmlns=\"http://www.esocial.gov.br/schema/lote/eventos/envio/v1_1_1\">\r\n" + 
			"	<envioLoteEventos grupo=\"1\">\r\n" + 
			"		<ideEmpregador>\r\n" + 
			"			<tpInsc>1</tpInsc>\r\n" + 
			"			<nrInsc>09041213</nrInsc>\r\n" + 
			"		</ideEmpregador>\r\n" + 
			"		<ideTransmissor>\r\n" + 
			"			<tpInsc>2</tpInsc>\r\n" + 
			"			<nrInsc>28247358808</nrInsc>\r\n" + 
			"		</ideTransmissor>\r\n" + 
			"		<eventos>\r\n" + 
			"			<evento Id=\"ID1090412130000002018090410324100001\">\r\n" + 
			"			 	<eSocial xmlns=\"http://www.esocial.gov.br/schema/evt/evtInfoEmpregador/v02_04_02\"> \r\n" + 
			"				<evtInfoEmpregador Id=\"ID1090412130000002018090410324100001\">\r\n" + 
			"					<ideEvento>\r\n" + 
			"						<tpAmb>2</tpAmb>\r\n" + 
			"						<procEmi>1</procEmi>\r\n" + 
			"						<verProc>2.4.02</verProc>\r\n" + 
			"					</ideEvento> \r\n" + 
			"					<ideEmpregador>\r\n" + 
			"						<tpInsc>1</tpInsc>\r\n" + 
			"						<nrInsc>09041213</nrInsc>\r\n" + 
			"					</ideEmpregador> \r\n" + 
			"					<infoEmpregador>\r\n" + 
			"						<inclusao>\r\n" + 
			"							<idePeriodo>\r\n" + 
			"								<iniValid>2018-05</iniValid> \r\n" + 
			"							</idePeriodo> \r\n" + 
			"							<infoCadastro>\r\n" + 
			"								<nmRazao>SÃO PAULO PREVIDÊNCIA</nmRazao>\r\n" + 
			"								<classTrib>85</classTrib>\r\n" + 
			"								<natJurid>1112</natJurid>\r\n" + 
			"								<indCoop>0</indCoop>\r\n" + 
			"								<indConstr>0</indConstr>\r\n" + 
			"								<indDesFolha>0</indDesFolha>\r\n" + 
			"			  					<indOptRegEletron>1</indOptRegEletron>\r\n" + 
			"								<indEntEd>N</indEntEd>\r\n" + 
			"								<indEtt>N</indEtt>\r\n" + 
			"								<contato> \r\n" + 
			"									<nmCtt>JOSE ROBERTO DE MORAES</nmCtt> \r\n" + 
			"									<cpfCtt>51907488804</cpfCtt> \r\n" + 
			"									<foneCel>11958465954</foneCel>\r\n" + 
			"								</contato> \r\n" + 
			"								<infoOP>\r\n" + 
			"									<nrSiafi>123456</nrSiafi> \r\n" + 
			"								</infoOP> \r\n" + 
			"								<infoComplementares>\r\n" + 
			"									<situacaoPJ>\r\n" + 
			"										<indSitPJ>0</indSitPJ>\r\n" + 
			"									</situacaoPJ>\r\n" + 
			"								</infoComplementares>\r\n" + 
			"							</infoCadastro>\r\n" + 
			"						</inclusao>\r\n" + 
			"					</infoEmpregador>\r\n" + 
			"				</evtInfoEmpregador>\r\n" + 
			"					<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">\r\n" + 
			"						<SignedInfo>\r\n" + 
			"							<CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\r\n" + 
			"							<SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/>\r\n" + 
			"							<Reference URI=\"\">\r\n" + 
			"								<Transforms>\r\n" + 
			"									<Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\r\n" + 
			"									<Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\r\n" + 
			"								</Transforms>\r\n" + 
			"								<DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\r\n" + 
			"								<DigestValue>BZC+lGc7tvTGydeWUWQBF8TkC4CsTSR2eJsU+9j7RHc=</DigestValue>\r\n" + 
			"							</Reference>\r\n" + 
			"						</SignedInfo>\r\n" + 
			"						<SignatureValue>\r\n" + 
			"							Jzl0XTCAmfHnj73xkgFWAMrrT8+j2GOIR4YI+wEU+wh6agt+DWY0v3ZzpVw5tGyPhYFqbx5f1onn \r\n" + 
			"							uXr5yyvoyGLJ1vBO6y6D8PQH9Uc8+62rf6W1rAQGf/ym5On66GIq8SHpKW5MaaIJ811pvjPKBd5B \r\n" + 
			"							48MwrhWtSW5R0+E3Cch8GhzuNa4nq07ivDZEP69XEAFGhDzloziC1wGn10OgFGLwphOaoDk9GciO \r\n" + 
			"							js9twuaQnrHNAEXQOB8syIJDQWIv76hWAbhkij/QpW8cYTxBJbldHRSvr87HCSgpLnwIqzYQuIAR \r\n" + 
			"							4dezakjGZ7M3x80Nih6SnRBNA99M5CMQ4uGThQ==\r\n" + 
			"						</SignatureValue>\r\n" + 
			"						<KeyInfo>\r\n" + 
			"							<X509Data>\r\n" + 
			"								<X509Certificate>\r\n" + 
			"									MIIH3jCCBcagAwIBAgIJARpoGfW3H8XcMA0GCSqGSIb3DQEBCwUAMIGCMQswCQYDVQQGEwJCUjET \r\n" + 
			"									MBEGA1UEChMKSUNQLUJyYXNpbDE2MDQGA1UECxMtU2VjcmV0YXJpYSBkYSBSZWNlaXRhIEZlZGVy \r\n" + 
			"									YWwgZG8gQnJhc2lsIC0gUkZCMSYwJAYDVQQDEx1BQyBJbXByZW5zYSBPZmljaWFsIFNQIFJGQiBH \r\n" + 
			"									NDAeFw0xODA4MTAxMzI5MDRaFw0yMTA4MTAxMzI5MDRaMIHQMQswCQYDVQQGEwJCUjETMBEGA1UE \r\n" + 
			"									ChMKSUNQLUJyYXNpbDE2MDQGA1UECxMtU2VjcmV0YXJpYSBkYSBSZWNlaXRhIEZlZGVyYWwgZG8g \r\n" + 
			"									QnJhc2lsIC0gUkZCMRUwEwYDVQQLEwxSRkIgZS1DUEYgQTMxFDASBgNVBAsTCyhFTSBCUkFOQ08p \r\n" + 
			"									MRwwGgYDVQQLExNBUiBJTVBSRU5TQSBPRklDSUFMMSkwJwYDVQQDEyBGQUJJTyBEQSBTSUxWQSBD \r\n" + 
			"									VU5IQToyODI0NzM1ODgwODCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAMKsvt4npifT \r\n" + 
			"									U4vcpaVDgitE/QGDCjkKsr5n/9Pu2f/dm1b25VZeG6nM31R6l5sKRiE+qzA3pJ7sh183P/Vmd7vb \r\n" + 
			"									x3/QmdtcuQb6SjJfMRVSN+D5FCrPo29wLEInHSdE8ThaqCstt2Sb6aa5+14lIKwPkwpeVaDBUIZd \r\n" + 
			"									if3p+CssYbmk88OLC5L8a5yTWzHelNrTFiQK6mlpvE77avDHGTKnkoUimn4CpGddazn9WwynqBvQ \r\n" + 
			"									+LKVUw1sqhGHwV/iNPXUJnvx6wZIbypq+YPEoMt3HeRFra6n1yBgaHcm/AoxmbJT1xoyOnQTNX+d \r\n" + 
			"									p5Mb3BV1XIX5iXBM5E/xsizdSEkCAwEAAaOCAwUwggMBMA4GA1UdDwEB/wQEAwIF4DCBpwYIKwYB \r\n" + 
			"									BQUHAQEEgZowgZcwNwYIKwYBBQUHMAGGK2h0dHA6Ly9pby1vY3NwLWljcGJyLmltcHJlbnNhb2Zp \r\n" + 
			"									Y2lhbC5jb20uYnIwXAYIKwYBBQUHMAKGUGh0dHA6Ly9pby1jb20taWNwYnIuaW1wcmVuc2FvZmlj \r\n" + 
			"									aWFsLmNvbS5ici9yZXBvc2l0b3Jpby9JTUVTUFJGQi9BQ0lNRVNQUkZCRzQucDdiMB8GA1UdIwQY \r\n" + 
			"									MBaAFHpU/MydBo954w1Eye7lw7dNTcuiMGIGA1UdIARbMFkwVwYGYEwBAgMQME0wSwYIKwYBBQUH \r\n" + 
			"									AgEWP2h0dHA6Ly9pby1jb20taWNwYnIuaW1wcmVuc2FvZmljaWFsLmNvbS5ici9yZXBvc2l0b3Jp \r\n" + 
			"									by9JTUVTUFJGQjAJBgNVHRMEAjAAMIH1BgNVHR8Ege0wgeowVqBUoFKGUGh0dHA6Ly9pby1jb20t \r\n" + 
			"									aWNwYnIuaW1wcmVuc2FvZmljaWFsLmNvbS5ici9yZXBvc2l0b3Jpby9JTUVTUFJGQi9BQ0lNRVNQ \r\n" + 
			"									UkZCRzQuY3JsMEqgSKBGhkRodHRwOi8vd3d3LmRpZ2l0YWx0cnVzdC5jb20uYnIvcmVwb3NpdG9y \r\n" + 
			"									aW8vSU1FU1BSRkIvQUNJTUVTUFJGQkc0LmNybDBEoEKgQIY+aHR0cDovL3JlcG9zaXRvcmlvLmlj \r\n" + 
			"									cGJyYXNpbC5nb3YuYnIvbGNyL0lNRVNQL0FDSU1FU1BSRkJHNC5jcmwwgZEGA1UdEQSBiTCBhoER \r\n" + 
			"									a2Nhc2Fkb0BzcC5nb3YuYnKgOAYFYEwBAwGgLwQtMDIwNzE5NzkyODI0NzM1ODgwODAwMDAwMDAw \r\n" + 
			"									MDAwMDAwMDAwMDAwMDAwMDAwoBcGBWBMAQMGoA4EDDAwMDAwMDAwMDAwMKAeBgVgTAEDBaAVBBMw \r\n" + 
			"									MDAwMDAwMDAwMDAwMDAwMDAwMCkGA1UdJQQiMCAGCCsGAQUFBwMCBggrBgEFBQcDBAYKKwYBBAGC \r\n" + 
			"									NxQCAjANBgkqhkiG9w0BAQsFAAOCAgEAVUb9X7NRt/Z/tjgb5AsiqvIZQtvMmKEBdBSG3gFm7j7b \r\n" + 
			"									GW4noxPyw3XIh9dID2imjCIKnXb664pLsfjk6bqvmhsLiPauSBfRiFyHCm1LJ6C2nSRk+JBQbS8t \r\n" + 
			"									l8ykx+xlaqInhpgYeLGllzvro3TuUjmvg6YE7sdk5iwuIRN+O9dmHRidrCt1tNVUMDPG/5N2YMCV \r\n" + 
			"									rj9Ff5y/Z9SSG9Begx3zFZAbZfQ+3NFOLd6KywTKo9bErPAhxGZ9PhjeQDx8Y7eN9DgfmcD+VNVx \r\n" + 
			"									tVxZPy4x6CcKPnC6MOmLSvecFx+WKIZ3FcNEgq7CFYvt8CWuvht8uqFSOM2MOl7to0RpY8W/4KYO \r\n" + 
			"									W7x9WUt3m98DVutZK83oSR3OiUnhZj6cQEvff1HQ57afCH68TVT1q7s7A5ylexsDtjiqTa+v47SF \r\n" + 
			"									5wd2ulEAd2B/bEMXHgxLvWEIRnKYzEYOL0W5LLL5kWzEyUU6p4J/jdk+nol0kECMk/ayzk23LcI2 \r\n" + 
			"									Z0IML4vYnjZjQ3Cfto0yp+8qz9RTROLz8zTiObwEwfCjPHUzBA1vyOY9pI/LGVI5Eaplq5MHljnr \r\n" + 
			"									7I83FxQvHt0+9TbsdEXrmvDw77qTk3swqbCWn2mOP43NUJaWrsJRgypS5KhhI1f/rwqS004TfKOt \r\n" + 
			"									vz9Gp1CxTr/gFv8Ko6jPSpwrrX2NA7s=\r\n" + 
			"								</X509Certificate>\r\n" + 
			"							</X509Data>\r\n" + 
			"						</KeyInfo>\r\n" + 
			"					</Signature>\r\n" + 
			"				</eSocial>\r\n" + 
			"			</evento>\r\n" + 
			"		</eventos>\r\n" + 
			"	</envioLoteEventos>\r\n" + 
			"</eSocial>";

}
