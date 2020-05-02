using System.Threading.Tasks;
using PlantillaCorreoUS.DTO;

namespace PlantillaCorreoUS.Servicio
{
    public interface IPlantillaCorreoES
    {
        Task<PlantillaCorreoDTO> ObtenerPlantillaCorreoPorNombre(string nombrePlantilla);
    }
}