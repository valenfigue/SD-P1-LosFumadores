package com.mycompany.app.XMLWriter;

import com.mycompany.app.Client;
import com.mycompany.app.smokers.SmokerWithMatchstick;
import com.mycompany.app.smokers.SmokerWithPaper;
import junit.framework.TestCase;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class XMLWriterTest extends TestCase {
	XMLWriter writer;
	
	public void setUp() throws Exception {
		super.setUp();
		writer = new XMLWriter();
	}
	
	public void testUpdateFile() {
		Client smoker = new SmokerWithPaper();
		String action = "SOLO " + smoker.getActorName() + " está probando la creación de un XML";
		int quantity = 0;
		
		try {
			writer.updateMotionTrace(smoker, action, quantity);
//			writer.updateFile();
		} catch (TransformerException e) {
			fail("Hubo un problema al escribir el archivo de trazas de movimientos para este actor.");
		} catch (IOException | SAXException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void testUpdateFileWithTwoActivities() {
		Client smoker1 = new SmokerWithPaper();
		String action1 = smoker1.getActorName() + " está probando la creación de un XML";
		int quantity1 = 1;
		
		Client smoker2 = new SmokerWithMatchstick();
		String action2 = smoker2.getActorName() + " también está probando la creación de un XML";
		int quantity2 = 2;
		
		try {
			// First activity
			writer.updateMotionTrace(smoker1, action1, quantity1);
			// Second activity
			writer.updateMotionTrace(smoker2, action2, quantity2);
			
//			writer.updateFile();
		} catch (TransformerException e) {
			fail("Hubo un problema al escribir el archivo de trazas de movimientos para este actor.");
		} catch (IOException | SAXException e) {
			throw new RuntimeException(e);
		}
	}
}