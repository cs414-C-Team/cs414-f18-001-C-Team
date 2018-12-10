package edu.colostate.cs.cs414.cteam.p4.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.ResultSetMetaData;

import javafx.util.Pair;

public class FacadeController {
		
	int latest_match = 0;
	Database db;
	ArrayList<Integer> players = new ArrayList<Integer>();
	ArrayList<Pair<Integer, String>> matches = new ArrayList<Pair<Integer, String>>();
	ArrayList<Pair<Integer, Integer>> invites = new ArrayList<Pair<Integer, Integer>>();

	
	public FacadeController() {
		db = new Database();
		players.add(999);
		players.add(3);
	}

	/**  Registers a user in the database, returns their new user id */
	public int register(String substring) {
		String[] registerInfo = substring.split("-");   //  [email, username, password]
		try {
			boolean success = db.register(registerInfo[1], registerInfo[2], registerInfo[0]);
			if (success) { 
				ResultSet rs = db.getUser(registerInfo[1]);
				rs.absolute(1);
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/** Checks login information in the database, returns the user id if valid, otherwise -1  */
	public int login(String credentials) {
		String[] loginInfo = credentials.split("-");
		try {
			if (db.login(loginInfo[0], loginInfo[1])) {
				ResultSet rs = db.getUser(loginInfo[0]);
				rs.absolute(1);
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/** TODO */
	public int processTurn(String turn) {
		for(int i = 0; i < matches.size(); i++) {
			if(matches.get(i).getKey() == Integer.parseInt(turn.split("-")[0])) {
				matches.remove(matches.get(i));
				matches.add(new Pair<Integer,String>(Integer.parseInt(turn.split("-")[0]), turn));
				return 0;
			}
		}
		return 1;
	}

	/** TODO */
	public String retrieveMatch(String match) {
		for(int i = 0; i < matches.size(); i++) {
			if(matches.get(i).getKey() == Integer.parseInt(match)) {
				return matches.get(i).getValue();
			}
		}
		return null;
	}
	

	/** Add an invite to the database  */
	public String storeInvite(String inviteMsg) {
   //   [sender_id, receiver_id]
		String[] users = inviteMsg.split("-");
		int sender = Integer.parseInt(users[0]);
		int receiver = Integer.parseInt(users[1]);
		db.sendInvite(sender, receiver);
		return "stored invite";
	}

	
	/** Delete an invite from database without a new game  */
	public void rejectInvite(String responseMsg) {
   //   [sender_id, receiver_id]
		String[] users = responseMsg.split("-");
		db.deleteInvite(Integer.parseInt(users[0]), Integer.parseInt(users[1])); 
	}
	

	/** Store a new match in database, and remove the corresponding invite  */
	public void newMatch(String matchString) {
		String[] match = matchString.split("-");
	//  [id, player1_id, player2_id, current_player_id, date, status(int), board_state]
		int gameid = Integer.parseInt(match[0]);
		int player1 = Integer.parseInt(match[1]);
		int player2 = Integer.parseInt(match[2]);
		int currentPlayer = Integer.parseInt(match[3]); 
		int status = Integer.parseInt(match[5]);
		String boardState = match[6];
		
		if(status == 1111)
			db.deleteInvite(player1, player2);  // removes the invite from the db  (player 1 is the sender)
		
		boolean result = db.storeGame(gameid, player1, player2, currentPlayer, boardState, status);
	}
	
	
	/** TODO: plug this into the db  */
	public int newGameID() {
		return ++latest_match;
	}
	
	
     /** Returns a string containing the invites and matches for a user  */ 
     //  Format: <invite>&<invite>...&<game>&<game>
     //  Games are returned in this format: <matchID>-<user1ID>-<user2ID>-<player turn>-<date>-<board>
     //  Invites are returned in this format: <senderID>   
	public String queryMatches(String substring) {
		int user = Integer.parseInt(substring);
		String result = "";
		try {
			ResultSet inviteSet = db.receivedInvites(user);
			inviteSet.absolute(1);
			boolean first = true;
			do {
				if (!first) {
					result += "&" + inviteSet.getInt("sender") + ":" + inviteSet.getInt("receiver");
				} else {
					result += inviteSet.getInt("sender") + ":" + inviteSet.getInt("receiver");
					first = false;
				}
			} while (inviteSet.next());
			
			ResultSet matchSet = db.currentMatches(user);
			matchSet.absolute(1);
			first = true;
			do {
				String match = matchSet.getInt("gameid") + "-" + matchSet.getInt("user1") + "-" + matchSet.getInt("user2") + "-" 
	            					+ matchSet.getInt("turn") + "-" + matchSet.getInt("current_timestamp") + "-" + matchSet.getInt("state");
				if(!first) {
					result += "&" + match;
				} else {
					result += match;
					first = false;
				}
			} while (matchSet.next());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result.length() == 0) return null;
		System.out.println("System: Returning turns/matches for user " + substring + ": " + result);
		return result;
	}
		
	/** Search users in database. Returns a string in the format: user1&user2&user3  */
	public String searchUsers(String keyword) {
		try {
			ResultSet rs = db.searchUsers(keyword);
			rs.absolute(1);
			String result = rs.getString(1);
			while (rs.next()) {
				result += "&" + rs.getString(1);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
/*		System.out.println("System: number of total invites: " + invites.size());
		System.out.println("System: number of total matches: " + matches.size());
		String result = "";
		boolean first = true;
		for(int i = 0; i < invites.size(); i++) {
			if(Integer.parseInt(substring) == invites.get(i).getValue()) {
				if(!first) {
					result += "&";
					result += invites.get(i).getKey() + ":" + invites.get(i).getValue(); 
				} else {
					result += invites.get(i).getKey() + ":" + invites.get(i).getValue(); 
					first = false;
				}
			}
		}
		first = true;
		for(int i = 0; i < matches.size(); i++) {
			if(Integer.parseInt(matches.get(i).getValue().split("-")[1]) == user || Integer.parseInt(matches.get(i).getValue().split("-")[2]) == user) {
				if(!first) {
					result += "&" + matches.get(i).getValue();
				} else {
					result += matches.get(i).getValue();
					first = false;
				}
			}
		}
		if(result.length() == 0) {
			return null;
		}
		System.out.println("System: Returning turns/matches for user " + substring + ": " + result);
		return result;
*/
	
	
	
	public static void main(String[] args) throws SQLException {
		FacadeController f = new FacadeController();
		Database db = f.db;
//		db.resetDB();
//		System.out.println(String.format("SELECT * FROM jungle.Invites WHERE Receiver = '%1$s';", "piss"));

//		f.login("ian-california");
		
		
		
//		System.out.println(f.storeInvite("3-2"));
		
		
//		db.register("ianaf", "california", "ianaf100@gmail.com");
		String result = f.searchUsers("user");
//		ResultSetMetaData rsmd = rs.getMetaData();
//		rs.absolute(2);
//		rs.next();
//		db.printRS(rs);
		System.out.println(result);
//		
//		System.out.println(db.login("ianaf", "california"));
		
	}
}

