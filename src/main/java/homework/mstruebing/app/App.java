package homework.mstruebing.app;

import java.util.ArrayList;

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
	protected static final short PARAMETER_ERROR = 1;
	protected static final short PARAMETER_PARSING_ERROR = 2;
	protected static final short DATABASE_ERROR = 3;
	protected static final short UNKNOWN_ERROR = 255;

	public static void main(String[] args) throws Exception
	{
		ConfigService configService = new ConfigService();

		if (!configService.configIsValid()) {
			System.out.println("Your config file doesn't exist or isn't valid!");
			configService.askToCreateDefaultConfig();
		}

		ConfigRepository configRepository = new ConfigRepository();
		Config config = configRepository.getConfig();

		DatabaseService databaseService = new DatabaseService();

		if (!databaseService.testConnection()) {
			exit(DATABASE_ERROR, "Can't connect to database", null);
		}

		if (config.getUserID() == -1) {
			int userID = databaseService.getNextUsableUserId();

			// the legendary 'this should never happen' comment
			if (userID == -1) {
				exit(UNKNOWN_ERROR, "Something really bad happend while determining the next user id", null);
			}

			config.setUserID(userID);
			configRepository.save(config);
		}

		UserRepository userRepository = new UserRepository();
		User user = userRepository.findById(config.getUserID());

		PasswordListRepository passwordListRepository = new PasswordListRepository();

		if (user == null) {
			user = new User(config.getUserID());

			// until a user have not more than one passwordlists this is working
			PasswordList passwordList = new PasswordList(user.getId(), user);
			user.setPasswordList(passwordList);
			userRepository.save(user);
			passwordListRepository.save(passwordList);
		}

		Options options = new Options();
		Option addOption = new Option("a", "add", false, "add a new password");
		Option usernameOption = new Option("u", "username", true, "your username");
		Option passwordOption = new Option("p", "password", true, "your password");
		Option serviceOption = new Option("s", "service", true, "which service/url the password is used for");
		Option removeOption = new Option("r", "remove", true, "remove a password");
		Option generateOption = new Option("g", "generate", false, "use an auto generated password");

		options.addOption(addOption);
		options.addOption(usernameOption);
		options.addOption(passwordOption);
		options.addOption(serviceOption);
		options.addOption(removeOption);
		options.addOption(generateOption);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			exit(PARAMETER_PARSING_ERROR, e.getMessage(), options);
		}

		boolean add = cmd.hasOption("add");
		boolean newUsername = cmd.hasOption("username");
		boolean newPassword = cmd.hasOption("password");
		boolean newService = cmd.hasOption("service");
		boolean remove = cmd.hasOption("remove");
		boolean generatePassword = cmd.hasOption("generate");

		if (add && newUsername && newService && !remove) {
			EncryptionService encryptionService = new EncryptionService();
			String plainPassword = null;

			if (newPassword && !generatePassword) {
				plainPassword = cmd.getOptionValue("password");
			} else if (!newPassword && generatePassword) {
				plainPassword = encryptionService.generatePassword();
			} else {
				exit(PARAMETER_ERROR, "you need to specify a password OR to generate one", options);
			}

			PasswordRepository passwordRepository = new PasswordRepository();
			Password password = new Password(
					user.getPasswordList(),
					cmd.getOptionValue("service"),
					cmd.getOptionValue("username"),
					encryptionService.encrypt(plainPassword));

			passwordRepository.save(password);
		} else if (remove && !add) {
			System.out.println( "Some remove stuff" );
		} else if (args.length == 0){
			PasswordRepository passwordRepository = new PasswordRepository();
			ArrayList<Password> passwords = passwordRepository.findByUserId(user.getId());
			printPasswords(passwords);
		} else {
			exit(PARAMETER_ERROR, "Don't know what to do", options);
		}
    }

	/**
	 * A helper function to exit the program
	 *
	 * @param exitCode the exit code to return to the caller
	 * @param customErrorMessage an errormessage which will be printed on stderr if defined
	 * @param options options to print the help output
	 */
	protected static void exit(short exitCode, String customErrorMessage, Options options)
	{
		if (customErrorMessage != null) {
			System.err.println("ERROR: " + customErrorMessage);
		}

		if (options != null) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("utility-name", options);
		}

		System.exit(exitCode);
	}

	protected static void printPasswords(ArrayList<Password> passwords) {
		EncryptionService encryptionService = new EncryptionService();
		for (int i = 0; i < passwords.size(); i++) {
			System.out.println("#" + i);
			System.out.println("\tService: " + passwords.get(i).getTitle());
			System.out.println("\tUsername: " + passwords.get(i).getUsername());
			System.out.println("\tPassword: " + encryptionService.decrypt(passwords.get(i).getPassword()));
		}
	}
}
