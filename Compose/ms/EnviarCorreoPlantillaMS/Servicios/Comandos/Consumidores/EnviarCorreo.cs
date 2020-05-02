using EnviarCorreoPlantillaMS.DTO;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace EnviarCorreoPlantillaMS.Servicios.Comandos.Consumidores
{
    public class EnviarCorreo
    {
        private readonly ILogger _registro;
        private HttpClient _cliente;

        public EnviarCorreo()
        {
            _registro = new LoggerFactory().CreateLogger<EnviarCorreo>();
        }

        public async Task<bool> Ejecutar(CorreoDTO correo) 
        {
            try 
            {
                _registro.LogInformation("Start enviar correo", correo);

                //instancia un cliente http para enviar la peticion al embajador
                _cliente = new HttpClient() { BaseAddress = new Uri("http://itehl-us-correo-api:1008/") };
                //serializa el correo para enviarlo al embajador
                string cuerpo = JsonConvert.SerializeObject(correo);
                //establece el cuerpo de la petición
                StringContent contenido = new StringContent(cuerpo, Encoding.UTF8, "application/json");
                //realiza la petición http
                HttpResponseMessage response = await _cliente.PostAsync(_cliente.BaseAddress.ToString() + "/us/email/v1", contenido);

                if (!response.IsSuccessStatusCode) 
                {
                    return false;
                }

                _registro.LogInformation("End enviar correo", correo);

                return true;
            } 
            catch (Exception ex) 
            {
                _registro.LogError(ex.Message, DateTime.Now, ex);
                throw;
            }
        }
    }
}
