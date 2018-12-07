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
	public void storeMatch(String matchString) {if(open)out.println("1" + matchString);}
	public void sendInvitation(String players) {if(open)out.println("2" + players); }
	public void submitTurn(String turn) {if(open)out.println("3" + turn); }
	public void register(String email, String username, String password) {if(open)out.println("4" + username + "-" + password); }
	public void login(String username, String password) {if(open)out.println("5" + username + "-" + password); }
	public void getMatch(String matchID) {if(open)out.println("6" + matchID);}
	public void getLatestMatchID() {if(open)out.println("7");}
	//public void getCurrentMatches() {if(open)out.println("8");}
	public void queryGames(String user) {if(open)out.println("9" + user);}

		
	public boolean receivedMessage() {return clientListener.inputStatus(); }
	public String getMessage() {
		//Wait to receive response indicating a turn was successfully received, but only for 10 seconds
		long startTime = System.currentTimeMillis(); //fetch starting time
		while(!clientListener.inputStatus()) {
			if((System.currentTimeMillis()-startTime)>5000) {
				if(clientListener.inputStatus()) {
					break;
				}
				System.out.println("Client:ERROR: turn retrieval timed out.");
				return null;
			}
			if(clientListener.inputStatus()) {
				break;
			}
		}
		
		try {
			String result = clientListener.getMessage();
			if(result.equals("null")) {
				return null;
			}
			return result;

		} catch (InterruptedException e) {
			System.out.println("Client:ERROR: failed to read message.");
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean isConnected(){ return open; }






}