package com.mycompany.app.serverSide;

/**
 * Creates a server for the bench with papers.
 *
 * @author valen
 */
public class BenchWithPaperServer extends BenchServer {
	public BenchWithPaperServer() {
		this.port = 5002;
		this.bench = new BenchWithPaper();
	}
}