package com.mycompany.app.serverSide;

/**
 * Creates a server for the bench with matchsticks.
 *
 * @author valen
 */
public class BenchWithMatchstickServer extends BenchServer {
	public BenchWithMatchstickServer() {
		this.port = 5001;
		this.bench = new BenchWithMatchstick();
	}
}
