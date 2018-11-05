package wraith.library.Multiplayer;


import java.io.PrintWriter;

public interface ServerListener{
	public void clientConnected(ClientInstance client, PrintWriter out);
	public void clientDisconnected(ClientInstance client);
	public void recivedInput(ClientInstance client, String msg);
	public void serverClosed();
}