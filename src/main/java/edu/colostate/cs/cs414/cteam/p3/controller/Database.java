package edu.colostate.cs.cs414.cteam.p3.controller;

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
		//System.out.println(search);
		Statement stQuery = conn.createStatement();
		return stQuery.executeQuery(search);
	}

	public int update(String update) throws SQLException {
		//System.out.println(update);
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
				"Password varchar(255) not null,\r\n" + 
				"Primary Key (Username))";
		update(create);
		create = "Create table if not exists jungle.Match_State (         \r\n" + 
				"				GameID int not null unique AUTO_INCREMENT,\r\n" + 
				"				User1 varchar(30) not null,\r\n" + 
				"				User2 varchar(30) not null,\r\n" + 
				"				State varchar(20) not null,\r\n" + 
				"				createTS Timestamp not null default current_timestamp,\r\n" + 
				"				Primary Key(GameID),\r\n" + 
				"				foreign key(User1) references User(Username),\r\n" + 
				"				foreign key(User2) references User(Username)\r\n" + 
				"				);";
		update(create);
		create = "CREATE TABLE if not exists jungle.Match_Record(\r\n" + 
				"				GameID int NOT NULL UNIQUE,\r\n" + 
				"				Winner varchar(30) NOT NULL,\r\n" + 
				"				Loser varchar(30) NOT NULL,\r\n" + 
				"				foreign key(Winner) references User(Username),\r\n" + 
				"				foreign key(Loser) references User(Username),\r\n" + 
				"				primary key(GameID)\r\n" + 
				"				);";
		update(create);
		create = "				Create table if not exists jungle.Invites (\r\n" + 
				"				Sender varchar(30) ,\r\n" + 
				"				Receiver varchar(30) ,\r\n" + 
				"				Status varchar(30) ,\r\n" + 
				"				foreign key(Sender) references User(Username),\r\n" + 
				"				foreign key(Receiver) references User(Username)\r\n" + 
				"				);";
		update(create);
	}
	
	public void resetDB() throws SQLException {
		dropTables();
		createTables();
	}

	public static void main(String[] args) throws SQLException {
		Database db = new Database();
		
		db.resetDB();
		
		//create four new users:
		db.register("user1", "pass", "email1");
		db.register("user2", "pass", "email2");
		db.register("user3", "pass", "email3");
		db.register("user4", "pass", "email4");
		
		//check deletion and login:
		System.out.println(db.login("user4", "pass") + " " + db.deleteAccount("user4", "pass") + " " + !db.login("user4", "pass"));
	
		//send invites to everyone from user1:
		System.out.println("true: " + db.sendInvite("user1", "user2", "pending"));
		System.out.println("true: " + db.sendInvite("user1", "user3", "pending"));
		System.out.println("false: " + db.sendInvite("user1", "user4", "pending"));
		System.out.println("false: " + db.sendInvite("user4", "user2", "pending"));
		
		//view invites received by user2:
		db.printRS(db.viewReceivedInvites("user2"));
		
		//view invites send by user1:
		db.printRS(db.viewSentInvites("user1"));
		
		//accept from u1 to u2:
		System.out.println(db.acceptInvite("user1", "user2"));
		//check that the invite was deleted
		db.printRS(db.viewSentInvites("user1"));
		//check that the game was created:
		db.printRS(db.suspendedMatches("user1"));
		ResultSet rss = db.suspendedMatches("user2");
		rss.next();
		int ID1v2 = rss.getInt(rss.findColumn("GameID"));
		
		//reject from u1 to u3:
		System.out.println(db.rejectInvite("user1", "user3"));
		//check that the invite was deleted
		db.printRS(db.viewSentInvites("user1"));
		//check that the game wasn't created:
		db.printRS(db.suspendedMatches("user3"));
		
		db.sendInvite("user1", "user3", "pending");
		db.acceptInvite("user1", "user3");
		ResultSet rss1 = db.suspendedMatches("user3");
		rss1.next();
		int ID1v3 = rss1.getInt(rss1.findColumn("GameID"));
		
		//get users:
		db.printRS(db.getUsers());
		
		//matches for a user, all matches:
		db.printRS(db.matches("user3"));
		db.printRS(db.allMatches());
		
		//current, all current:
		
		//suspended, all suspended:
		
		//finished, all finished:
		
		//matchOver:
		System.out.println(db.matchOver("user1", "user2", Integer.toString(ID1v2)));
		//check match status:
		db.printRS(db.finishedMatches("user2"));
		//check match record:
		db.printRS(db.matchRecord("user1"));
		
		db.matchOver("user1", "user3", Integer.toString(ID1v3));
		db.printRS(db.allMatchRecords());
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

	public boolean sendInvite(String sender, String receiver, String status) {
		String update = String.format(
				"INSERT INTO jungle.Invites (Sender, Receiver, Status) VALUES ('%1$s', '%2$s', '%3$s');", sender,
				receiver, status);
		return alter(update);
	}

	public ResultSet viewReceivedInvites(String user) throws SQLException {
		String query = String.format("SELECT * FROM jungle.Invites WHERE Receiver = '%1$s';", user);
		return sendQuery(query);
	}

	public ResultSet viewSentInvites(String user) throws SQLException {
		String query = String.format("SELECT * FROM jungle.Invites WHERE Sender = '%1$s';", user);
		return sendQuery(query);
	}
	
	public boolean acceptInvite(String sender, String receiver) {
		//delete the invite
		String update = String.format("DELETE FROM jungle.Invites WHERE Receiver = '%1$s' and Sender = '%2$s';", receiver, sender);
		boolean ret = alter(update);
		//create the game
		update = String.format("INSERT INTO jungle.Match_State (User1, User2, State) VALUES ('%1$s', '%2$s', 'suspended');", sender, receiver);
		return ret && alter(update);
	}
	
	public boolean rejectInvite(String sender, String receiver) {
		//delete the invite
		String update = String.format("DELETE FROM jungle.Invites WHERE Receiver = '%1$s' and Sender = '%2$s';", receiver, sender);
		return alter(update);
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

	public ResultSet currentMatches(String user) throws SQLException {
		String query = String.format(
				"SELECT * FROM jungle.Match_State WHERE User1 = '%1$s' OR User2 = '%1$s' AND State = 'in-progress';",
				user);
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
	
	public ResultSet allFinihedMatches() throws SQLException {
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
}
