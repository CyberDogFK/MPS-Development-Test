## MPS Development Test

## Description of project

The application is designed to calculate the drone's route. In this project, it is possible to create your own aircraft with the following characteristics:
- maximum speed, m/s
- maximum acceleration, m/s
- the maximum rate of course change, deg/s
- maximum rate of climb, m/s

Set routes for your drones and receive data about flight in every second.
And you can save you data in database. 

## Technologies

- Java 17
- Spring Boot 2
- Spring Data JPA
- MySQL
- Lombok

## Project Structure

I use N-tier architecture.

| Tier    | Description                                                     |
|---------|-----------------------------------------------------------------|
| Control | At this tier user can give orders for program                   |
| Service | Calculate all logic, and response for transfer data to DAO-tier |
| Dao     | Response for work with database                                 |

## What I'm actually done

- Saving data in database- I have use MySQL, because I have more experience with it  
- Creating airplanes with characteristics, flights with waypoints and temporary points
- Some logic of flight

## What goes wrong

- Calculating logic of flight... I think I have some mistake in calculating of degree between airplane, and waypoint.

I really hope we can meet and discuss the best way of calculating this logic.

## How to start

I recommend use for starting IDE you like
1. You need to download repo on your computer
2. In /src/resources/application.properties you must connect SQL Database,
add your driver, url to db, and login with password
3. Go to pom.xml and download all dependencies
4. If you start this application with IDE, you need to start file in package springboot, 
file Application Initializer 
5. Or you can use command line: mvn spring-boot:run



