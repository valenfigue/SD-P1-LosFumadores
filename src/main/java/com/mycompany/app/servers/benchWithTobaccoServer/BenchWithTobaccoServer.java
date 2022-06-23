package com.mycompany.app.servers.benchWithTobaccoServer;

import com.mycompany.app.benches.BenchWithTobacco;
import com.mycompany.app.servers.BenchServer;

public class BenchWithTobaccoServer extends BenchServer {
	
	public BenchWithTobaccoServer() {
		this.port = 5000;
		this.bench = new BenchWithTobacco();
	}
}
