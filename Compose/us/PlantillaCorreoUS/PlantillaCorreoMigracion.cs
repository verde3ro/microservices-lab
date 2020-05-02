using MongoDB.Bson;
using MongoDB.Driver;
using PlantillaCorreoUS.Model;

namespace PlantillaCorreoUS
{
    public class PlantillaCorreoMigracion
    {
        public static void Migrar(PlantillaCorreoContext context)
        {
            MigrarPlantillas(context);
        }

        private static void MigrarPlantillas(PlantillaCorreoContext context)
        {
            context.Correos.DeleteMany(e => e.Id != null);
            if (context.Correos.EstimatedDocumentCount() == 0)
            {
                PlantillaCorreoModel[] emailTemplates =
                {
                    new PlantillaCorreoModel
                    {
                        Id = ObjectId.Parse("5e4ea229d97601a110cc73d9"),

                                Html = "Hola $K_Nombre" + "\n \n" +
                                       "Bienvenido al curso $K_Curso." +  "\n \n" +
                                       "Itehl Digital.",

                                Name = "EMAIL_INSCRIPTION"
                    }
                };

                context.Correos.InsertMany(emailTemplates);
            }
        }
    }
}