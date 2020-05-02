using EnviarCorreoPlantillaMS.DTO;
using EnviarCorreoPlantillaMS.Servicios.Comandos.Consumidores;
using EnviarCorreoPlantillaMS.Servicios.Comandos.Negocio;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EnviarCorreoPlantillaMS.Servicios
{
    public class ServicioEnviarCorreo: IServicioEnviarCorreo
    {
        private readonly EnviarCorreo _enviarCorreo;
        private readonly ObtenerPlantillaPorNombre _obtenerPlantillaPorNombre;
        private readonly VincularParametros _vincularParametrosHtml;
        private readonly ILogger _registro;

        public ServicioEnviarCorreo()
        {
            _registro = new LoggerFactory().CreateLogger<ServicioEnviarCorreo>();
            _obtenerPlantillaPorNombre = new ObtenerPlantillaPorNombre();
            _vincularParametrosHtml = new VincularParametros();
            _enviarCorreo = new EnviarCorreo();
        }

        public async Task<bool> Enviar(CorreoDTO correoDto)
        {
            try
            {

                var sended = await _enviarCorreo.Ejecutar(_vincularParametrosHtml.Vincular(_obtenerPlantillaPorNombre.Ejecutar(correoDto).Result));

                return sended;
            }
            catch (Exception ex)
            {
                _registro.LogError(ex.Message, DateTime.Now);
                throw;
            }
        }
    }
}
