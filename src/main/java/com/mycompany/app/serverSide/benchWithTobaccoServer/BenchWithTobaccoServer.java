package com.mycompany.app.serverSide.benchWithTobaccoServer;

import com.mycompany.app.serverSide.BenchServer;

/**
 * Creates a server for the bench with tobaccos.
 *
 * @author valen
 */
public class BenchWithTobaccoServer extends BenchServer {
	
	public BenchWithTobaccoServer() {
		this.port = 6000;
		this.bench = new BenchWithTobacco();
	}
}
