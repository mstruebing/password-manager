# Compile command
COMPILER = mvn package

# Set source dir and scan source dir for all java files
SRC_DIR = src/main/java/homework/mstruebing/app/
SOURCES := $(shell find $(SRC_DIR) -name '*.java')

all: target start

target: $(SOURCES)
	mvn package

start:
	java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App

setup: Build/GitHooks/pre-commit
	cp -f Build/GitHooks/pre-commit .git/hooks/

clean: 
	rm -Rf target

