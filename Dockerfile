# Etapa de construcción
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copia los archivos de Maven
COPY pom.xml .
COPY src ./src

# Compila la aplicación y crea el uber-jar
RUN mvn clean package "-Dquarkus.package.type=uber-jar"

# Etapa de ejecución
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copia el JAR construido desde la etapa anterior
COPY --from=build /app/target/*-runner.jar app.jar

# Exponer el puerto que usará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
