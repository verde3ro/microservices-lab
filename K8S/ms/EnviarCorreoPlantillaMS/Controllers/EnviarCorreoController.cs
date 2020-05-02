using System;
using System.Threading.Tasks;
using EnviarCorreoPlantillaMS.Servicios;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using EnviarCorreoPlantillaMS.DTO;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
namespace EnviarCorreoPlantillaMS.Controllers
{
    
    [ApiController]
    [Route("ms/enviarcorreoplantilla/v1/")]
    public class EnviarCorreoController : ControllerBase
    {
        private readonly ILogger<EnviarCorreoController> _logger;
        private readonly IServicioEnviarCorreos _servicio;

        public EnviarCorreoController(ILogger<EnviarCorreoController> logger, IServicioEnviarCorreos servicio)
        {
            _logger = logger;
            _servicio = servicio;
        }

        [HttpPost("enviar")]
        public async Task<ActionResult> EnviarCorreos([FromBody] CorreoDTO correoDTO)
        {
            try

            {
                _logger.LogInformation("Start EnviarCorreoMS");

                var resultado = await _servicio.Enviar(correoDTO);
       
                _logger.LogInformation("End EnviarCorreoMS");
                
                return Ok();
            }
            catch (Exception ex)
            {
                _logger.LogError(ex.Message, DateTime.Now);
                return BadRequest(ex.Message);
            }
        }
    }
}