package com.mycompany.app.serverSide;


/**
 * Execute the server for the bench with the tobaccos.
 *
 * @author valen
 */
public class BenchWithTobaccoServerRunner {
	public static void main(String[] args) {
		new BenchWithTobaccoServer().startListening();
	}
}
