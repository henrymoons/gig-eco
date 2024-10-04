# 1. Base image for the first stage (Build stage)
FROM gradle:7.5.1-jdk17 AS build

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Clone the GitHub repository
RUN git clone https://github.com/henrymoons/gig-eco.git .

# 4. Build the application using Gradle
RUN gradle clean build -x test

# 5. Base image for the second stage (Run stage)
FROM openjdk:17-jdk-slim

# 6. Set the working directory for the final container
WORKDIR /app

# 7. Copy the jar file from the first build stage
COPY --from=build /app/build/libs/*.jar /app/gig-eco.jar

# 8. Expose the port the application will run on (default is 8080)
EXPOSE 8080

# 9. Command to run the application
CMD ["java", "-jar", "/app/gig-eco.jar"]
