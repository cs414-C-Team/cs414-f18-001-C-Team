package edu.colostate.cs.cs414.cteam.p4.controller;

public class ServerRunner {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerListener slt = new ServerListener();
		int port = 51600;
		
		while(port < 90000) {
			try {
				start_server(port, slt);
			} catch(Exception e) {
				port++;
				continue;
			}
		}
	}
	
	public static void start_server(int port, ServerListener slt) throws Exception{
		Server testServer;
		
		try {
		testServer = new Server(port, slt);
		} catch(Exception e) {
			throw(e);
		}
		
		System.out.println(testServer.getIp() + ":" + port);
		while(true) {
			;
		}
	}
}
