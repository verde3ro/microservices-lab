using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using EnviarCorreoPlantillaMS.DTO;
using EnviarCorreoPlantillaMS.Servicios;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace EnviarCorreoPlantillaMS.Controllers
{
    [ApiController]
    [Route("ms/enviarcorreoplantilla/v1/")]
    public class EnviarCorreoController : ControllerBase
    {
        private readonly ILogger<EnviarCorreoController> _logger;
        private readonly IServicioEnviarCorreo _servicio;

        public EnviarCorreoController(ILogger<EnviarCorreoController> logger, IServicioEnviarCorreo servicio)
        {
            _logger = logger;
            _servicio = servicio;
        }

        [HttpPost("enviar")]
        public async Task<ActionResult> EnviarCorreo([FromBody] CorreoDTO correo)
        {
            try 
            {
                bool enviado = await _servicio.Enviar(correo);

                return Ok(enviado);
            } 
            catch (Exception ex) 
            {
                _logger.LogError(ex.Message, ex);
                return BadRequest(ex);
            }
        }
    }
}
