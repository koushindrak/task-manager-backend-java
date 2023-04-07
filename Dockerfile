FROM openjdk:19-jdk-slim

ENV GRADLE_VERSION=8.0.1
RUN apt-get update && apt-get install -y wget unzip

# Download and install Gradle
RUN wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle-${GRADLE_VERSION}-bin.zip -d /opt \
    && rm gradle-${GRADLE_VERSION}-bin.zip

# Set Gradle home environment variable
ENV GRADLE_HOME=/opt/gradle-${GRADLE_VERSION}

# Add Gradle to PATH
ENV PATH=${GRADLE_HOME}/bin:${PATH}


# Set the working directory
WORKDIR /app

# Copy the build files
COPY build.gradle gradlew gradlew.bat settings.gradle ./

# Copy the source code
COPY src ./src


# Build the application
RUN gradle clean build --warning-mode=none

# Expose the port on which the application will run
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "build/libs/todolist-backend-0.0.1-SNAPSHOT.jar"]

