using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using EnviarCorreoPlantillaMS.DTO;
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
    public class EnviarCorreoHtml
    {
        private readonly ILogger _registro;
        private HttpClient _cliente;

        public EnviarCorreoHtml()
        {
           
            _cliente = new HttpClient();
            _registro = new LoggerFactory().CreateLogger<EnviarCorreoHtml>();
        }

        public async Task<bool> Ejecutar(CorreoDTO correoDto)
        {
            try
            {
                _registro.LogInformation("Start EnviarCorreoMS - EnviarHTML");

                 
                var contenido = JsonConvert.SerializeObject(correoDto);

                var cuerpo = new StringContent(contenido, Encoding.UTF8, "application/json");

                var response = await _cliente.PostAsync(
                    "http://itehl-us-correo-api:1008/us/email/v1", cuerpo);
                
                if (!response.IsSuccessStatusCode) return false;

                _registro.LogInformation("End EnviarCorreoMS - EnviarHTML");
                
                return true;
            }
            catch (Exception ex)
            {
                _registro.LogError(ex.Message, DateTime.Now);
                throw;
            }
        }
    }
}