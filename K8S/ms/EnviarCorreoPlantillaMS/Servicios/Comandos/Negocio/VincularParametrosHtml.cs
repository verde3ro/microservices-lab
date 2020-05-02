using System;
using EnviarCorreoPlantillaMS.DTO;
using Microsoft.Extensions.Logging;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
namespace EnviarCorreoPlantillaMS.Servicios.Comandos.Negocio
{
    public class VincularParametrosHtml
    {
        private readonly ILogger _registro;

        public VincularParametrosHtml()
        {
            _registro = new LoggerFactory().CreateLogger<VincularParametrosHtml>();
        }

        public CorreoDTO Ejecutar(CorreoDTO correoDto)
        {
            try
            {
                _registro.LogInformation("Start EnviarCorreoMS - VincularParámetros");
              
                String body =  correoDto.html.Replace("$K_Nombre", correoDto.nombre).Replace("$K_Curso", correoDto.curso);

                correoDto.mensaje = body;
                
                _registro.LogInformation("End EnviarCorreoMS - VincularParámetros");

                return correoDto;
            }
            catch (Exception ex)
            {
                _registro.LogError(ex.Message, DateTime.UtcNow);
                throw;
            }
        }
        
    }
}