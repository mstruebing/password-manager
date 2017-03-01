package homework.mstruebing.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Functions to handle the database connection
 *
 */
public class DatabaseService
{
	/**
	 * Connects to the database
	 *
	 * @return the database connection
	 */
	public Connection getConnection()
	{
		Connection connection = null;
		ConfigRepository configRepository = new ConfigRepository();
		Config config = configRepository.getConfig();

		String url = "jdbc:mysql://" + config.getDbHost() + ":" + config.getDbPort() + "/" + config.getDbName();
		String user = config.getDbUsername();
        String password = config.getDbPassword();

        try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.err.println("ERROR: " + e.getMessage());
		}

		return connection;
	}

	/**
	 * Closes the database connection
	 *
	 * @param connection the database connection
	 */
	public void disconnect(Connection connection)
	{
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Tests the database connection
	 *
	 * @return whether the program is able to connect to the database or not
	 */
	public boolean testConnection()
	{
		Connection connection = getConnection();

		if (connection != null) {
			disconnect(connection);
		} else {
			return false;
		}

		return true;
	}

	/**
	 * Get the next useable user id
	 *
	 * @return the next useable user id
	 */
	public int getNextUsableUserId()
	{
		Connection connection = getConnection();
		ResultSet rs = null;

		if (null != connection) {
			PreparedStatement pst = null;
			String stmnt = "INSERT INTO UserRepository VALUES()";
			String getLastInsertedId = "SELECT @id:=id AS id FROM UserRepository WHERE id = last_insert_id();";

			try {
				pst = connection.prepareStatement(stmnt);
				pst.executeUpdate();
				pst = connection.prepareStatement(getLastInsertedId);
				rs = pst.executeQuery();
				rs.next();
				return rs.getInt(1);
			} catch (SQLException e) {
				System.err.println("ERROR" + e.getMessage());
			} finally {
				disconnect(connection);
			}
		}

		return -1;
	}

	/**
	 * Executes a mysql statement
	 *
	 * @param stmnt the statement to execute
	 * @return whether the statement was Successfully executed or not
	 */
	public boolean executeStatement(String stmnt)
	{
		Connection connection = getConnection();

		if (null != connection) {
			PreparedStatement pst = null;

			try {
				pst = connection.prepareStatement(stmnt);
				pst.execute();
				return true;
			} catch (SQLException e) {
				System.err.println("ERROR: " + e.getMessage());
			} finally {
				disconnect(connection);
			}
		}

		return false;
	}
}
