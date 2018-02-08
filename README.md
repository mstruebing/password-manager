# Password-Manager

## Description
This is a CLI-tool written in java to manage passwords.
At the current state it handles different users and as a user you are able
to add or remove passwords associated with your user.

## Goal
The goal for me was to write something like a repository which can be used
for different models without reimplementing logic.
See: src/main/java/homework/mstruebing/app/framework/Repository.java.

The second goal was to use domain-driven-design.
Domain driven design is just a principle, not a direct specification how to organize
your code or something. Sadly I had to find out that this project is not big enough
to get the relevant benefits that domain-driven-design could give you.

## Setup, Build and Development
This tool was developed with usage of maven, git and make.
You need a MySQL database up and running and, if you fulfill
all requirements, could simply do a `make setup` in the project
root. This will install some githooks into the repository,
which are simply doing a `mvn test` when some java files are
changed before commiting, so that you are not be able
to commit with broken tests, you can find them in Build/GitHooks.
Then it creates a database called `pw_stuff` and imports a databasedump which
contains the raw database structure which can be found in Build/Database.
You should configure your editor to read the `.editorconfig`
from the project-root, which contains information about how to
format the code for consistency(spaces, tabs etc.). There are plugins for most editors.

## Dependencies
Junit - I use junit for tests even if there are only some minor utility-function tests  
[commons-cli](https://commons.apache.org/proper/commons-cli/) - to parse CLI-Arguments  
[json-simple](https://code.google.com/archive/p/json-simple/) - to parse and manipulate/create json files  
[mysql-connector-java](https://mvnrepository.com/artifact/mysql/mysql-connector-java) - just because  
javadoc - to create a documentation out of code  
[errorprone](http://errorprone.info/) - for static code-analysis  
[shade-plugin](https://maven.apache.org/plugins/maven-shade-plugin/) - to bundle everything together for runtime  

## Compiling and execution
Compiling can be done simply via `make`
Or with `mvn package`

After that the application can be run via
`java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App`

With the `-h` or `--help` param at the end you get a cool help text.

HELP:
```
usage: utility-name
 -a,--add              add a new password
 -g,--generate         use an auto generated password
 -p,--password <arg>   your password
 -r,--remove <arg>     remove a password
 -s,--service <arg>    which service/url the password is used for
 -u,--username <arg>   your username
```

service and username are required after the add option, also a password XOR the generate option

`java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App -a -s "google" -u "max" -g`
`java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App -a -s "google" -u "max" -p "password"`

With no parameter given the passwordlist from your user will be displayed.

After the first execution you will find a file called `.pw_man_max.conf` in your home directory. - I don't know where it is located under Windows
but maybe in your 'Eigene Dateien' directory.
This file contains database connection credentials and your user id.

## Trivia
At the current state I wrote 1394 LoC(Lines of Code) `wc -l src/**/*.java`     
I created a `tests.sh` file to tests the error codes in case of falsy user input.     
There is a Makefile which I wrote because I didn't want to remember what maven commands I have to use     
With `make docs` javadoc would create a documentation and place it in `Documentation/apidocs`    
I don't like Java.
