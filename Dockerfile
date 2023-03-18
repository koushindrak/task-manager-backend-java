FROM openjdk:19-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the build files
COPY build.gradle gradlew gradlew.bat my-links settings.gradle ./

# Copy the source code
COPY src ./src

# Set permissions for gradlew

# Build the application
RUN gradle clean build

# Expose the port on which the application will run
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "build/libs/your-application.jar"]

