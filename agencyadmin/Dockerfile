# ============================
# 1) BUILD STAGE
# ============================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy Maven files first (cache optimization)
COPY pom.xml .
COPY src ./src

# Build the JAR (skip tests for faster builds)
RUN mvn clean package -DskipTests && rm -f target/*-plain.jar


# ============================
# 2) RUN STAGE
# ============================
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
