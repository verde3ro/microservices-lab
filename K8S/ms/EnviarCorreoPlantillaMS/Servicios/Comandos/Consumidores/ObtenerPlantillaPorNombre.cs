using System;
using System.Net.Http;
using System.Threading.Tasks;
using EnviarCorreoPlantillaMS.DTO;
using EnviarCorreoPlantillaMS.Servicios.Helpers;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
namespace EnviarCorreoPlantillaMS.Servicios.Comandos.Consumidores
{
    public class ObtenerPlantillaPorNombre
    {
        private readonly ILogger _registro;
        private HttpClient _cliente;

        public ObtenerPlantillaPorNombre()
        {
            _registro = new LoggerFactory().CreateLogger<ObtenerPlantillaPorNombre>();
        }

        public async Task<CorreoDTO> Ejecutar(CorreoDTO correoDto)
        {
            try
            {
                _registro.LogInformation("Start EnviarCorreoMS - ObtenerPlantilla");
                
                //obtiene la url base del servicio necesario para la ejecución
              
                
                _cliente = ServiceLocator.Buscar("PlantillaCorreoUS");
                
                //envía la petición al servicio necesario
                var response =
                    await _cliente.GetAsync(_cliente.BaseAddress + "us/plantillacorreo/v1/" +correoDto.plantilla);
                
                
                //obtiene el contenido de la respuesta
                var text = response.Content.ReadAsStringAsync().Result;
                
                //deserializa el contenido de la respuesta
                CorreoDTO correoDtoHtml = JsonConvert.DeserializeObject<CorreoDTO>(text);
                
                correoDto.html = correoDtoHtml.html;
                
                _registro.LogInformation("Start EnviarCorreoMS - ObtenerPlantilla");
                
                return correoDto;
            }
            catch (Exception ex)
            {
                _registro.LogError(ex.Message, DateTime.Now);
                throw;
            }
        }
    }
}