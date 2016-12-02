# Compile command
COMPILER = mvn package

# Set source dir and scan source dir for all java files
SRC_DIR = src/main/java/homework/mstruebing/app/
SOURCES := $(shell find $(SRC_DIR) -name '*.java')

# Set source dir and scan source dir for all java files
TEST_DIR = src/test/java/homework/mstruebing/app/
TEST_SOURCES := $(shell find $(TEST_DIR) -name '*.java')

all: target start

target: $(SOURCES)
	mvn package

start:
	java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App

setup: Build/GitHooks/pre-commit
	cp Build/GitHooks/pre-commit .git/hooks/

test: $(SOURCES) $(TEST_SOURCES)
	mvn test

clean: 
	rm -Rf target

