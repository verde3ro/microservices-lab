using AutoMapper;
using PlantillaCorreoUS.DTO;
using PlantillaCorreoUS.Model;

namespace PlantillaCorreoUS.Mapeo
{
    public class PlantillaCorreoConfiguracionMapeo : Profile
    {
        public PlantillaCorreoConfiguracionMapeo()
        {
            CreateMap<PlantillaCorreoModel, PlantillaCorreoDTO>()
                .ForMember(destiny => destiny.Id, options => options.Ignore());
        }
    }
}