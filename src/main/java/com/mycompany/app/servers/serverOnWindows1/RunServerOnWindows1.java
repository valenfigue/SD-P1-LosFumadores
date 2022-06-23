package com.mycompany.app.servers.serverOnWindows1;


import java.io.IOException;

public class RunServerOnWindows1 {
	public static void main(String[] args) {
		try {
			new ServerOnWindows1().startListening();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
