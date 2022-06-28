package com.mycompany.app.clientSide.smokers;

import com.mycompany.app.common.smokers.SmokerWithMatchstick;

/**
 * Execute the program's client side for a smoker with matchstick.
 * @author valen
 */
public class SmokerWithMatchstickRunner {
	public static void main(String[] args) {
		SmokerRunner runnerClient = new SmokerRunner();
		SmokerWithMatchstick smoker = new SmokerWithMatchstick();
		
		runnerClient.startProgram(smoker);
	}
}
