using System;
using System.Threading.Tasks;
using EnviarCorreoPlantillaMS.DTO;
using EnviarCorreoPlantillaMS.Servicios.Comandos.Consumidores;
using EnviarCorreoPlantillaMS.Servicios.Comandos.Negocio;
using Microsoft.Extensions.Logging;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
namespace EnviarCorreoPlantillaMS.Servicios
{
    public class ServicioEnviarCorreos : IServicioEnviarCorreos
    {
        private readonly EnviarCorreoHtml _enviarCorreo;
        private readonly ObtenerPlantillaPorNombre _obtenerPlantillaPorNombre;
        private readonly VincularParametrosHtml _vincularParametrosHtml;
        private readonly ILogger _registro;

        public ServicioEnviarCorreos()
        {
            _registro = new LoggerFactory().CreateLogger<ServicioEnviarCorreos>();
            _obtenerPlantillaPorNombre = new ObtenerPlantillaPorNombre();
            _vincularParametrosHtml = new VincularParametrosHtml();
            _enviarCorreo = new EnviarCorreoHtml();
        }

        public async Task<bool> Enviar(CorreoDTO correoDto)
        {
            try
            {
                
                var sended = await _enviarCorreo.Ejecutar(_vincularParametrosHtml.Ejecutar(_obtenerPlantillaPorNombre.Ejecutar(correoDto).Result));
                
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