package homework.mstruebing.app;

/**
 * https://commons.apache.org/proper/commons-cli/
 */
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;


/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) throws Exception
    {

		ConfigService configService = new ConfigService();

		if (!configService.configIsValid()) {
			System.out.println("Your config file doesn't exist or isn't valid!");
			configService.askToCreateDefaultConfig();
		} 

		ConfigRepository configRepository = new ConfigRepository();
		Config config = configRepository.getConfig();

		// just in case something has manipulated the config file in between hrhr
		if (config == null) {
			System.out.println("Your config was modified since the program started and isn't anymore valid!");
			System.out.println("Start the program again to create a default config or edit the config manually and start again.");
			System.exit(1);
		}
		
		DatabaseService databaseService = new DatabaseService();
		
		if (!databaseService.testConnection(config)) {
			System.err.println("ERROR: Can't connect to database");
			System.err.println("Exiting ...");
			System.exit(1);
		}

		
		Options options = new Options();

		// short opt
		// int opt
		// argument after
		// description
//        Option input = new Option("i", "input", true, "input file path");
//        input.setRequired(true);
//        options.addOption(input);

		Option add = new Option("a", "add", false, "add a new password");
		Option generate = new Option("g", "generate", false, "outputs a generated password");

		options.addOption(add);
		options.addOption(generate);


        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("ERROR: " + e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
			return;
        }

        boolean generatePassword = cmd.hasOption("generate");
        System.out.println(generatePassword);
		if (generatePassword) {
			EncryptionService encryptionService = new EncryptionService();
			System.out.println(encryptionService.generatePassword());
		}
    }
}
