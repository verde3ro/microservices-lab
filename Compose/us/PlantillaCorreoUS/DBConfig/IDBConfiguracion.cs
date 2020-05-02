namespace PlantillaCorreoUS.DBConfig
{
    public interface IDBConfiguracion
    {
        string ConnectionString { get; set; }
        string DataBaseName { get; set; }
        string CollectionName { get; set; }
        string ConnectionStringTest { get; set; }
        string CollectionNameTest { get; set; }
        string DataBaseNameTest { get; set; }
    }
}