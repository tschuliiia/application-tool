## Application tool
The following project is a little showcase for implementing API's in Java with Spring Boot.
The topic of it is job applications. Here you can add, update, delete and retrieve applicant data.
The applicant with id 1 is me with all the details of my CV :) \
In order that it is not only some basic endpoints which retrieve data, I implemented an applicant battle  
to have a little more logic in the background. What does that mean? Two applicants can battle against 
each other and based on skills & education and a random value a winner is determined. Of course there is
also a leadership board. These little enhancements make also unit and integration testing more interesting.

## Setup
This project is made with Spring 3.0 and Java 17. If you are using Intellij and
haven't worked with Java 17 yet, please download the correct JDK under
File > Project Structure > Project > SDK > Add SDK and select the Java 17.

Besides that you need to change your Gradle settings to Java 17. That can be done via
Intellij IDEA > Preferences > Build, Execution, Deployments > Build Tools > Gradle.
There you have to change the value of Gradle JVM to Java 17.

## Run the project
If you want to run the project, you need to execute the following command: \
./gradlew bootRun \

## Tooling
The following tools have been chosen for the implementation. \
Lombok avoids Boilerplate code in entities. \
H2 database was chosen for simplicity, that you don't need to set up anything locally to test
the application.\
Flyway is used as higher level database migration tool.


## Testing
The application provides the following endpoints:

|          Endpoint           |          Description           | 
|:---------------------------:|:------------------------------:|
|         applicants/         |     List of all applicants     |
|         applicants/         |     Create new applicants      |
|        applicants/id        |    Detail page of applicant    |
| applicants/id/jobExperience |  Job experience of applicant   |
|   applicants/id/education   |     Education of applicant     |
|           battle/           | Post Request to fight a battle |
|   battle/leadershipBoard    |  Leadership board of battles   |


All endpoints are added to a Postman Collection that you can easily test
it out yourself without spending too much time with searching for the correct request syntax.
Just download Postman and import the Postman Collection. If you want to try out more custom, 
you can use the ids from 1 to 5 to retrieve data or perform battles.

## H2 DB
if you want to check the data on the H2 db, use the following credentials:\
username: user\
passwort: test

## init database
Flyway scripts are used for schema creation and the data.sql to add data.

