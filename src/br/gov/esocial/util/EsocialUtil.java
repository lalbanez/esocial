package br.gov.esocial.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.gov.esocial.servicos.empregador.consulta.ConsultarLoteEventosResponse.ConsultarLoteEventosResult;
import br.gov.esocial.servicos.empregador.envio.EnviarLoteEventosResponse.EnviarLoteEventosResult;

public class EsocialUtil {
	public void getConfiguracao() {

//		System.clearProperty("javax.net.ssl.keyStore");
//		System.clearProperty("javax.net.ssl.keyStorePassword");
//		System.clearProperty("javax.net.ssl.trustStore");
//
//		System.setProperty("javax.net.ssl.trustStoreType", "JKS");
//		System.setProperty("javax.net.ssl.trustStore", new File("certificados/jssecacerts").getAbsolutePath());
//		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
//
//		System.setProperty("javax.net.ssl.keyStore", new File("certificados/spprev_a1.pfx").getAbsolutePath());
//		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		
		
		System.getProperty("javax.net.ssl.trustStoreType");
	    System.getProperty("javax.net.ssl.trustStore");
	    System.getProperty("javax.net.ssl.trustStorePassword");
	    
	    System.getProperty("javax.net.ssl.keyStore");
	    System.getProperty("javax.net.ssl.keyStorePassword");
	    
	    RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
	    List<String> arguments = runtimeMxBean.getInputArguments();
	    for (String a : arguments) {
	      System.out.println(a);
	    }
	}

	public Document toDocument(String xml) {
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			return db.parse(is);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public String toObject(ConsultarLoteEventosResult result) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXB.marshal(result, sw);
		return sw.toString();
	}
	
	public String toObject(EnviarLoteEventosResult result) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXB.marshal(result, sw);
		return sw.toString();
	}
}
