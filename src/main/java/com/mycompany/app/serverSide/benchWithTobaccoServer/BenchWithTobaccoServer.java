package com.mycompany.app.serverSide.benchWithTobaccoServer;

import com.mycompany.app.serverSide.BenchServer;

public class BenchWithTobaccoServer extends BenchServer {
	
	public BenchWithTobaccoServer() {
		this.port = 5000;
		this.bench = new BenchWithTobacco();
	}
}
