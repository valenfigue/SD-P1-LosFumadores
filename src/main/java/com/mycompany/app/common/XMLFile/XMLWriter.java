package com.mycompany.app.common.XMLFile;

import com.mycompany.app.common.Client;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class XMLWriter {
	private final File motionTraceFile;
	private Document doc;
	private Element docRoot;
	private Node motionTraceNode;
	private static boolean newFile = true;
	private final DocumentBuilder builder;
	private static final String xmlFormatFilePath = "src/main/java/com/mycompany/app/common/XMLFile/xml-format.xslt";
	private static final String motionTraceFileName = "motion-trace-file.xml";
	
	public XMLWriter() throws ParserConfigurationException, IOException, SAXException {
		motionTraceFile = new File(motionTraceFileName);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		
		if (!motionTraceFile.exists()) {
			doc = builder.newDocument();
			docRoot = doc.createElement("motion-trace");
			doc.appendChild(docRoot);
		} else {
			InputStream motionTraceIS = new FileInputStream(motionTraceFileName);
			doc = builder.parse(motionTraceIS);
			motionTraceNode = doc.getFirstChild();
			newFile = false;
		}
		getDoc();
	}
	
	private void getDoc() throws IOException, SAXException {
		String documentElement = "motion-trace";
		if (!motionTraceFile.exists()) {
			doc = builder.newDocument();
			docRoot = doc.createElement(documentElement);
			doc.appendChild(docRoot);
		} else {
			doc = builder.parse(motionTraceFile);
			motionTraceNode = doc.getFirstChild();
			newFile = false;
		}
	}
	
	public synchronized void updateMotionTrace(Client actor, String action, int quantity) throws IOException, SAXException, TransformerException {
		Element activityNode = doc.createElement("activity");
		
		if (newFile) {
			docRoot.appendChild(activityNode);
		} else {
			motionTraceNode.appendChild(activityNode);
		}
		
		String dateTimeFormat = "yyyy/MM/dd HH:mm:ss.SSS";
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
		
		updateFile();
	}
	
	private synchronized void updateFile() throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		StreamResult result = new StreamResult(motionTraceFile);
		if (newFile) {
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Pretty print
			DOMSource source = new DOMSource(doc);
			
			transformer.transform(source, result);
		} else { // This section adds pretty print to the motion-trace XML file without blank lines.
			File xmlFormatFile = new File(xmlFormatFilePath);
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(xmlFormatFile));
			transformer.setOutputProperty(OutputKeys.STANDALONE, "no"); // Pretty print
			DOMSource source = new DOMSource(doc);
			
			transformer.transform(source, result);
		}
	}
}
