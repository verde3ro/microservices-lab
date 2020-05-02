FROM mcr.microsoft.com/dotnet/core/aspnet:3.1.3-alpine3.11 AS runtime
WORKDIR /app

#Conexion a consul
ENV CONSUL=http://consul:8500/
#Puerto a exponer del api
ENV PUERTO=1007
#Nombre del proyecto en consul
ENV PROYECTO=Laboratorio
#Variables de lo referente a base de datos
ENV CONNECTION_STRING=mongodb://localhost:27017/admin
ENV DATABASE_NAME=PlantillaCorreoDB
ENV COLLECTION_NAME=PlantillaCorreo
#Nombre Servicio
ENV ID_SERVICIO=PlantillaCorreoUS

EXPOSE 1007
COPY ./bin/Release/netcoreapp3.0 /app
#Ejecuta la dll de Api para subir el servicio
ENTRYPOINT ["dotnet", "PlantillaCorreoUS.dll"]