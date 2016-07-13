K15t Practical Assessment Task - PAT 
------------------------------------
## What's this?
This is a small app allowing users to register over a website for the next Java Meetup.  

## What do you want me to do?
Unfortunately the most important part of the app is currently missing. The form fields as well as the backend to store the data in memory.
But you are here to help us and to get it done.
 
## But *what* should I do exactly?
Extend the form with the desired fields (see registration.vm for further details) and store it by calling a REST endpoint. Respond with a
proper success message including the entered data for verification. 

To start with, please see the already created files/classes. Especially:
- com.k15t.pat.registration: The package includes a REST endpoint and a controller
- resources/templates: The folder includes the initial velocity templates for the registration page 

You can start the app with java -jar registration-0.1.0.jar. After that the page is available under: http://localhost:8080/registration.html

## A few words to the used technology stack
The app is build on top of Spring Boot (http://projects.spring.io/spring-boot/) as runtime container. Furthermore Jersey is used for 
building the REST resource and Velocity for templating the pages. To build the UI, jQuery and Bootstrap is already integrated. 

## What's expected of me?
When our engineers receive this, we'll be looking at the following things:

* The ability to build it out of the box using maven
* The quality and style of code written
* The tests and their structure and coverage
* The choice of technologies used to complete the task
* The documentation provided (javadoc). Please consider also to document assumptions pr decisions you made.  

Typically we expect it to compile and run on a MAC/Windows environment with Java 8. If your set up is any different, do let us know!
If you are done, please build the project and send us the jar and the source jar created in the target folder.  

Good luck!
