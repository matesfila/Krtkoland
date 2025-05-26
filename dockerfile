# Použijeme oficiálny Maven image na buildovanie
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Nastavíme pracovný adresár
WORKDIR /app

# Skopírujeme všetky súbory
COPY . .

# Buildnieme projekt a vytvoríme .jar
RUN mvn clean package -DskipTests

# Použijeme minimalistický image pre runtime
FROM eclipse-temurin:17-jdk-alpine

# Nastavíme pracovný adresár
WORKDIR /app

# Skopírujeme .jar z predchádzajúceho kroku
COPY --from=build /app/target/krtkoland-0.0.1-SNAPSHOT.jar app.jar

# Otvoríme port (ak používaš 8080)
EXPOSE 8080

# Spustenie aplikácie
ENTRYPOINT ["java", "-jar", "app.jar"]
