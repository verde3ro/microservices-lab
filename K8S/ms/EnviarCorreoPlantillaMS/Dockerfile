FROM mcr.microsoft.com/dotnet/core/aspnet:3.1.3-alpine3.11 AS runtime
WORKDIR /app

#Conexion a consul
ENV CONSUL=http://localhost:8500/
#Puerto a exponer del api
ENV PUERTO=1006
#Nombre del proyecto en consul
ENV PROYECTO=Laboratorio
#Nombre del servicio
ENV ID_SERVICIO=EnviarCorreoMS
#Nombre del servicio de entidad a descubrir
ENV PLANTILLA_ES=PlantillaCorreoUS


EXPOSE 1006
COPY ./bin/Release/netcoreapp3.0 /app
#Ejecuta la dll de Api para subir el servicio
ENTRYPOINT ["dotnet", "EnviarCorreoPlantillaMS.dll"]