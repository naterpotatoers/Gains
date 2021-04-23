package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDatabase implements Database {
	private String host;
	private String database;
	private String username;
	private String password;
	public Connection mySqlConnection;
	
	MySQLDatabase(String host, String database, String username, String password){
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
	}

	@Override
	public Connection connectDatabase() throws SQLException {
    	System.out.print("Connecting to " + this.host + " MySQL database... ");
		mySqlConnection = DriverManager.getConnection("jdbc:mysql://" + host + database, username, password);
		if (mySqlConnection == null)
		{
			System.out.println("Unable to connect to database!");
		}
		System.out.println("Connected!");
		return mySqlConnection;
	}

	@Override
	public void closeDatabase() {
		try {
			System.out.println("Closing connection to " + this.host + " MySQL database...");
			mySqlConnection.close();
		} catch (SQLException e) {
			sqlExceptionError(e);
		}
	}

	@Override
	public void sqlExceptionError(SQLException e) {
		System.err.format("SQL State: %s\n%s\n", e.getSQLState(), e.getMessage());
	}
	
	public ResultSet queryStatement(String sql) {
		try (Connection connection = connectDatabase()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			return result;
		} catch (SQLException e) {
			sqlExceptionError(e);
		}
		return null;
	}
}
