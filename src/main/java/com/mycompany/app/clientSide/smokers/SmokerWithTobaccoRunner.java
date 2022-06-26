package com.mycompany.app.clientSide.smokers;

import com.mycompany.app.common.smokers.SmokerWithTobacco;

import java.io.*;

public class SmokerWithTobaccoRunner {
	public static void main(String[] args) throws IOException, InterruptedException {
		SmokerRunner runnerClient = new SmokerRunner();
		SmokerWithTobacco smoker = new SmokerWithTobacco();
		
		runnerClient.mainProgram(smoker);
	}
}
