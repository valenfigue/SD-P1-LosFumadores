package com.mycompany.app.clientSide.smokers;

import com.mycompany.app.common.smokers.SmokerWithMatchstick;

import java.io.IOException;

public class SmokerWithMatchstickRunner {
	public static void main(String[] args) throws IOException, InterruptedException {
		SmokerRunner runnerClient = new SmokerRunner();
		SmokerWithMatchstick smoker = new SmokerWithMatchstick();
		
		runnerClient.mainProgram(smoker);
	}
}
