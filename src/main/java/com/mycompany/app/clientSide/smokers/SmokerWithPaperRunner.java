package com.mycompany.app.clientSide.smokers;

import com.mycompany.app.common.smokers.SmokerWithPaper;

import java.io.IOException;

public class SmokerWithPaperRunner {
	public static void main(String[] args) throws IOException, InterruptedException {
		SmokerRunner runnerClient = new SmokerRunner();
		SmokerWithPaper smoker = new SmokerWithPaper();
		
		runnerClient.mainProgram(smoker);
	}
}
