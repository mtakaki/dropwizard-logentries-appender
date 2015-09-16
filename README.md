### Status
[![Build Status](https://travis-ci.org/mtakaki/dropwizard-logentries-appender.svg?branch=master)](https://travis-ci.org/mtakaki/dropwizard-logentries-appender)

# Dropwizard Logentries Log Appender
This project provides integration of logentries log appender to [dropwizard](http://www.dropwizard.io/) framework, so it can be referenced in dropwizard's configuration YML.

[Project lombok](https://projectlombok.org/) was used to auto-generate the getter and setters, so you will need to have lombok plugin installed in your eclipse/intellij if you want to import the source code.

This library currently supports dropwizard version `0.8.2`.

## Maven Configuration
In order to add this functionality to your dropwizard project, you will need to add reference to this library:

```
...
<repositories>
  <repository>
     <id>bintray</id>
     <url>http://dl.bintray.com/mtakaki/maven</url>
     <releases>
       <enabled>true</enabled>
     </releases>
     <snapshots>
       <enabled>false</enabled>
     </snapshots>
   </repository>
</repositories>
...
<dependencies>
  <dependency>
    <groupId>com.mtakaki</groupId>
    <artifactId>logentries-appender</artifactId>
    <version>0.0.1</version>
  </dependency>
</dependencies>
```

## Dropwizard Configuration

```
logging:
  level: INFO
  appenders:
    - type: logentries
      threshold: WARN
      token: your_token_here
```
