using System;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.Extensions.Logging;
using PlantillaCorreoUS.DTO;
using PlantillaCorreoUS.Mapeo;
using PlantillaCorreoUS.Model;
using PlantillaCorreoUS.Repositorio;

namespace PlantillaCorreoUS.Servicio
{
    public class PlantillaCorreoESImpl : IPlantillaCorreoES
    {
        private readonly IMapper _mapper;
        private readonly ILogger _registro;
        private readonly IPlantillaCorreoRepositorio _repo;

        public PlantillaCorreoESImpl(PlantillaCorreoContext contexto, IMapper mapper)
        {
            _repo = new PlantillaCorreoRepositorio(contexto);
            _registro = new LoggerFactory().CreateLogger<PlantillaCorreoESImpl>();
            _mapper = mapper;
        }

        public async Task<PlantillaCorreoDTO> ObtenerPlantillaCorreoPorNombre(string nombrePlantilla)
        {
            try
            {
                var modelo = await _repo.ObtenerPlantillaCorreoPorNombre(nombrePlantilla);
                var plantilla = PlantillaCorreoMapeo.FromModelToDTO(modelo, _mapper);
                return plantilla;
            }
            catch (Exception ex)
            {
                _registro.LogError(ex.Message, DateTime.Now);
                throw;
            }
        }
    }
}