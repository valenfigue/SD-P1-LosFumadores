package com.mycompany.app.clients;

import com.mycompany.app.Smoker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RunSmokerWithTobacco {
	public static void main(String[] args) {
		RunnerClient runnerClient = new RunnerClient();
		
		try (Socket socket = runnerClient.selectBench()) {
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
			
			Smoker smokerWithTobacco = Smoker.createSmokerWithTobacco(socket);
			outputStream.writeUTF(smokerWithTobacco.getActorName());
			
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
