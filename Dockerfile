#### Stage 1: Build the application
FROM openjdk:17-jdk-alpine as build

# Set the current working directory inside the image
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Ensure mvnw is executable and then download dependencies to go offline
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src

# Package the application without running tests
RUN ./mvnw package -DskipTests

# Extract the built application into the target/dependency directory
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-jdk-alpine

# Define the path to dependencies
ARG DEPENDENCY=/app/target/dependency

# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Specify the entrypoint to run the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.pookietata.hacktues.HackTuesApplication"]
