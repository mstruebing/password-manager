package homework.mstruebing.app;

/**
 * A model which represents the config
 *
 */
public class Config
{
	protected int userID;
	protected String dbHost;
	protected int dbPort;
	protected String dbUsername;
	protected String dbPassword;
	protected String dbName;

	/**
	 * Constructor for the Config class without an userID
	 *
	 * @param dbHost the ip or name where to find the database
	 * @param dbPort the port on which to access the database
	 * @param dbUsername the username to log in with
	 * @param dbPassword the password to log in with
	 * @param dbName the name of the database to use
	 */
	public Config(String dbHost, int dbPort, String dbUsername, String dbPassword, String dbName) {
		this.dbHost = dbHost;
		this.dbPort = dbPort;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.dbName = dbName;
	}

	/**
	 * Constructor for the Config class with an userID
	 *
	 * @param userID the userID of the user
	 * @param dbHost the ip or name where to find the database
	 * @param dbPort the port on which to access the database
	 * @param dbUsername the username to log in with
	 * @param dbPassword the password to log in with
	 * @param dbName the name of the database to use
	 */
	public Config(int userID, String dbHost, int dbPort, String dbUsername, String dbPassword, String dbName) {
		this.userID = userID;
		this.dbHost = dbHost;
		this.dbPort = dbPort;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.dbName = dbName;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbHost() {
		return this.dbHost;
	}

	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}

	public int getDbPort() {
		return this.dbPort;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbUsername() {
		return this.dbUsername;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbPassword() {
		return this.dbPassword;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbName() {
		return this.dbName;
	}
}
