﻿using System;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using PlantillaCorreoUS.Controllers.Fwk;
using PlantillaCorreoUS.Servicio;

namespace PlantillaCorreoUS.Controllers
{
    [ApiController]
    [Route("us/plantillacorreo/v1/")]
    public class PlantillaCorreoController : BaseController
    {
        private readonly ILogger _logger;
        private readonly IPlantillaCorreoES _servicio;

        public PlantillaCorreoController(IPlantillaCorreoES servicio)
        {
            _logger = new LoggerFactory().CreateLogger<PlantillaCorreoController>();
            _servicio = servicio;
        }

        [HttpGet("{nombrePlantilla}")]
        public async Task<ActionResult> ObtenerPlantillaCorreoPorNombre(string nombrePlantilla)
        {
            try
            {
              //  if (string.IsNullOrEmpty(nombrePlantilla)) return NotFound();

                var plantilla = await _servicio.ObtenerPlantillaCorreoPorNombre(nombrePlantilla);

                return Ok(plantilla);
            }
            catch (Exception ex)
            {
                _logger.LogError(ex.Message, DateTime.Now);
                return BadRequest(ex.Message);
            }
        }
    }
}