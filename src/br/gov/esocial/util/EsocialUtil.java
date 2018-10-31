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
