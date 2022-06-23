package com.mycompany.app.servers.benchWithMatchstickServer;

import com.mycompany.app.benches.BenchWithMatchstick;
import com.mycompany.app.benches.BenchWithTobacco;
import com.mycompany.app.servers.BenchServer;

public class BenchWithMatchstickServer extends BenchServer {
	public BenchWithMatchstickServer() {
		this.port = 5001;
		this.bench = new BenchWithMatchstick();
	}
}
