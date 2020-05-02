using System.Threading.Tasks;
using PlantillaCorreoUS.Model;

namespace PlantillaCorreoUS.Repositorio
{
    public interface IPlantillaCorreoRepositorio
    {
        Task<PlantillaCorreoModel> ObtenerPlantillaCorreoPorNombre(string nombrePlantilla);
    }
}