package com.mycompany.app.clients;

import com.mycompany.app.Smoker;
import com.mycompany.app.smokers.SmokerWithTobacco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RunSmokerWithTobacco {
	public static void main(String[] args) {
		RunnerClient runnerClient = new RunnerClient();
		int exit = 0; // The user doesn't want to end the program.
		
		do {
			try (Socket socket = runnerClient.selectBench()) {
				DataInputStream inputStream = new DataInputStream(socket.getInputStream());
				DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
				
				Smoker smokerWithTobacco = new SmokerWithTobacco();
				outputStream.writeUTF(smokerWithTobacco.getActorName());
				
			} catch (UnknownHostException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} while (exit != 0);
	}
}
