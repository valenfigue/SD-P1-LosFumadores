package com.mycompany.app.serverSide.benchWithPaperServer;

import com.mycompany.app.serverSide.BenchServer;

/**
 * Creates a server for the bench with papers.
 *
 * @author valen
 */
public class BenchWithPaperServer extends BenchServer {
	public BenchWithPaperServer() {
		this.port = 6002;
		this.bench = new BenchWithPaper();
	}
}
