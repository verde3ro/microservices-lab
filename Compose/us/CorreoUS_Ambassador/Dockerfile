FROM openjdk:8-jdk-alpine
#Nueva carpeta /app
WORKDIR /app
#Copia el jar a la carpeta app
COPY ./target/CorreoUS_Ambassador-0.0.1-SNAPSHOT.jar /app/
#Exposicion del puerto
EXPOSE 1008
#Iniciacion del servicio
ENTRYPOINT ["java", "-jar", "CorreoUS_Ambassador-0.0.1-SNAPSHOT.jar"]