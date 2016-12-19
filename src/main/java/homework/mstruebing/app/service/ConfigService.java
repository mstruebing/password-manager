package homework.mstruebing.app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Scanner;

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
	public boolean createDefaultConfig() {
		// default db settings
		String dbHost = "127.0.0.1";
		int dbPort = 3306;
		String dbUsername = "admin";
		String dbPassword = "pass";
		String dbName = "pw_stuff";


		Config config = new Config(dbHost, dbPort, dbUsername, dbPassword, dbName);

		// get the next free userid
		DatabaseService databaseService = new DatabaseService();
		int userID = databaseService.getNextUserId(config);
		config.setUserID(userID);

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
		
		// I do the parsing there anyway
		Config config = configRepository.getConfig();
		return configRepository.configExists() && config != null;
	}

	public boolean askToCreateDefaultConfig() {
		System.out.println("Should the program create a default config? [Y/n]");
		System.out.println("Caution: This will overwrite an existing one");
		Scanner scanner = new Scanner(System.in);

		char choice = Character.toUpperCase(scanner.next().charAt(0));
		if (choice == 'Y') {
			createDefaultConfig();
		}

		return true;
	}
}
