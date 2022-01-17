FROM openjdk:8-jdk-alpine
#Nueva carpeta /app
WORKDIR /app
#Copia el jar a la carpeta app
COPY ./target/ClienteES-0.0.1-SNAPSHOT.jar /app/
#Exposicion del puerto
EXPOSE 1005
#Iniciacion del servicio
ENTRYPOINT ["java", "-jar", "ClienteES-0.0.1-SNAPSHOT.jar"]