package com.mycompany.app.clientSide.smokers;

import com.mycompany.app.common.smokers.SmokerWithPaper;

/**
 * Execute the program's client side for a smoker with paper.
 * @author valen
 */
public class SmokerWithPaperRunner {
	public static void main(String[] args) {
		SmokerRunner runnerClient = new SmokerRunner();
		SmokerWithPaper smoker = new SmokerWithPaper();
		
		runnerClient.startProgram(smoker);
	}
}
