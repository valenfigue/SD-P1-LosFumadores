package com.mycompany.app.clientSide.smokers;

import com.mycompany.app.common.smokers.SmokerWithTobacco;

/**
 * Execute the program's client side for a smoker with tobacco.
 * @author valen
 */
public class SmokerWithTobaccoRunner {
	public static void main(String[] args) {
		SmokerRunner runnerClient = new SmokerRunner();
		SmokerWithTobacco smoker = new SmokerWithTobacco();
		
		runnerClient.startProgram(smoker);
	}
}
