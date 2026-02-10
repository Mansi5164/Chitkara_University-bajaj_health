# ---------- Build Stage ----------
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# copy maven wrapper
COPY mvnw .
COPY .mvn .mvn

# copy pom
COPY pom.xml .

# download dependencies first (this speeds build)
RUN chmod +x mvnw
RUN ./mvnw -q -e -DskipTests dependency:go-offline

# copy source code
COPY src src

# build jar
RUN ./mvnw -q -DskipTests package


# ---------- Run Stage ----------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# copy jar from builder
COPY --from=builder /app/target/*.jar app.jar

# Render provides PORT dynamically
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
