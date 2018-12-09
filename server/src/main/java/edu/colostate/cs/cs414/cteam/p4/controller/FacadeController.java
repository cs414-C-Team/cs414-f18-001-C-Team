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

	
	public int register(String substring) {
		String[] registerInfo = substring.split("-");   //  [email, username, password]
		try {
			boolean success = db.register(registerInfo[1], registerInfo[2], registerInfo[0]);
			if (success) { 
				ResultSet rs = db.getUser(registerInfo[0]);
				db.printRS(rs);
				rs.absolute(1);
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	public int login(String credentials) {
		String[] loginInfo = credentials.split("-");
		try {
			if (db.login(loginInfo[0], loginInfo[1])) {
				ResultSet rs = db.getUser(loginInfo[0]);
				db.printRS(rs);
				rs.absolute(1);
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public String retrieveMatch(String match) {
		for(int i = 0; i < matches.size(); i++) {
			if(matches.get(i).getKey() == Integer.parseInt(match)) {
				return matches.get(i).getValue();
			}
		}
		return null;
	}

	public int latestMatchID() {
		System.out.println("Returning latest matchID");
		return ++latest_match;
	}

	public void storeMatch(String match) {
		System.out.println("Storing match:" + match);
		if(Integer.parseInt(match.split("-")[5]) == 1111) {
			newMatch(match);
		}
		matches.add(new Pair<Integer, String>(Integer.parseInt(match.split("-")[0]), match));
	}
	
	public void newMatch(String match) {
		System.out.println("Creating new match:" + match);
		
		for(int i = 0; i < invites.size(); i++) {
			if(invites.get(i).getKey() == Integer.parseInt(match.split("-")[1])) {
				if(invites.get(i).getValue() == Integer.parseInt(match.split("-")[2])) {
					invites.remove(i);
				}
			} else if(invites.get(i).getValue() == Integer.parseInt(match.split("-")[1])){
				if(invites.get(i).getKey() == Integer.parseInt(match.split("-")[2])) {
					invites.remove(i);
				}
			}
		}
	}

	public String getCurrentMatches(String substring) {

		return null; //not used
	}

	public String queryMatches(String substring) {
		//For game invites, return the other player's ID
		System.out.println("System: number of total invites: " + invites.size());
		System.out.println("System: number of total matches: " + matches.size());
		String result = "";
		boolean first = true;
		
		for(int i = 0; i < invites.size(); i++) {
			if(Integer.parseInt(substring) == invites.get(i).getValue()) {
				if(!first) {
					result += "&";
					result += invites.get(i).getKey() + ":" + invites.get(i).getValue(); 
				} else {
					result += invites.get(i).getKey() + ":" + invites.get(i).getValue();} 
					first = false;
			}
		}
		
		int user = Integer.parseInt(substring);
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
	}

	public String storeInvite(String substring) {
		System.out.println("System: Storing invite");
		invites.add(new Pair<Integer, Integer>(Integer.parseInt(substring.split("-")[0]), Integer.parseInt(substring.split("-")[1])));
		return "Stored invite";
		
	}
	

	public static void main(String[] args) throws SQLException {
		FacadeController f = new FacadeController();
		Database db = new Database();
//		db.resetDB();
		int result = f.register("california-california");
//		System.out.println(result);
		
		
//		db.register("ianaf", "california", "ianaf100@gmail.com");
		ResultSet rs = db.getUsers();
//		ResultSetMetaData rsmd = rs.getMetaData();
////		System.out.println(users);
////		
//		rs.absolute(1);
//		db.printRS(rs);
//		System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
//		
//		System.out.println(db.login("ianaf", "california"));
		
	}
}

