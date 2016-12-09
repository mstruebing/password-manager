# Compile command
COMPILE = mvn package

# Test command
TEST = mvn test

# Generate documentation command
DOC = mvn javadoc:javadoc

# Set source dir and scan source dir for all java files
SRC_DIR = src/main/java/homework/mstruebing/app/
SOURCES = $(shell find $(SRC_DIR) -type f -name '*.java')

# Set test dir and scan test dir for all java files
TEST_DIR = src/test/java/homework/mstruebing/app/
TEST_SOURCES = $(shell find $(TEST_DIR) -type f -name '*.java')


# Targets
all: clean docs start

target: $(SOURCES)
	$(COMPILE)

start: target
	java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App

docs: target
	$(DOC)

setup: Build/GitHooks/pre-commit
	cd .git/hooks && ln -sf ../../Build/GitHooks/pre-commit

test: $(SOURCES) $(TEST_SOURCES)
	$(TEST)

clean: 
	rm -Rf target

