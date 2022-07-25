# SpringTest
Java Spring Framework

# Installation
## Install Java JDK 
* Go : https://www.oracle.com/java/technologies/downloads/#jdk18-windows
* Download and install Java
* Set Environment Valiable > System valiable

| Valiable | Value |
| :-: | :-:|
| JAVA_HOME | C:\Program Files\Java\jdk-18.x.x |
| Path | %JAVA%\bin |

## Install Apache
* Go : https://maven.apache.org/download.cgi
* Download Binary zip archive
* Unzip to C:\Program Files\Apache
* Set Environment Valiable > System valiable

| Valiable | Value |
| :-: | :-:|
| MAVEN_HOME | C:\Program Files\Apache\apache-maven-3.x.x |
| Path | %MAVEN_HOME%\bin |

## Install Spring Boot CLI
* Go : https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/2.7.1/spring-boot-cli-2.7.1-bin.zip
* Unzip to C:\Program Files\Java
* Set Environment Valiable > System valiable

| Valiable | Value |
| :-: | :-:|
| SPRING_HOME | C:\Program Files\Java\spring-2.x.x |
| Path | %SPRING_HOME%\bin |

## Install Gradle 

## Install SDKMAN!

# Start
## Conifguation
* Add POM.xml
* Adding Classpath Dependencies
```
mvn dependency:tree
```

* Start with mvn
```
mvn spring-boot:run
```

* Start with jar
```
mvn package
java -jar target/myproject-0.0.1-SNAPSHOT.jar
```
