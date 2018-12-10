package edu.colostate.cs.cs414.cteam.p4.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that allows connection to and querying of a remote AWS RDS database.
 */

public class Database {
	// db configuration information
	private Connection conn;

	/**
	 * Constructor. Establishes connection with database and queries it for
	 * available filters.
	 */
	public Database() {
		String myDriver = "com.mysql.jdbc.Driver";
		String myUrl = "jdbc:mysql://cs414-c-team.cvrg8lr7y0hh.us-east-2.rds.amazonaws.com";
		String myUser = "jpode";
		String myPassword = "830566010";
		try {

			// connect to the database
			Class.forName(myDriver);
			System.out.println("Connecting");
			conn = DriverManager.getConnection(myUrl, myUser, myPassword);

			System.out.println("Connected");
		} catch (Exception e) {
			System.err.println("Exception in DB Construction: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Searches the database with the given search.
	 * 
	 * @param search
	 *            - SQL statement.
	 * @return SQL ResultSet object containing results from the query.
	 * @throws SQLException
	 */

	public ResultSet sendQuery(String search) throws SQLException {
		System.out.println("DB query:" + search);
		Statement stQuery = conn.createStatement();
		return stQuery.executeQuery(search);
	}

	public int update(String update) throws SQLException {
		System.out.println("DB update: " + update);
		Statement stUpdate = conn.createStatement();
		return stUpdate.executeUpdate(update);
	}
	
	public void dropTables() throws SQLException {
		update("drop table if exists jungle.Invites;");
		update("drop table if exists jungle.Match_Record;");
		update("drop table if exists jungle.Match_State;");
		update("drop table if exists jungle.Users;");
	}
	
	public void createTables() throws SQLException {
		String create = "Create Table if not exists jungle.Users (\r\n" + 
				"EmailID varchar(255) not null unique,\r\n" + 
				"Username varchar(255) not null unique,\r\n" + 
				"ID int not null unique AUTO_INCREMENT,\r\n" +
				"Password varchar(255) not null,\r\n" + 
				"Key (ID),\r\n" +
				"Primary Key (ID))";
		update(create);
		create = "Create table if not exists jungle.Match_State (\r\n" + 
				"				GameID int not null unique,\r\n" + 
				"				User1 int not null,\r\n" + 
				"				User2 int not null,\r\n" + 
				"               Turn int not null,\r\n" +
				"				State varchar(300) not null,\r\n" + 
				"               Status int,\r\n" +
				"				date varchar(30) not null,\r\n" + 
				"				Primary Key(GameID)\r\n" + 
//				"				Constraint Foreign key(User1) references User(id),\r\n" + 
//				"				Constraint Foreign key(User2) references User(id)\r\n" +    // I couldn't get foreign keys working
				"				);";
		update(create);
		create = "CREATE TABLE if not exists jungle.Match_Record(\r\n" + 
				"				GameID int NOT NULL UNIQUE,\r\n" + 
				"				Winner varchar(30) NOT NULL,\r\n" + 
				"				Loser varchar(30) NOT NULL,\r\n" + 
				"				foreign key(Winner) references Users(ID),\r\n" + 
				"				foreign key(Loser) references Users(ID),\r\n" + 
				"				primary key(GameID)\r\n" + 
				"				);";
		update(create);
		create = "				Create table if not exists jungle.Invites (\r\n" + 
				"				Sender varchar(30) ,\r\n" + 
				"				Receiver varchar(30) ,\r\n" + 
				"				Status varchar(30) ,\r\n" + 
				"				foreign key(Sender) references Users(ID),\r\n" + 
				"				foreign key(Receiver) references Users(ID)\r\n" + 
				"				);";
		update(create);
		create = "INSERT INTO jungle.Users VALUES (admin@admin.com, admin, 999, );";
		update(create);
		create = "INSERT INTO jungle.Users VALUES (test@test.com, test, 3, );";
		update(create);
	}
	
	public void resetDB() throws SQLException {
		dropTables();
		createTables();
	}

	
	public void printRS(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		   System.out.println("***");
		   int columnsNumber = rsmd.getColumnCount();
		   while (rs.next()) {
		       for (int i = 1; i <= columnsNumber; i++) {
		           if (i > 1) System.out.print(",  ");
		           String columnValue = rs.getString(i);
		           System.out.print(columnValue + " " + rsmd.getColumnName(i));
		       }
		       System.out.println("");
		   }
	}
	
	
	public boolean login(String user, String pass) throws SQLException {
		String query = "SELECT Username, Password FROM jungle.Users WHERE Username = '" + user + "' AND Password = '"
				+ pass + "';";
		ResultSet rs = sendQuery(query);
		return rs.next();
	}

	public boolean alter(String update) {
		int ret = 0;
		try {
			ret = update(update);
		} catch (SQLException e) {
			return false;
		}
		return ret > 0;
	}

	public boolean register(String user, String pass, String email) {
		String update = String.format(
				"INSERT INTO jungle.Users (EmailID, Username, Password) VALUES ('%1$s', '%2$s', '%3$s');", email, user,
				pass);
		return alter(update);
	}

	public boolean deleteAccount(String user, String pass) {
		String update = String.format("DELETE FROM jungle.Users WHERE Username = '%1$s' AND Password = '%2$s';", user,
				pass);
		return alter(update);
	}

	public boolean sendInvite(int sender, int receiver) {
		String update = String.format(
				"INSERT INTO jungle.Invites (Sender, Receiver) VALUES ('%1$d', '%2$d');", sender, receiver);
		return alter(update);
	}
	
	public ResultSet searchUsers(String search) throws SQLException {
		String query = "SELECT Username, id FROM jungle.Users WHERE Username LIKE '%" + search + "%';";
		return sendQuery(query);
	}

	public ResultSet receivedInvites(int user) throws SQLException {
		String query = String.format("SELECT * FROM jungle.Invites WHERE Receiver = '%1$d';", user);
		System.out.println("DB: Sending query: " + query);
		return sendQuery(query);
	}

	public ResultSet viewSentInvites(String user) throws SQLException {
		String query = String.format("SELECT * FROM jungle.Invites WHERE Sender = '%1$s';", user);
		return sendQuery(query);
	}
	
	public boolean deleteInvite(int sender, int receiver) {
		//delete the invite
		String update = String.format("DELETE FROM jungle.Invites WHERE Receiver = '%1$d' and Sender = '%2$d';", receiver, sender);
		return alter(update);
	}
		
	public boolean storeGame(int id, int player1, int player2, int currentPlayer, String date, String state, int status) {
		try {
			if(sendQuery(String.format("SELECT * FROM jungle.Match_State WHERE GameID = '%1$d'", id)).next() == true) {
				String update = String.format("REPLACE INTO jungle.Match_State (GameID, User1, User2, Turn, Date, State, Status) " +
	 				       "VALUES ('%1$d', '%2$d', '%3$d', '%4$d', '%5$s','%6$s', '%7$d');", 
	 				         id, player1, player2, currentPlayer, date, state, status);
				return alter(update);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String update = String.format("INSERT INTO jungle.Match_State (GameID, User1, User2, Turn, date, State, Status) " +
				 				       "VALUES ('%1$d', '%2$d', '%3$d', '%4$d', '%5$s','%6$s', '%7$d');", 
				 				         id, player1, player2, currentPlayer, date, state, status);
		return alter(update);
	}


	public ResultSet getUser(String username) throws SQLException {
		String query = String.format("SELECT * FROM jungle.Users WHERE Username = '%1$s';", username);
		return sendQuery(query);
	}
	
	public ResultSet getUsers() throws SQLException {
		String query = String.format("SELECT * FROM jungle.Users;");
		return sendQuery(query);
	}

	public ResultSet matches(String user) throws SQLException {
		String query = String.format("SELECT * FROM jungle.Match_State WHERE User1 = '%1$s' OR User2 = '%1$s';", user);
		return sendQuery(query);
	}

	public ResultSet allMatches() throws SQLException {
		String query = "SELECT * FROM jungle.Match_State;";
		return sendQuery(query);
	}

	public ResultSet currentMatches(int user) throws SQLException {
		String query = String.format(
				"SELECT * FROM jungle.Match_State WHERE User1 = '%1$d' OR User2 = '%1$d'", user);
		return sendQuery(query);
	}
	
	public ResultSet allCurrentMatches() throws SQLException {
		String query = "SELECT * FROM jungle.Match_State WHERE State = 'in-progress';";
		return sendQuery(query);
	}
	
	public ResultSet suspendedMatches(String user) throws SQLException {
		String query = String.format(
				"SELECT * FROM jungle.Match_State WHERE User1 = '%1$s' OR User2 = '%1$s' AND State = 'suspended';",
				user);
		return sendQuery(query);
	}
	
	public ResultSet allSuspendedMatches() throws SQLException {
		String query = "SELECT * FROM jungle.Match_State WHERE State = 'suspended';";
		return sendQuery(query);
	}
	
	public ResultSet finishedMatches(String user) throws SQLException {
		String query = String.format(
				"SELECT * FROM jungle.Match_State WHERE User1 = '%1$s' OR User2 = '%1$s' AND State = 'finished';",
				user);
		return sendQuery(query);
	}
	
	public ResultSet allFinishedMatches() throws SQLException {
		String query = "SELECT * FROM jungle.Match_State WHERE State = 'finished';";
		return sendQuery(query);
	}
	
	public boolean matchOver(String winner, String loser, String gameId) {
		//update the matchstatus:
		boolean ret;
		String update = String.format("UPDATE jungle.Match_State SET State = 'finished' WHERE GameID = '%1$s';", gameId);
		ret = alter(update);
		//update the match record:
		update = String.format("INSERT INTO jungle.Match_Record (GameID, Winner, Loser) VALUES ('%1$s', '%2$s', '%3$s');", gameId, winner, loser);
		return ret && alter(update);
	}
	
	public ResultSet matchRecord(String user) throws SQLException {
		String query = String.format("SELECT * FROM jungle.Match_Record WHERE Winner = '%1$s' OR Loser = '%1$s';", user);
		return sendQuery(query);
	}
	
	public ResultSet allMatchRecords() throws SQLException {
		String query = "SELECT * FROM jungle.Match_Record;";
		return sendQuery(query);
	}
		
	
	public ResultSet winHistory(String username) throws SQLException {
		String query = String.format("SELECT * FROM jungle.Match_Record WHERE Winner = '%1$s';", username);
		return sendQuery(query);
	}
	
	public int wins(String username) throws SQLException {
		ResultSet rs = winHistory(username);
		if(!rs.first())
			return 0;
		rs.last();
		return rs.getRow();
	}
	
	public ResultSet lossHistory(String username) throws SQLException {
		String query = String.format("SELECT * FROM jungle.Match_Record WHERE Loser = '%1$s';", username);
		return sendQuery(query);
	}
	
	public int losses(String username) throws SQLException {
		ResultSet rs = lossHistory(username);
		if(!rs.first())
			return 0;
		rs.last();
		return rs.getRow();
	}

  public ResultSet retrieveMatch(String matchID) throws SQLException {
		String query = String.format("SELECT * FROM jungle.Match_State WHERE GameID = '%1$s';", matchID);
		return sendQuery(query);
	}
}
