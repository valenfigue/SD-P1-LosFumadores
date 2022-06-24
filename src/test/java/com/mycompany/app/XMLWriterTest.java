package com.mycompany.app;

import com.mycompany.app.smokers.SmokerWithPaper;
import junit.framework.TestCase;

import javax.xml.transform.TransformerException;

public class XMLWriterTest extends TestCase {
	XMLWriter writer;
	
	public void setUp() throws Exception {
		super.setUp();
		writer = new XMLWriter();
	}
	
	public void tearDown() throws Exception {
	}
	
	public void testUpdateMotionTrace() {
	}
	
	public void testUpdateFile() {
		Client smoker = new SmokerWithPaper();
		String action = smoker.actorName + " está probando la creación de un XML";
		int quantity = 0;
		
		try {
			writer.updateMotionTrace(smoker, action, quantity);
			writer.updateFile();
		} catch (TransformerException e) {
			fail("Hubo un problema al escribir el archivo de trazas de movimientos para este actor.");
		}
	}
}