package com.mycompany.app.clients.smokers;

import com.mycompany.app.smokers.SmokerWithPaper;

import java.io.IOException;

public class SmokerWithPaperRunner {
	public static void main(String[] args) throws IOException {
		SmokerRunner runnerClient = new SmokerRunner();
		SmokerWithPaper smoker = new SmokerWithPaper();
		
		runnerClient.mainProgram(smoker);
	}
}
