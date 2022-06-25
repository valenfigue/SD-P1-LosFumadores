package com.mycompany.app.serverSide.benchWithMatchstickServer;

import com.mycompany.app.serverSide.BenchServer;

public class BenchWithMatchstickServer extends BenchServer {
	public BenchWithMatchstickServer() {
		this.port = 5001;
		this.bench = new BenchWithMatchstick();
	}
}
