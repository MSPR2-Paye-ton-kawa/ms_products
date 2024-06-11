# Étape 1 : construire l'application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Étape 2 : exécuter l'application
#FROM openjdk:17-jdk-slim
#COPY --from=build /app/target/mspr-prdoduct-0.0.1-SNAPSHOT.jar /app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]
# Utiliser une image de base pour Java
FROM openjdk:17-jdk-slim

# Créer un répertoire pour l'application
RUN mkdir /app

# Copier le jar de l'application dans le conteneur
COPY ./target/mspr-prdoduct-0.0.1-SNAPSHOT.jar /app/mspr-product.jar

# Exposer le port que votre application utilise
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app/mspr-product.jar"]
