package application;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {
	/**
	 * Establishes initial database connection
	 * @return 
	 * @throws SQLException
	 */
	public Connection connectDatabase() throws SQLException;
	public void closeDatabase();
	public void sqlExceptionError(SQLException e);
}
