package knockKnockBuddy;

public class KKBMain {
	public static void main(String args[]) {
		String portNumber = "3300";
		String hostName = "192.168.1.154";
		ServerBuddy server = new ServerBuddy(portNumber);
		ClientBuddy client = new ClientBuddy(hostName, portNumber);
		new Thread(server).start();
		client.run();
	}
}
