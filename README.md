# ParkLot

## Requirement

* JDK (Min 1.8)
* Apache Maven
* Mysql Database

## Setup

The following Environment Variable are required for starting up the application
* DATASOURCE_URL (Database connection url)
* DATASOURCE_USERNAME (Database username)
* DATASOURCE_PASSWORD (Database password)
* SEDAN_RATE (Sedan pricing policy hourly rate e.g 150) 
* ELECTRIC_20_RATE (Electric 20kw hourly rate e.g 200)
* ELECTRIC_20_FIXED (Electric 20kw Fixed amount e.g 250)
* ELECTRIC_50_RATE (Electric 50kw hourly rate e.g 300)
* ELECTRIC_50_FIXED (Electric 50kw fixed rate.g 500)



## Booting App
To run the application after setting the environment variables use
```javascript
mvn spring-boot:run
``` 

## Documentation
This service is accompanied with swagger documentation for easy interaction and testing. 
* [Swagger] http://localhost:8080/swagger-ui.html

### Guides 
Important models in the application and what they represent

* Slot: This is a space in the parking lot that a car/vehicle can be parked
* VehicleEntry: This is an individual car entry and exit which is billable.
* SlotType: This represent a type of a slot in the parking which represent what type of car can be parked in there.


### Todo
Implement Authentication using Oauth2
