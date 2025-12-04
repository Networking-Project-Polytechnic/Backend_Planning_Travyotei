# Use OpenJDK
FROM eclipse-temurin:17-jdk

# Set working directory in container
WORKDIR /app

# Copy your built JAR into the container
COPY agencyadmin/target/agencyadmin-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar", "app.jar"]
