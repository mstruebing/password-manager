package homework.mstruebing.app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Service class to handle the config
 *
 */
public class ConfigService
{

	/**
	 * Creates a default config and saves it via repository
	 *
	 * @return boolean
	 */
	public static boolean createDefaultConfig() {
		// default settings
		// @TODO get real useable userid
		int userID = 1;
		String dbHost = "127.0.0.1";
		int dbPort = 3306;
		String dbUsername = "admin";
		String dbPassword = "pass";
		String dbName = "pw_stuff";

		Config config = new Config(userID, dbHost, dbPort, dbUsername, dbPassword, dbName);
		ConfigRepository configRepository = new ConfigRepository();
		
		return configRepository.save(config);
	}

	/**
	 * Checks if a config file exists and if it is valid
	 *
	 * @return boolean
	 */
	public boolean configIsValid() {
		ConfigRepository configRepository = new ConfigRepository();
		return configRepository.configExists() && true;
	}
}
