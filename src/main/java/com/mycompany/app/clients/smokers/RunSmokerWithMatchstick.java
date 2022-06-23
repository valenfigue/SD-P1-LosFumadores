package com.mycompany.app.clients.smokers;

import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.IOException;

public class RunSmokerWithMatchstick {
	public static void main(String[] args) throws IOException {
		RunnerSmoker runnerClient = new RunnerSmoker();
		SmokerWithTobacco smoker = new SmokerWithTobacco();
		
		runnerClient.mainProgram(smoker);
	}
}
