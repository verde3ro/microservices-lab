FROM openjdk:8-jdk-alpine
#Nueva carpeta /app
WORKDIR /app
#Copia el jar a la carpeta app
COPY ./target/CursoES-0.0.1-SNAPSHOT.jar /app/
#Exposicion del puerto
EXPOSE 1004
#Iniciacion del servicio
ENTRYPOINT ["java", "-jar", "CursoES-0.0.1-SNAPSHOT.jar"]