# Build Stage
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copy and resolve dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy project files and build
COPY src ./src
RUN mvn clean package -DskipTests

# Production Stage
FROM openjdk:17-jdk-slim

# Set non-root user for better security
RUN useradd -m spring
USER spring

WORKDIR /app

# Copy only the built JAR to the production image
COPY --from=build /app/target/projects-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
