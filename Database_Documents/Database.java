
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
   * Constructor. Establishes connection with database and queries it for available filters.
   */
  public Database(){
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
    } catch(Exception e){
      System.err.println("Exception in DB Construction: " + e.getMessage());
    }
  }
  
  /**
   * Searches the database with the given search.
   * @param search - SQL statement.
   * @return SQL ResultSet object containing results from the query.
   */
  public ResultSet sendQuery(String search){
    System.out.println(search);
    try {
      Statement stQuery = conn.createStatement();
      return stQuery.executeQuery(search);
    } catch (Exception e) {
      System.err.println("Exception in sending query: " + e.getMessage());
    }
    return null;
  }
}
