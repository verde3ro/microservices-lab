using System;
using System.Threading.Tasks;
using Microsoft.Extensions.Logging;
using MongoDB.Driver;
using PlantillaCorreoUS.Model;

namespace PlantillaCorreoUS.Repositorio
{
    public class PlantillaCorreoRepositorio : IPlantillaCorreoRepositorio
    {
        private readonly PlantillaCorreoContext _contexto;
        private readonly ILogger _registro;

        public PlantillaCorreoRepositorio(PlantillaCorreoContext contexto)
        {
            _contexto = contexto;
            _registro = new LoggerFactory().CreateLogger<PlantillaCorreoRepositorio>();
        }

        public async Task<PlantillaCorreoModel> ObtenerPlantillaCorreoPorNombre(string nombrePlantilla)
        {
            try
            {
                var plantilla = await _contexto.Correos.Find(plantilla => plantilla.Name == nombrePlantilla)
                    .FirstOrDefaultAsync();
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