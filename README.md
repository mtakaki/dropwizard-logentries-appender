### Status
![Build Status](https://codeship.com/projects/c895ce00-bdce-0133-0ed3-2a1d867cc1c8/status?branch=master)
[![Coverage Status](https://coveralls.io/repos/mtakaki/dropwizard-logentries-appender/badge.svg?branch=master&service=github)](https://coveralls.io/github/mtakaki/dropwizard-logentries-appender?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/80402d0e0f764dc29670e50d2418eee5)](https://www.codacy.com/app/mitsuotakaki/dropwizard-logentries-appender)
[![Download](https://maven-badges.herokuapp.com/maven-central/com.github.mtakaki/logentries-appender/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.mtakaki/logentries-appender)
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/com.github.mtakaki/logentries-appender/badge.svg)](http://www.javadoc.io/doc/com.github.mtakaki/logentries-appender)

# Dropwizard Logentries Log Appender
This project provides integration of [logentries](https://logentries.com) log appender to [dropwizard](http://www.dropwizard.io/) framework, so it can be referenced in dropwizard's configuration YML.

[Project lombok](https://projectlombok.org/) was used to auto-generate the getter and setters, so you will need to have lombok plugin installed in your eclipse/intellij if you want to import the source code.

This library currently supports dropwizard version `0.9.2`.

## Maven Configuration
This library is available at central maven, so you only need to add this to your dependencies:

```java
<dependencies>
  <dependency>
    <groupId>com.github.mtakaki</groupId>
    <artifactId>logentries-appender</artifactId>
    <version>0.0.3</version>
  </dependency>
</dependencies>
```

## Dropwizard Configuration
Without data hub:

```yaml
logging:
  level: INFO
  appenders:
    - type: logentries
      threshold: WARN
      token: your_token_here
```

If data hub is used:

```yaml
logging:
  level: INFO
  appenders:
    - type: logentries
      threshold: WARN
      token: your_token_here
      useDataHub: true
      dataHubAddress: your_datahub_address
      dataHubPort: datahub_port
```
