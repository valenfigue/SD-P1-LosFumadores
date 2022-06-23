package com.mycompany.app.clients;

import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.*;

public class RunSmokerWithTobacco {
	public static void main(String[] args) throws IOException {
		RunnerSmoker runnerClient = new RunnerSmoker();
		SmokerWithTobacco smoker = new SmokerWithTobacco();
		
		runnerClient.mainProgram(smoker);
	}
}
