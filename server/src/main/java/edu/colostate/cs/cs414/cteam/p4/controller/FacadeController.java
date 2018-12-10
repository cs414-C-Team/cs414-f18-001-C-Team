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
		String[] match = turn.split("-");
	//  [id, player1_id, player2_id, current_player_id, date, status(int), board_state]
		int gameid = Integer.parseInt(match[0]);
		int player1 = Integer.parseInt(match[1]);
		int player2 = Integer.parseInt(match[2]);
		int currentPlayer = Integer.parseInt(match[3]);
		String date = match[4];
		int status = Integer.parseInt(match[5]);
		String boardState = match[6];
		
		if(status != 1111 && status > 0) {
			if(status == player1) {
				db.matchOver(Integer.toString(player1), Integer.toString(player2), Integer.toString(gameid));
				return status;
			} else {
				db.matchOver(Integer.toString(player2), Integer.toString(player1), Integer.toString(gameid));
				return status;
			}
		}
		
		if(db.storeGame(gameid, player1, player2, currentPlayer, date, boardState, status)) {
			return 0;
		} else {
			return 1111;
		}
	}

	public String retrieveMatch(String match) {
		System.out.println("Controller: retrieving matches " + match);
		try {
			ResultSet retrieved_match = db.retrieveMatch(match);
			if(retrieved_match.next()){
				String result = retrieved_match.getInt("gameid") + "-" + retrieved_match.getInt("user1") + "-" + retrieved_match.getInt("user2") + "-" 
	            					+ retrieved_match.getInt("turn") + "-" + retrieved_match.getString("date") + "-" + retrieved_match.getString("status") + "-" + retrieved_match.getString("state");
				return result;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		String date = match[4];
		int status = Integer.parseInt(match[5]);
		String boardState = match[6];
		
		if(status == 1111)
			db.deleteInvite(player1, player2);  // removes the invite from the db  (player 1 is the sender)
		
		boolean result = db.storeGame(gameid, player1, player2, currentPlayer, date, boardState, status);
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
		System.out.println("Controller: querying matches for user " + substring);
		int user = Integer.parseInt(substring);
		String result = "";
		try {
			ResultSet inviteSet = db.receivedInvites(user);
			db.printRS(inviteSet);
			inviteSet.absolute(0);
			
			boolean first = true;
			while (inviteSet.next()){
				if (!first) {
					result += "&" + inviteSet.getInt("sender") + ":" + inviteSet.getInt("receiver");
				} else {
					result += inviteSet.getInt("sender") + ":" + inviteSet.getInt("receiver");
					first = false;
				}
			}
			
			ResultSet matchSet = db.currentMatches(user);
			db.printRS(matchSet);
			matchSet.absolute(0);
			first = true;
			while (matchSet.next()){
				System.out.println("System: adding match to return list");
				String match = matchSet.getInt("gameid") + "-" + matchSet.getInt("user1") + "-" + matchSet.getInt("user2") + "-" 
	            					+ matchSet.getInt("turn") + "-" + matchSet.getString("date") + "-" + matchSet.getInt("Status") + "-" + matchSet.getString("state");
				if(!first) {
					result += "&" + match;
				} else {
					result += match;
					first = false;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result.length() == 0) {
			System.out.println("System: no invites/matches found for user " + substring);
			return null;
		} else {
			System.out.println("System: Returning invites/matches for user " + substring + ": " + result);
			return result;
		}
	}
		
	/** Search users in database. Returns a string in the format: user1&user2&user3  */
	public String searchUsers(String keyword) {
		try {
			ResultSet rs = db.searchUsers(keyword);
			if (!rs.next()) {
				return " ";
			}
			String result = rs.getString("username") + ":" + rs.getString("id");
			while (rs.next()) {
				result += "&" + rs.getString("username") + ":" + rs.getString("id");
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
		
	}

}

