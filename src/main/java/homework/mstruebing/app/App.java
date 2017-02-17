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
// @TODO ERRORCODES
public class App
{
	public static void main(String[] args) throws Exception
	{
		ConfigService configService = new ConfigService();

		if (!configService.configIsValid()) {
			System.err.println("Your config file doesn't exist or isn't valid!");
			configService.askToCreateDefaultConfig();
		}

		ConfigRepository configRepository = new ConfigRepository();
		Config config = configRepository.getConfig();

		DatabaseService databaseService = new DatabaseService();

		if (!databaseService.testConnection()) {
			System.err.println("ERROR: Can't connect to database");
			System.exit(1);
		}

		if (config.getUserID() == -1) {
			int userID = databaseService.getNextUsableUserId();

			// the legendary 'this should never happen' comment
			if (userID == -1) {
				System.err.println("ERROR: Something really bad happend while determining the next user id");
				System.exit(1);
			}

			config.setUserID(userID);
			configRepository.save(config);
		}

		UserRepository userRepository = new UserRepository();
		User user = userRepository.findById(config.getUserID());

		PasswordListRepository passwordListRepository = new PasswordListRepository();
		PasswordRepository passwordRepository = new PasswordRepository();

		if (user == null) {
			user = new User(config.getUserID());

			// until a user have not more than one passwordlists this is working
			PasswordList passwordList = new PasswordList(user.getId(), user);
			user.setPasswordList(passwordList);
			userRepository.save(user);
			passwordListRepository.save(passwordList);
		}

		Password password = new Password(1, user.getPasswordList(), "Google", "name", "1234");
		passwordRepository.save(password);

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
		// System.out.println(generatePassword);
		if (generatePassword) {
			EncryptionService encryptionService = new EncryptionService();
			// System.out.println(encryptionService.generatePassword());
		}
    }
}
