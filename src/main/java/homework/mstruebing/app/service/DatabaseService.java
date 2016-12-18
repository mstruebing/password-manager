package homework.mstruebing.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Functions to handle the database connection
 *
 */
public class DatabaseService
{
	/**
	 * Connects to the database
	 *
	 * @param Config
	 * @return Connection
	 */
	public Connection getConnection(Config config) {
		
		Connection connection = null;
		
		String url = "jdbc:mysql://" + config.getDbHost() + ":" + config.getDbPort() + "/" + config.getDbName();
		String user = config.getDbUsername();
        String password = config.getDbPassword();
        
        try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.err.println("ERROR: No database connection");
			System.err.println(e.getMessage());
		}
		
		return connection;
	}

	/**
	 * Closes the database connection
	 *
	 * @param Connection
	 */
	public void disconnect(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("No database connection");
		}
	}

	/**
	 * Tests the database connection
	 *
	 * @param Config
	 * @return boolean
	 */
	public boolean testConnection(Config config) {
		Connection connection = getConnection(config);

		if (connection != null) {
			disconnect(connection);
		} else {
			return false;
		}

		return true;
	}
}
