# My example qa project

The project implements automation testing on 'automationpractice.com' website.

## Prerequisites

Сhromedriver should be installed in usr/bin/ (other locations included in your PATH environment variable might also work. Otherwise you can add the following line in 'beforclass' method.

```
System.setProperty("webdriver.chrome.driver", “/path/to/chromedriver");
```


## Usage

```java
//run all tests
$ mvn test

//Run a class
$ mvn test -Dtest=CLASS_NAME

//Run a specific case
$ mvn test -Dtest=CLASS_NAME#METHOD_NAME

//Run a group.Only 'smoke' group is present.
$ mvn test -Dgroups=smoke
```