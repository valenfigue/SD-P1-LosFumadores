package com.mycompany.app.serverSide;

/**
 * Creates a server for the bench with tobaccos.
 *
 * @author valen
 */
public class BenchWithTobaccoServer extends BenchServer {
	
	public BenchWithTobaccoServer() {
		this.port = 5000;
		this.bench = new BenchWithTobacco();
	}
}
