# API-Booking

## Abstract


This is an booking API for the very last hotel in Cancun :
- the hotel has only one room available
- the stay can’t be longer than 3 days and can’t be reserved more than 30 days in advance.
- all reservations start at least the next day of booking,
- to simplify the use case, a “DAY’ in the hotel room starts from 00:00 to 23:59:59.
- every end-user can check the room availability, place a reservation, cancel it or modify it.
- to simplify the API is insecure.


In this API, it is possible to:
- Create a booking.
- Get a booking by its id.
- Get all bookings.
- Delete a booking.
- Update a booking.

##  Technologies
This application is build with Spring Boot for implementing a RESTful backend, and Angular for creating a JavaScript-based frontend.


### Prerequisites:
[java 8](https://www.java.com/fr/) , [maven](https://maven.apache.org/download.cgi) , [mongoDB](https://www.mongodb.com/fr-fr), [Nodejs](https://nodejs.org/en/download/) , [Angular CLI](https://docs.docker.com/get-docker/)

### Technologies Backend:
- java
- Spring Boot2
- MongoDB
- Swagger
- Junit 4
- Mockito
### Technologies Frontend:
- HTML 5
- CSS 3
- Bootstrap
- Angular 11

# Installation and Run application

1- before starting the application, you must create a database in mongoDB named booking

2- To install this API , run the following commands :
* *git clone https://github.com/fderbel/bookingAPI.git 

3- To run the server , cd into the backend folder and run : 
* 	*mvn clean install * (this command will also run all tests developed in the application)
*  *mvn spring-boot:run 
*  *run swagger in http://localhost:8080/swagger-ui.html#/

4- To run the client , cd into frontend folder and run 
* *npm install 
* *ng serve 
