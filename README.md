## Spring Webflux and Reactive Redis 

## Prerequisites

Docker should be installed and running

## RampUp

Starts the application and waits till all containers become healthy and all exposed TCP ports are open
(so till the application is ready). It reads assigned host and ports of particular containers and stores them
into dockerCompose.servicesInfos property.
```bash
gradle composeUp
```

Task stops the application and removes the containers, only if 'stopContainers' is set to 'true' (default value).
```bash
gradle composeDown
```
Task pulls and optionally builds the images required by the application. This is useful, for example, 
with a CI platform that caches docker images to decrease build times.
```bash
gradle composePull 
```

## Usage

```bash
./gradlew bootRun
```

Call the URL in Webbrowser:
```
http://localhost:8082/postalcodes/55481
```