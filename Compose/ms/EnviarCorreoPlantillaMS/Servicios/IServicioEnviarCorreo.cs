using EnviarCorreoPlantillaMS.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EnviarCorreoPlantillaMS.Servicios
{
    public interface IServicioEnviarCorreo
    {
        Task<bool> Enviar(CorreoDTO correoDto);
    }
}
