package homework.mstruebing.app;

/**
 * A model which represents the config
 *
 */
public class Config 
{

	protected long userID;
	protected String dbHost;
	protected int dbPort;
	protected String dbUsername;
	protected String dbPassword;
	protected String dbName;

	public Config(long userID, String dbHost, int dbPort, String dbUsername, String dbPassword, String dbName) {
		this.userID = userID;
		this.dbHost = dbHost;
		this.dbPort = dbPort;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.dbName = dbName;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public long getUserID() {
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
