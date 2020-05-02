using System;
using MongoDB.Driver;
using PlantillaCorreoUS.DBConfig;

namespace PlantillaCorreoUS.Model
{
    public class PlantillaCorreoContext
    {
        public PlantillaCorreoContext(IDBConfiguracion configuracion)
        {
            IMongoClient cliente =
                new MongoClient(Environment.GetEnvironmentVariable(configuracion.ConnectionString) == null
                    ? configuracion.ConnectionStringTest
                    : Environment.GetEnvironmentVariable(configuracion.ConnectionString));
            var database = cliente.GetDatabase(Environment.GetEnvironmentVariable(configuracion.DataBaseName) == null
                ? configuracion.DataBaseNameTest
                : Environment.GetEnvironmentVariable(configuracion.DataBaseName));
            Correos = database.GetCollection<PlantillaCorreoModel>(
                Environment.GetEnvironmentVariable(configuracion.CollectionName) == null
                    ? configuracion.CollectionNameTest
                    : Environment.GetEnvironmentVariable(configuracion.CollectionName));
        }

        public IMongoCollection<PlantillaCorreoModel> Correos { get; }
    }
}