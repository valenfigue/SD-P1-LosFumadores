package com.mycompany.app.serverSide.benchWithPaperServer;

import com.mycompany.app.serverSide.BenchServer;

public class BenchWithPaperServer extends BenchServer {
	public BenchWithPaperServer() {
		this.port = 5002;
		this.bench = new BenchWithPaper();
	}
}
