package br.com.esocial.leandro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Properties;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis.message.MessageElement;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.gov.esocial.servicos.empregador.consulta.ConsultarLoteEventosResponse.ConsultarLoteEventosResult;
import br.gov.esocial.servicos.empregador.envio.EnviarLoteEventosResponse.EnviarLoteEventosResult;

@Component
public class EsocialUtil {

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

	public MessageElement[] convertXMLStringtoMessageElement(String xmlString)
			throws SAXException, IOException, ParserConfigurationException {
		MessageElement[] m = new MessageElement[1];
		Document XMLDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new StringReader(xmlString)));
		Element element = XMLDoc.getDocumentElement();
		m[0] = new MessageElement(element);
		return m;
	}

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/config.properties");
		props.load(file);

		return props;
	}

	public static void tofile(String xml) throws FileNotFoundException {
		try {
			Files.write(Paths.get("my-file.txt"), xml.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void rescheduleCronJob(String newCronExpression, String timeSyncTrigger) {
		try {
			SchedulerFactory shedFact = new StdSchedulerFactory();
			Scheduler scheduler = shedFact.getScheduler();
			TriggerKey triggerKey = new TriggerKey(timeSyncTrigger, "grupo01");
			if(scheduler.checkExists(triggerKey)) {
				CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
				trigger.setCronExpression(newCronExpression);
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (SchedulerException | ParseException e) {
			System.out.println("Erro ao realizar reagendamento do quartz");
			e.printStackTrace();
		}
	}
}
