FROM openjdk:8-jdk-alpine
#Nueva carpeta /app
WORKDIR /app
#Copia el jar a la carpeta app
COPY ./target/InscripcionES-0.0.1-SNAPSHOT.jar /app/
#Exposicion del puerto
EXPOSE 1003
#Iniciacion del servicio
ENTRYPOINT ["java", "-jar", "InscripcionES-0.0.1-SNAPSHOT.jar"]