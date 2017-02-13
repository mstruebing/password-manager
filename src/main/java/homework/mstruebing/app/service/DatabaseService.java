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
	 * @return Connection - the database connection
	 */
	protected Connection getConnection()
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
	 * @param Connection connection - the database connection
	 */
	protected void disconnect(Connection connection)
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
	 * @return boolean - whether the program is able to connect to the database or not
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
	 * @param Config config - the config
	 * @return int - the next useable user id
	 */
	public int getNextUserId()
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
			} catch (SQLException e) {
				System.err.println("ERROR" + e.getMessage());
			} finally {
				disconnect(connection);
			}
		}

		return -1;
	}

	public boolean executeStatement(String stmnt)
	{
		System.out.println(stmnt);
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
