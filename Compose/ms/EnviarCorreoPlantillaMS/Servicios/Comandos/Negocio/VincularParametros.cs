using EnviarCorreoPlantillaMS.DTO;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EnviarCorreoPlantillaMS.Servicios.Comandos.Negocio
{
    public class VincularParametros
    {
        private readonly ILogger _registro;

        public VincularParametros()
        {
            _registro = new LoggerFactory().CreateLogger< VincularParametros>();
        }

        public CorreoDTO Vincular(CorreoDTO correo) 
        {
            try
            {
                _registro.LogInformation("Entro vincular", correo);

                //prepara el mensaje reemplazando valores en la plantilla
                string body = correo.html.Replace("$K_Nombre", correo.nombre).Replace("$K_Curso", correo.curso);
                //establece el mensaje
                correo.mensaje = body;

                _registro.LogInformation("Salio vincular", correo);

                return correo;
            } 
            catch (Exception ex) 
            {
                _registro.LogError(ex.Message, ex);
                throw;
            }
        }
    }
}
