# Meetup Registration Application


## Environment Setup

### Software
* JDK-1.8
* Tomcat 9 web server
* Apache Maven

 
## Deployment instructions

* `git clone https://github.com/venkdev/meetup-registration-app.git`  
* Open command prompt, go to the root directory of the cloned repository.
* Execute command `mvn clean install`
* Post successful build, execute `java -jar target/k15t-full-stack-dev-tasks-0.1.0.jar com.k15t.pat.ApplicationBootstrap`
* Launch localhost:8080 in your browser. Registration page will be rendered.

## Application
* The landing page will display a form. 
* Fill in the form with necessary information. If the data does not meet the validation conditions, validation errors will be thrown.
* Clicking on submit would redirect you to Success/Failure page.
* User trying to register again with the same mail id is will be considered as duplicate registration and is not allowed.   
 

### `/users` Endpoint
* To get the list of users who have already registered for the meetup. Currently accessible by everyone, no authentication mechanism in place.

### Architecture
* Spring boot application
* Rest controllers
* In memory H2 database to store the participants
* Junit tests

Removed Jersey configuration as Rest calls can be handled by already existing Spring boot web dependencies.
