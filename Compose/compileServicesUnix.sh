mvn clean install -Dmaven.test.skip=true -f ./ms/ConsultarInscripcionesMS/
dotnet publish ./ms/EnviarCorreoPlantillaMS/ -c Release
mvn clean install -Dmaven.test.skip=true -f ./ms/ProcesarInscripcionMS/
mvn clean install -Dmaven.test.skip=true -f ./ms/ReservarCupoMS/
mvn clean install -Dmaven.test.skip=true -f ./es/ClienteES/
mvn clean install -Dmaven.test.skip=true -f ./es/CursoES/
mvn clean install -Dmaven.test.skip=true -f ./es/InscripcionES/
mvn clean install -Dmaven.test.skip=true -f ./us/CorreoUS_Ambassador/
dotnet publish ./us/PlantillaCorreoUS/ -c Release
