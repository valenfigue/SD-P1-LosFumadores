package com.mycompany.app.serverSide.benchWithPaperServer;

import java.io.IOException;

/**
 * Execute the server for the bench with the papers.
 *
 * @author valen
 */
public class BenchWithPaperServerRunner {
	public static void main(String[] args) {
		new BenchWithPaperServer().startListening();
	}
}
