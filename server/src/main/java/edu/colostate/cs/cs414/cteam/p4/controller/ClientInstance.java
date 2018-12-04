package edu.colostate.cs.cs414.cteam.p4.controller;


import java.net.InetAddress;

public class ClientInstance{
	public final InetAddress ip;
	public final int port;
	public ClientInstance(InetAddress ip, int port){
		this.ip=ip;
		this.port=port;
	}
	@Override public String toString(){ return ip.toString()+":"+port; }
}