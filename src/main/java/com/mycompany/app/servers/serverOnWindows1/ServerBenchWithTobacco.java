package com.mycompany.app.servers.serverOnWindows1;

import com.mycompany.app.benches.BenchWithTobacco;
import com.mycompany.app.servers.Server;

public class ServerBenchWithTobacco extends Server {
	
	public ServerBenchWithTobacco() {
		this.port = 5000;
		this.bench = new BenchWithTobacco();
	}
}
