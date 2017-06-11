package knockKnockBuddy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientBuddy implements Runnable {
	private String hostName;
	private String portNumber;

	public ClientBuddy(String hostName, String portNumber) {
		setHostName(hostName);
		setPortNumber(portNumber);
	}

	@Override
	public void run() {
		String hostName = getHostName();
		int portNumber = Integer.parseInt(getPortNumber());

		try (Socket kkSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(kkSocket.getOutputStream(),
						true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						kkSocket.getInputStream()));) {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(
					System.in)); 
			String fromServer;
			String fromUser;
			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("Bye.")) {
					break;
				}

				fromUser = stdIn.readLine();
				if (fromUser != null) {
					//System.out.println("Client: " + fromUser);
					out.println(fromUser);
				}
			}
			;
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		}
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
}
