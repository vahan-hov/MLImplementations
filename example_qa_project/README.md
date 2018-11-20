# My example qa project

The project implements parallel automation testing on 'automationpractice.com' website.

## Prerequisites

 * Install Сhromedriver in usr/bin/ 

 * Install java 

 * Install maven

 * Install node
 
 * Install selenium-server-standalone-3.13.0.jar (or another version)


 * Navigate to the location of selenium-server-standalone-3.13.0.jar and run the following commands:
```bash
    $ java -jar selenium-server-standalone-3.13.0.jar -role hub

    $ java -jar selenium-server-standalone-3.13.0.jar -role node -hub http://localhost:4444/grid/register

```

## Usage

```
//run all tests
$ mvn test

//Run a class
$ mvn test -Dtest=CLASS_NAME

//Run a specific case
$ mvn test -Dtest=CLASS_NAME#METHOD_NAME

//Run a group.Only 'smoke' group is present.
$ mvn test -Dgroups=smoke
```