using AutoMapper;
using PlantillaCorreoUS.DTO;
using PlantillaCorreoUS.Model;

namespace PlantillaCorreoUS.Mapeo
{
    public class PlantillaCorreoMapeo
    {
        public static PlantillaCorreoDTO FromModelToDTO(PlantillaCorreoModel modelo, IMapper mapper)
        {
            var plantilla = new PlantillaCorreoDTO();
            plantilla.Id = modelo.Id.ToString();
            mapper.Map(modelo, plantilla);
            return plantilla;
        }
    }
}