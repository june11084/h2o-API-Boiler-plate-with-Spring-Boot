# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="jun_li@hsb.com"

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Add the application's jar to the container
ADD demo.jar demo.jar

# Run the jar file
CMD java -jar demo.jar