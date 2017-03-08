# Password-Manager

## Description

This is a CLI-tool written in java to manage passwords.
At the current state it handles different users and as a user you are able to add or remove passwords associated with your user.

## Setup, Build and Development

This tool was developed under usage of maven, git and make.
You need a MySQL database up and running and, if you fulfill
all requirements, could simply do a `make setup` in the project
root. This will install some githooks into the repository,
which are simply doing a `mvn test` when some java files are
changed before commiting, so that you are not be able
to commit with broken tests. Then it creates a database
called `pw_stuff` and imports a databasedump which
contains the raw database structure.
You should configure your editor to read the `.editorconfig`
from the project-root, which contains information about how to
format the code. There are plugins for most editors
