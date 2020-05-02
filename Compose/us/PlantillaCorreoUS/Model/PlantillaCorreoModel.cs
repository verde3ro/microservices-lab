using MongoDB.Bson;

namespace PlantillaCorreoUS.Model
{
    public class PlantillaCorreoModel
    {
        public ObjectId Id { get; set; }
        public string Name { get; set; }
        public string Html { get; set; }
    }
}