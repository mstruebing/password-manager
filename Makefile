COMPILER = mvn package

all: target start

target: src/main/java/homework/mstruebing/app/**.java
	mvn package

start:
	java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App

clean: 
	rm -Rf target

