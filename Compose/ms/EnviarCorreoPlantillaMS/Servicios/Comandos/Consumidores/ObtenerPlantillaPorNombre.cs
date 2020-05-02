using EnviarCorreoPlantillaMS.DTO;
using EnviarCorreoPlantillaMS.Helpers;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace EnviarCorreoPlantillaMS.Servicios.Comandos.Consumidores
{
    public class ObtenerPlantillaPorNombre
    {
        private readonly ILogger _registro;
        private HttpClient _cliente;
        
        public ObtenerPlantillaPorNombre()
        {
            _registro = new LoggerFactory().CreateLogger< ObtenerPlantillaPorNombre>();
        }

        public async Task<CorreoDTO> Ejecutar(CorreoDTO correo) 
        {
            try 
            {
                _registro.LogInformation("Start ObtenerPlantillaPorNombre", correo);

                //se establece un cliente para enviar la peticion que trae como respuesta la plantilla
                _cliente = ServiceLocator.LookUp("PlantillaCorreoUS");
                //resliza la petición al servicio de utilidad que posee la plantilla
                HttpResponseMessage response = await _cliente.GetAsync(_cliente.BaseAddress.ToString() + "us/plantillacorreo/v1/" + correo.plantilla);
                //obtiene el json de la plantilla
                string text = response.Content.ReadAsStringAsync().Result;
                //des-serializa la plantilla para obtener el campo que necesitamos
                CorreoDTO correoDTO = JsonConvert.DeserializeObject<CorreoDTO>(text);
                //obtiene el contenido de la plantilla
                correo.html = correoDTO.html;

                _registro.LogInformation("End ObtenerPlantillaPorNombre", correo);

                return correo;
            } 
            catch (Exception ex) 
            {
                _registro.LogError(ex.Message, DateTime.Now, ex);
                throw;
            }
        }
    }
}
