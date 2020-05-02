using System.Threading.Tasks;
using EnviarCorreoPlantillaMS.DTO;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
namespace EnviarCorreoPlantillaMS.Servicios
{
    public interface IServicioEnviarCorreos
    {
        Task<bool> Enviar(CorreoDTO correoDto);
    }
}