## Application tool
The following project is a little showcase for implementing API's in Java with Spring Boot.
The topic of it is job applications. Here you can add, update, delete and retrieve applicant data.
The applicant with id 1 is me with all the details of my CV.

## Setup
This project is made with Spring 3.0 and Java 17. If you are using Intellij and
haven't worked with Java 17 yet, please download the correct JDK under
File > Project Structure > Project > SDK > Add SDK and select the Java 17.

Besides that you need to change your Gradle settings to Java 17. That can be done via
Intellij IDEA > Preferences > Build, Execution, Deployments > Build Tools > Gradle.
There you have to change the value of Gradle JVM to Java 17.

## Tooling
The following tools have been chosen for the implementation. \
Lombok avoids Boilerplate code in entities. \
H2 database was chosen for simplicity, that you don't need to set up anything locally to test
the application.\
Flyway is used as higher level database migration tool.


## Testing
The application provides the following endpoints:

|  Endpoint  |         Description         | 
|:----------:|:---------------------------:|
| applicants |   List of all applicants    |
| applicants/id |  Detail page of applicant   |
| applicants/id/jobExperience | Job experience of applicant |
applicants/id/education |   Education of applicant    


All endpoints are added to a Postman Collection that you can easily test
it out yourself without spending too much time with searching for the correct request syntax.

## H2 DB
if you want to check the data on the H2 db, use the following credentials:\
username: user\
passwort: test

## init database
Flyway scripts are used for schema creation and the data.sql to add data.
