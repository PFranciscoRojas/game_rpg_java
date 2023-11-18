# Use the official OpenJDK 17 image as a base image
FROM openjdk:17-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the LibGDX project files into the container at /app
COPY . /app

# Grant execute permissions to the gradlew script
RUN chmod +x gradlew

# Build the LibGDX project and expose the port
RUN ./gradlew desktop:dist

# Expose the port on which your LibGDX application will run (optional)
EXPOSE 8080

# Command to run the LibGDX application
CMD ["java", "-jar", "desktop/build/libs/desktop-1.0.jar"]
