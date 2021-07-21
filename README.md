# Islands on a map Application

Test application, created to work with the marine to fight pirates

## 5 Minute Getting Started

### Installation

First, build gradle project

    $ gradle build

### Start application

You can run your spring boot application from command line with:

    $ java -jar build/libs/islands-on-a-map-1.0-SNAPSHOT.jar

### Configuration

You can configure your application in \src\main\resources\application.yml
- start url - server.port, default - http://localhost:8088
- database - spring.datasource , default - H2
    
### Endpoint list

 - GET /swagger-ui.html#/   -  Swagger endpoint list    
 - GET /api/islands   -  Get all islands info           
 - GET /api/islands/{id}   -  Find island by id 
 - POST /api/maps   -  Load and save test Maps data
 - GET /api/maps/asciiMap   -  Get Ascii representation of first map in db
 - GET /api/maps/asciiMap/{id}   -  Get Ascii representation of map by id  
 - GET  /actuator   -  Spring boot actuator 
 
### Additional info

create tarball archive:

    $ git archive --format=tar.gz master > islands-on-a-map.tar.gz
    
create docker image:
    
    gradle build
    #run Dockerfile with exposed 8088 container port     
    