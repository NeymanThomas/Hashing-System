# Hashing-System
This project is a simple implementation of a password hashing algorithm written in Java. Its purpose is to show how passwords are hashed, stored, and the importance of not keeping sensitive information in plain text. The hashes and plain text are stored in JSON files for easy readability and editing purposes. This project was made for educational purposes for class 4416 - Cybersecurity and Resilience in Fall 2021.

## Installation
This program uses Gradle as its build tool and requires it to be executed properly. Gradle will need to be installed and its distributions can be found here: [Distributions.](https://services.gradle.org/distributions/)
The project is composed of one module called `program`. It handles all of the functionality of the application. Using the command 

```bash 
$ gradle :program:assemble
```

will produce the files `program/build/distributions/Hashing-System-1.0-SNAPSHOT` for `.zip` and `.tar`. This installation can be unpacked wherever you wish to run the application.

## Usage
The application can be executed in two main ways.

### No Installation
If the installation step was not followed, use the following commands to run the application:

```bash 
$ gradle :program:run
```
or if gradle is not installed on your system:

```bash
$ gradlew :program:run
```
