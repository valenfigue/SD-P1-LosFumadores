package com.mycompany.app.clients.smokers;

import com.mycompany.app.smokers.SmokerWithMatchstick;

import java.io.IOException;

public class SmokerWithMatchstickRunner {
	public static void main(String[] args) throws IOException {
		SmokerRunner runnerClient = new SmokerRunner();
		SmokerWithMatchstick smoker = new SmokerWithMatchstick();
		
		runnerClient.mainProgram(smoker);
	}
}
