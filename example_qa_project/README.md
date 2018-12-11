# My example qa project

The project implements parallel automation testing on '/www.hbo.com' website.

## Prerequisites

 * Download Сhromedriver in usr/bin/ 
  
 * Download Firefoxdriver in usr/bin/
  
 * Install chrome 
  
 * Install firefox 

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
$ mvn test -DthreadCount=3 -Dos=Linux -Dbrowser=firefox -DbrowserVersion=63.0.3

//Run a class
$ mvn test -Dtest=MainPageTest -DthreadCount=3 -Dos=Linux -Dbrowser=firefox -DbrowserVersion=63.0.3

//Run a specific case
$ mvn test -Dtest=MainPageTest#verifyMainPageCentralImage -DthreadCount=3 -Dos=Linux -Dbrowser=firefox -DbrowserVersion=63.0.3

//Run a group.Only 'smoke' group is present.
$ mvn test -Dgroups=smoke -DthreadCount=3 -Dos=Linux -Dbrowser=firefox -DbrowserVersion=63.0.3

//Run with 2 browsers
$ mvn test -Dclasses=MainPageTest,CastAndCrewPageTest -DthreadCount=1 -Dos=Linux -Dbrowser=firefox,chrome -DbrowserVersion=63.0.3,69.0.3497.100 -Dgenerate=false

```
