# Test-service
1. To start this service, you must first create a docker image using the command below 

docker build --build-arg JAR_FILE=build/libs/*.jar -t myorg/myapp .

2. Then start the docker container using the following command

docker run -p 8080:8080 -expose myorg/myapp

## Main URI

http://localhost:8080/api/rate?symbol=