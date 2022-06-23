package com.mycompany.app.clients.smokers;

import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.*;

public class SmokerWithTobaccoRunner {
	public static void main(String[] args) throws IOException {
		SmokerRunner runnerClient = new SmokerRunner();
		SmokerWithTobacco smoker = new SmokerWithTobacco();
		
		runnerClient.mainProgram(smoker);
	}
}
