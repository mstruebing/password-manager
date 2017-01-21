package homework.mstruebing.app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.lang.reflect.Field;

/**
 * Repository for a config
 *
 */
public class ConfigRepository extends Repository<Config>
{

	// @TODO GET REAL CONFIG FILE
	protected final String configFile = System.getProperty("user.home").concat("/.pw_man_max.conf");

	/**
	 * Saves the config file to filesystem
	 *
	 * @param Config
	 * @return boolean
	 */
	@Override
	public boolean save(Config config)
	{
		JSONObject jsonConfig = new JSONObject();

		// Build up the json config object
		for (Field field : config.getClass().getDeclaredFields()) {
			try {
				// set cool sneaky accessible modifier to public :)
				field.setAccessible(true);
				Object value = field.get(config);
				if (value != null) {
					jsonConfig.put(field.getName(), value);
				}
			} catch (Exception e) {
				System.err.println("ERROR: " + e.getMessage());
				return false;
			}
		}

		// save the json config
		try {
			File file = new File(configFile);
			FileWriter writer = new FileWriter(file);
			writer.write(jsonConfig.toJSONString());
			writer.flush();
			writer.close();
			System.out.println("Successfully wrote config to " + configFile);
		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Deletes the config from filesystem
	 *
	 * @param config
	 * @return boolean
	 *
	 * @TODO Make use of config
	 */
	@Override
	public boolean remove(Config config)
	{
		try {
			File file = new File(configFile);
			file.delete();
		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
			return false;
		}

		return true;
	}


	/**
	 * Checks if a config file exists
	 *
	 * @return boolean
	 */
	public boolean configExists() {
		File file = new File(configFile);
		return file.isFile();
	}

	/**
	 * @TODO simplify parsing via reflection or smth?
	 *
	 * Returns a Config object with all properties set
	 * CAUTION: returns null if an exception occurs
	 *
	 * @return Config
	 */
	public Config getConfig()
	{
		// declare config due visibility
		Config config;

		try {
			JSONParser parser = new JSONParser();
			Object configObject = parser.parse(new FileReader(configFile));
            JSONObject configJsonObject = (JSONObject) configObject;

			// just because fucking org.json* left me no other choice
			int userID = ((Long) configJsonObject.get("userID")).intValue();
			int dbPort = ((Long) configJsonObject.get("dbPort")).intValue();

            String dbHost = (String) configJsonObject.get("dbHost");
			String dbUsername = (String) configJsonObject.get("dbUsername");
			String dbPassword = (String) configJsonObject.get("dbPassword");
			String dbName = (String) configJsonObject.get("dbName");

			config = new Config(userID, dbHost, dbPort, dbUsername, dbPassword, dbName);
        } catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
			config = null;
        }

		return config;
	}
}
