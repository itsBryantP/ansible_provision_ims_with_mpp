import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DB2Import {
	public static void main(String[] args) {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String db2Instance = args[0],	// The instance of DB2 where the table we want to load data to is located
				   db2Schema = args[1],		// Name of the DB2 schema under which the DB2 table is defined
				   db2Table = args[2],		// Name of the DB2 table
				   inputFile = args[3],		// The absolute path of the csv file containing the data to be imported
				   db2User = args[4],		// Username used to login to the DB2 server
				   db2Pass = args[5],		// Password used to login to the DB2 server
				   db2Url = args[6],		// URL of the DB2 server
				   db2Port = args[7];		// DB2 server port

			// The columns in the DB2 table
			String Cols = "TXID,TIMESTMP,TRANSTYP,AMOUNT,REFTXID,ACCID";
			
			// Construct the url to connect to the DB2 server
			String url = String.format("%1$s:%2$s/%3$s", db2Url, db2Port, db2Instance);

			// Create a JDBC connection using the contructed url
			Connection jdbcConn = DriverManager.getConnection(url, db2User, db2Pass);

			// Read the input file containing data
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			DB2Import db = new DB2Import();
			String line;
			
			jdbcConn.setAutoCommit(false);
			
			// Clear existing data from table
			db.runCommand(jdbcConn, String.format("DELETE FROM %1$s.%2$s", db2Schema, db2Table)); 
			
			// Insert each line in the input file into the table
			while((line = br.readLine()) != null){
				line = line.replace("\"", "'");
				String insert = String.format("INSERT INTO %1$s.%2$s(" + Cols + ") VALUES(" + line + ")", db2Schema, db2Table);
				db.runCommand(jdbcConn, insert);
			}
			br.close();
			jdbcConn.commit();
			jdbcConn.close();

		} catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			System.out.println("Exception: " + e);
			e.printStackTrace();
		} catch (FileNotFoundException e){
			System.err.println("File not found");
		} catch (IOException e){
			System.err.println("Exception: " + e);
		} catch (SQLException ex) {
			System.err.println("SQLException information");
			while (ex != null) {
				System.err.println("Error msg: " + ex.getMessage());
				System.err.println("SQLSTATE: " + ex.getSQLState());
				System.err.println("Error code: " + ex.getErrorCode());
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		}
	} // End main

	public void runCommand(Connection jdbcConn, String command){
		try{
			PreparedStatement presta = jdbcConn.prepareStatement(command);
			presta.executeUpdate();
			presta.close();
		} catch (SQLException ex) {
			System.err.println("SQLException information");
			while (ex != null) {
				System.err.println("Error msg: " + ex.getMessage());
				System.err.println("SQLSTATE: " + ex.getSQLState());
				System.err.println("Error code: " + ex.getErrorCode());
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		}
	}
}





