package com.mycompany.app;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class XMLWriter {
	private final File motionTraceFile;
	private final Document doc;
	private final Element docRoot;
	
	public XMLWriter() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.newDocument();
		docRoot = doc.createElement("motion-trace");
		doc.appendChild(docRoot);
		
		motionTraceFile = new File("motion-trace-file.xml");
	}
	
	public void updateMotionTrace(Client actor, String action, int quantity) {
		Element activityNode = doc.createElement("activity");
		docRoot.appendChild(activityNode);
		
		String dateTimeFormat = "yyyy-MM-dd HH-mm-ss-SSS";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateTimeFormat);
		Attr dateTimeAttr = doc.createAttribute("dateTime");
		dateTimeAttr.setValue(dtf.format(LocalDateTime.now()));
		activityNode.setAttributeNode(dateTimeAttr);
		
		Element actorTag = doc.createElement("actor");
		actorTag.appendChild(doc.createTextNode(actor.getActorName()));
		activityNode.appendChild(actorTag);
		
		Element actionTag = doc.createElement("action");
		actionTag.appendChild(doc.createTextNode(action));
		activityNode.appendChild(actionTag);
		
		Element quantityTag = doc.createElement("quantity");
		quantityTag.appendChild(doc.createTextNode(String.valueOf(quantity)));
		activityNode.appendChild(quantityTag);
	}
	
	public void updateFile() throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(motionTraceFile);
		
		transformer.transform(source, result);
	}
}
