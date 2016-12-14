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

	protected final String configFile = System.getProperty("user.home").concat("/.pw_man_max.conf");

	public boolean configExists() {
		File file = new File(configFile);
		return file.isFile();
	}

	public boolean configIsValid() {
		return configExists() && true;
	}

	public Config getConfig() {
		return new Config();
	}

	public boolean createDefaultConfig() {
		JSONObject obj = new JSONObject();
		obj.put("Name", "crunchify.com");
		obj.put("Author", "App Shah");
 
		JSONArray company = new JSONArray();
		company.add("Compnay: eBay");
		company.add("Compnay: Paypal");
		company.add("Compnay: Google");
		obj.put("Company List", company);
 
		try (FileWriter file = new FileWriter(configFile)) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		} catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
			return false;
		}

		return true;
	}

	private boolean parseConfig() {
		JSONParser parser = new JSONParser();

		try {
			Object configObject = parser.parse(new FileReader(configFile));
 
            JSONObject configJsonObject = (JSONObject) configObject;
 
            String name = (String) configJsonObject.get("Name");
            String author = (String) configJsonObject.get("Author");
            JSONArray companyList = (JSONArray) configJsonObject.get("Company List");
 
            System.out.println("Name: " + name);
            System.out.println("Author: " + author);
            System.out.println("\nCompany List:");
 
        } catch (Exception e) {
            e.printStackTrace();
        }

		return true;
	}
}
