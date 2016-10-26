K15t Practical Assessment Task - PAT 
------------------------------------

## What is it about?
Here we have a small web application allowing to register for the next Java Meetup.
Unfortunately the most important part of the app is still missing. The form 
fields as well as the backend to store the data in memory. But you are here to 
help us out and get it done.
 
## But *what* should I do exactly?
Extend the form with the required fields (see registration.vm for further details) and 
store the entered information by using a REST endpoint. Giv the user feedback if the
save was successful or in case of an error. Ensure mandatory fields will be entered
and verify the entered values are reasonable e.g. the name must not contains numbers.

To start with, please see the already created files and classes. Especially:

* com.k15t.pat.registration: The package includes a REST endpoint and a controller
* resources/templates: The folder includes the initial velocity templates for the registration page 

The Maven build creates a executable jar which includes the whole runtime (tomcat) to run the app.
You can start it with java -jar registration-0.1.0.jar. If the application is started the pages are
available under http://localhost:8080/registration.html

## A few words about the technology stack
The application is build on top of Spring Boot (http://projects.spring.io/spring-boot/) providing a runtime container. 
Furthermore Jersey for implementing REST resources, Velocity for templating pages and jQuery/Bootstrap is included and 
can be used as well. Building and packaging the application is done with Maven. 

## What's expected of me?
When our engineers receive your final result, we'll be looking at the following things:

* The ability to build it out of the box using maven
* Improvements you made around the main task  
* The quality and style of code written
* The tests and their structure and coverage
* The choice of technologies used to complete the task. You are free to use what every you think is needed and helps you to get it done!
* The documentation provided (mainly javadoc). Please consider also to document assumptions or decisions you made.  

Typically we expect it to compile and run on a Mac/Windows environment with Java 8. If your set up is any different, do let us know!
If you are done, please build the project and send us the jar and the source jar created in the target folder.  

Good luck!
