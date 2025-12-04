# ---------- Stage 1: Build the JAR ----------
FROM maven:3.9.6-amazoncorretto-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copy source code
COPY src ./src

# Build the JAR
RUN mvn -B clean package -DskipTests


# ---------- Stage 2: Run the JAR ----------
FROM amazoncorretto:17-alpine

WORKDIR /app

# Copy the built JAR from stage 1
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
