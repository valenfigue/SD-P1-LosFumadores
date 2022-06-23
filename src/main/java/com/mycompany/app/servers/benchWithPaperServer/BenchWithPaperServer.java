package com.mycompany.app.servers.benchWithPaperServer;

import com.mycompany.app.benches.BenchWithPaper;
import com.mycompany.app.servers.BenchServer;

public class BenchWithPaperServer extends BenchServer {
	public BenchWithPaperServer() {
		this.port = 5002;
		this.bench = new BenchWithPaper();
	}
}
