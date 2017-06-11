package knockKnockBuddy;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBuddy implements Runnable {

	private String portNumber;

	public ServerBuddy(String portNumber) {
		setPortNumber(portNumber);
	}

	public void run() {

		int portNumber = Integer.parseInt(getPortNumber());

		try (ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(
						clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));) {
			String inputLine, outputLine;

			// Initiate conversation with client
			KnockKnockBuddy kkb = new KnockKnockBuddy();
			outputLine = kkb.processInput(null);
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				outputLine = kkb.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("Bye!"))
					break;
			}
		} catch (IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
}
