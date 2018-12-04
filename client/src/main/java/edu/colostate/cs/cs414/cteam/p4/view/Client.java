package edu.colostate.cs.cs414.cteam.p4.view;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ClientListener clientListener;
	private boolean open = true;
	
	public Client(String ip, int port, ClientListener listener){
		clientListener=listener;
		
		try{
			socket=new Socket(ip, port);
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out=new PrintWriter(socket.getOutputStream(), true);
			
			Thread clientThread = new Thread(new Runnable(){
				public void run(){
					while(open){
						try{
							String s = in.readLine();
							if(s==null){
								open=false;
								clientListener.disconnected();
								try{ if(socket!=null)socket.close();
								}catch(Exception exception){ exception.printStackTrace(); }
								try{ if(in!=null)in.close();
								}catch(Exception exception){ exception.printStackTrace(); }
								try{ if(out!=null)out.close();
								}catch(Exception exception){ exception.printStackTrace(); }
								return;
							}
							clientListener.reciveInput(s);
						}catch(IOException exception){
							open=false;
							clientListener.serverClosed();
							try{ socket.close();
							}catch(Exception exception1){ exception.printStackTrace(); }
							try{ in.close();
							}catch(Exception exception1){ exception.printStackTrace(); }
							try{ out.close();
							}catch(Exception exception1){ exception.printStackTrace(); }
							return;
						}catch(Exception exception){ exception.printStackTrace(); }
					}
				}
			});
			clientThread.setName("Client Connection");
			clientThread.setDaemon(true);
			clientThread.start();
			listener.connectedToServer();
		}catch(UnknownHostException exception){
			open=false;
			listener.unknownHost();
		}catch(IOException exception){
			open=false;
			listener.couldNotConnect();
		}catch(Exception exception){
			open=false;
			exception.printStackTrace();
		}
	}
	public void dispose(){
		try{
			if(open){
				open=false;
				socket.close();
				in.close();
				out.close();
				clientListener.disconnected();
			}
			socket=null;
			in=null;
			out=null;
			clientListener=null;
		}catch(Exception exception){ exception.printStackTrace();}
	}
	public void send(String msg){ if(open)out.println(msg); }
	public void newMatch(String userID) {if(open)out.println("1" + userID); }
	public void getTurn() {if(open)out.println("2"); } //obsolete
	public void submitTurn(String turn) {if(open)out.println("3" + turn); }
	public void register(String email, String username, String password) {if(open)out.println("4" + username + "-" + password); }
	public void login(String username, String password) {if(open)out.println("5" + username + "-" + password); }
	public void retrieveGame(int user, int matchID) {if(open)out.println("6" + user + "-" + matchID); }
	public void getLatestMatchID() {if(open)out.println("7");}
	
	public boolean receivedMessage() {return clientListener.inputStatus(); }
	public String getMessage() throws InterruptedException {return clientListener.getMessage(); }
	
	public boolean isConnected(){ return open; }


}