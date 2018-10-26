package br.gov.esocial.util;

import javax.xml.bind.JAXBException;

import br.gov.esocial.servicos.empregador.consulta.ConsultaClient;
import br.gov.esocial.servicos.empregador.envio.EnvioClient;

public class Teste {
	public static void main(String[] args) {
		try {
			ConsultaClient consulta = new ConsultaClient();
			System.out.println(consulta.consultaEsocial(XML_CONSULTA));
			
			EnvioClient envio = new EnvioClient();
			System.out.println(envio.envioEsocial(XML_ENVIO));
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private static final String XML_CONSULTA = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
			+ "<eSocial xmlns= \"http://www.esocial.gov.br/schema/lote/eventos/envio/consulta/retornoProcessamento/v1_0_0\" >\r\n"
			+ "	<consultaLoteEventos>\r\n" + "		<protocoloEnvio>1.2.201809.0000000000004516078</protocoloEnvio>\r\n"
			+ "	</consultaLoteEventos>\r\n" + "</eSocial>";

	private static final String XML_ENVIO = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<eSocial xmlns=\"http://www.esocial.gov.br/schema/lote/eventos/envio/v1_1_1\">\r\n"
			+ "  <envioLoteEventos grupo=\"1\">\r\n" + "    <ideEmpregador>\r\n" + "      <tpInsc>1</tpInsc>\r\n"
			+ "      <nrInsc>09041213</nrInsc>\r\n" + "    </ideEmpregador>\r\n" + "    <ideTransmissor>\r\n"
			+ "      <tpInsc>2</tpInsc>\r\n" + "      <nrInsc>28247358808</nrInsc>\r\n" + "    </ideTransmissor>\r\n"
			+ "    <eventos>\r\n" + "      <evento Id=\"ID1090412130000002018090410324100002\">\r\n" +
			// XML DO EVENTO
			" 	<eSocial xmlns=\"http://www.esocial.gov.br/schema/evt/evtInfoEmpregador/v02_04_02\">\r\n"
			+ "	<evtInfoEmpregador Id=\"ID1090412130000002018090410324100002\">\r\n" + "		<ideEvento>\r\n"
			+ "			<tpAmb>2</tpAmb>\r\n" + "			<procEmi>1</procEmi>\r\n"
			+ "			<verProc>2.4.02 Beta</verProc>\r\n" + "		</ideEvento>\r\n" + "		<ideEmpregador>\r\n"
			+ "			<tpInsc>1</tpInsc>\r\n" + "			<nrInsc>09041213</nrInsc>\r\n"
			+ "		</ideEmpregador>\r\n" + "		<infoEmpregador>\r\n" + "			<inclusao>\r\n"
			+ "				<idePeriodo>\r\n" + "					<iniValid>2018-05</iniValid>\r\n"
			+ "				</idePeriodo>\r\n" + "				<infoCadastro>\r\n"
			+ "					<nmRazao>SÃO PAULO PREVIDÊNCIA</nmRazao>\r\n"
			+ "					<classTrib>85</classTrib>\r\n" + "					<natJurid>1112</natJurid>\r\n"
			+ "					<indCoop>0</indCoop>\r\n" + "					<indConstr>0</indConstr>\r\n"
			+ "					<indDesFolha>0</indDesFolha>\r\n"
			+ "  					<indOptRegEletron>1</indOptRegEletron>\r\n"
			+ "					<indEntEd>N</indEntEd>\r\n" + "					<indEtt>N</indEtt>\r\n"
			+ "					<contato>\r\n" + "						<nmCtt>JOSE ROBERTO DE MORAES</nmCtt>\r\n"
			+ "						<cpfCtt>51907488804</cpfCtt>\r\n"
			+ "						<foneCel>11958465954</foneCel>\r\n" + "					</contato>\r\n"
			+ "					<infoComplementares>\r\n" + "						<situacaoPJ>\r\n"
			+ "							<indSitPJ>0</indSitPJ>\r\n" + "						</situacaoPJ>\r\n"
			+ "					</infoComplementares>\r\n" + "				</infoCadastro>\r\n"
			+ "			</inclusao>\r\n" + "		</infoEmpregador>\r\n" + "	</evtInfoEmpregador>\r\n"
			+ "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><DigestValue>LNB3bvjRR7gOroF3FwfyjbnsHvNFLP6Km8T8Gq4/TXY=</DigestValue></Reference></SignedInfo><SignatureValue>TJZmvPFp3shxyT3rb86J0Cu1Z2WQwGM0WsPyHxViHn+tludo9/dVKJth3wzmg4ARFJM/8uya7T1I\r\n"
			+ "HNL6s4qXHFmK6DucBwKyh1h0AmqdAAQHIT5K8T3caRZ6lR041Q0O/EnwGuGVMyTXmIPEonaFiHS9\r\n"
			+ "OiRbdH0gQPV0fuuPJ+NQRpzzk8aQoO441GmwbRxVowEGvBY5yWccrguZFx8L/6wJXrkE5ksGkL4J\r\n"
			+ "qwxjTZifckk0mq/VQJBUZOzc37R1z0eVdQN38RB21HV2Zncp4eg7xeCvrf11QGPEJiGYvQU1cfus\r\n"
			+ "wcWzGr8VTg6zATOT+YCKP2f/eVUzTtmdT2jYPw==</SignatureValue><KeyInfo><X509Data><X509Certificate>MIIH3jCCBcagAwIBAgIJARpoGfW3H8XcMA0GCSqGSIb3DQEBCwUAMIGCMQswCQYDVQQGEwJCUjET\r\n"
			+ "MBEGA1UEChMKSUNQLUJyYXNpbDE2MDQGA1UECxMtU2VjcmV0YXJpYSBkYSBSZWNlaXRhIEZlZGVy\r\n"
			+ "YWwgZG8gQnJhc2lsIC0gUkZCMSYwJAYDVQQDEx1BQyBJbXByZW5zYSBPZmljaWFsIFNQIFJGQiBH\r\n"
			+ "NDAeFw0xODA4MTAxMzI5MDRaFw0yMTA4MTAxMzI5MDRaMIHQMQswCQYDVQQGEwJCUjETMBEGA1UE\r\n"
			+ "ChMKSUNQLUJyYXNpbDE2MDQGA1UECxMtU2VjcmV0YXJpYSBkYSBSZWNlaXRhIEZlZGVyYWwgZG8g\r\n"
			+ "QnJhc2lsIC0gUkZCMRUwEwYDVQQLEwxSRkIgZS1DUEYgQTMxFDASBgNVBAsTCyhFTSBCUkFOQ08p\r\n"
			+ "MRwwGgYDVQQLExNBUiBJTVBSRU5TQSBPRklDSUFMMSkwJwYDVQQDEyBGQUJJTyBEQSBTSUxWQSBD\r\n"
			+ "VU5IQToyODI0NzM1ODgwODCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAMKsvt4npifT\r\n"
			+ "U4vcpaVDgitE/QGDCjkKsr5n/9Pu2f/dm1b25VZeG6nM31R6l5sKRiE+qzA3pJ7sh183P/Vmd7vb\r\n"
			+ "x3/QmdtcuQb6SjJfMRVSN+D5FCrPo29wLEInHSdE8ThaqCstt2Sb6aa5+14lIKwPkwpeVaDBUIZd\r\n"
			+ "if3p+CssYbmk88OLC5L8a5yTWzHelNrTFiQK6mlpvE77avDHGTKnkoUimn4CpGddazn9WwynqBvQ\r\n"
			+ "+LKVUw1sqhGHwV/iNPXUJnvx6wZIbypq+YPEoMt3HeRFra6n1yBgaHcm/AoxmbJT1xoyOnQTNX+d\r\n"
			+ "p5Mb3BV1XIX5iXBM5E/xsizdSEkCAwEAAaOCAwUwggMBMA4GA1UdDwEB/wQEAwIF4DCBpwYIKwYB\r\n"
			+ "BQUHAQEEgZowgZcwNwYIKwYBBQUHMAGGK2h0dHA6Ly9pby1vY3NwLWljcGJyLmltcHJlbnNhb2Zp\r\n"
			+ "Y2lhbC5jb20uYnIwXAYIKwYBBQUHMAKGUGh0dHA6Ly9pby1jb20taWNwYnIuaW1wcmVuc2FvZmlj\r\n"
			+ "aWFsLmNvbS5ici9yZXBvc2l0b3Jpby9JTUVTUFJGQi9BQ0lNRVNQUkZCRzQucDdiMB8GA1UdIwQY\r\n"
			+ "MBaAFHpU/MydBo954w1Eye7lw7dNTcuiMGIGA1UdIARbMFkwVwYGYEwBAgMQME0wSwYIKwYBBQUH\r\n"
			+ "AgEWP2h0dHA6Ly9pby1jb20taWNwYnIuaW1wcmVuc2FvZmljaWFsLmNvbS5ici9yZXBvc2l0b3Jp\r\n"
			+ "by9JTUVTUFJGQjAJBgNVHRMEAjAAMIH1BgNVHR8Ege0wgeowVqBUoFKGUGh0dHA6Ly9pby1jb20t\r\n"
			+ "aWNwYnIuaW1wcmVuc2FvZmljaWFsLmNvbS5ici9yZXBvc2l0b3Jpby9JTUVTUFJGQi9BQ0lNRVNQ\r\n"
			+ "UkZCRzQuY3JsMEqgSKBGhkRodHRwOi8vd3d3LmRpZ2l0YWx0cnVzdC5jb20uYnIvcmVwb3NpdG9y\r\n"
			+ "aW8vSU1FU1BSRkIvQUNJTUVTUFJGQkc0LmNybDBEoEKgQIY+aHR0cDovL3JlcG9zaXRvcmlvLmlj\r\n"
			+ "cGJyYXNpbC5nb3YuYnIvbGNyL0lNRVNQL0FDSU1FU1BSRkJHNC5jcmwwgZEGA1UdEQSBiTCBhoER\r\n"
			+ "a2Nhc2Fkb0BzcC5nb3YuYnKgOAYFYEwBAwGgLwQtMDIwNzE5NzkyODI0NzM1ODgwODAwMDAwMDAw\r\n"
			+ "MDAwMDAwMDAwMDAwMDAwMDAwoBcGBWBMAQMGoA4EDDAwMDAwMDAwMDAwMKAeBgVgTAEDBaAVBBMw\r\n"
			+ "MDAwMDAwMDAwMDAwMDAwMDAwMCkGA1UdJQQiMCAGCCsGAQUFBwMCBggrBgEFBQcDBAYKKwYBBAGC\r\n"
			+ "NxQCAjANBgkqhkiG9w0BAQsFAAOCAgEAVUb9X7NRt/Z/tjgb5AsiqvIZQtvMmKEBdBSG3gFm7j7b\r\n"
			+ "GW4noxPyw3XIh9dID2imjCIKnXb664pLsfjk6bqvmhsLiPauSBfRiFyHCm1LJ6C2nSRk+JBQbS8t\r\n"
			+ "l8ykx+xlaqInhpgYeLGllzvro3TuUjmvg6YE7sdk5iwuIRN+O9dmHRidrCt1tNVUMDPG/5N2YMCV\r\n"
			+ "rj9Ff5y/Z9SSG9Begx3zFZAbZfQ+3NFOLd6KywTKo9bErPAhxGZ9PhjeQDx8Y7eN9DgfmcD+VNVx\r\n"
			+ "tVxZPy4x6CcKPnC6MOmLSvecFx+WKIZ3FcNEgq7CFYvt8CWuvht8uqFSOM2MOl7to0RpY8W/4KYO\r\n"
			+ "W7x9WUt3m98DVutZK83oSR3OiUnhZj6cQEvff1HQ57afCH68TVT1q7s7A5ylexsDtjiqTa+v47SF\r\n"
			+ "5wd2ulEAd2B/bEMXHgxLvWEIRnKYzEYOL0W5LLL5kWzEyUU6p4J/jdk+nol0kECMk/ayzk23LcI2\r\n"
			+ "Z0IML4vYnjZjQ3Cfto0yp+8qz9RTROLz8zTiObwEwfCjPHUzBA1vyOY9pI/LGVI5Eaplq5MHljnr\r\n"
			+ "7I83FxQvHt0+9TbsdEXrmvDw77qTk3swqbCWn2mOP43NUJaWrsJRgypS5KhhI1f/rwqS004TfKOt\r\n"
			+ "vz9Gp1CxTr/gFv8Ko6jPSpwrrX2NA7s=</X509Certificate></X509Data></KeyInfo></Signature></eSocial>" + "\r\n"
			// XML DO EVENTO
			+ "      </evento>\r\n" + "    </eventos>\r\n" + "  </envioLoteEventos>\r\n" + "</eSocial>";
}
