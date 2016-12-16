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

		UserRepository userRepository = new UserRepository();
		User user = new User();
		userRepository.save(user);

        System.out.println( "Hello World!" );

		ConfigService configService = new ConfigService();
		System.out.println(configService.configExists());
		configService.createDefaultConfig();

		Options options = new Options();

		// short opt
		// long opt
		// argument after
		// description
        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

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

        String inputFilePath = cmd.getOptionValue("input");
        System.out.println(inputFilePath);
    }
}
