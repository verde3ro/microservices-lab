FROM openjdk:8-jdk-alpine
#Nueva carpeta /app
WORKDIR /app
#Copia el jar a la carpeta app
COPY /target/ReservarCupoMS-0.0.1-SNAPSHOT.jar /app/
#Exposicion del puerto
EXPOSE 1002
#Iniciacion del servicio
ENTRYPOINT ["java", "-jar", "ReservarCupoMS-0.0.1-SNAPSHOT.jar"]