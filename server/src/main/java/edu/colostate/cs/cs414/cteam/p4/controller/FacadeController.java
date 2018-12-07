package edu.colostate.cs.cs414.cteam.p4.controller;

import java.util.ArrayList;
import javafx.util.Pair;

public class FacadeController {
		
	int latest_match = 0;
	
	ArrayList<Integer> players = new ArrayList<Integer>();
	ArrayList<Pair<Integer, String>> matches = new ArrayList<Pair<Integer, String>>();
	ArrayList<Pair<Integer, Integer>> invites = new ArrayList<Pair<Integer, Integer>>();

	
	public FacadeController() {
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
		return 0;
	}
	
	public int login(String credentials) {
		return 0;
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
	
}
