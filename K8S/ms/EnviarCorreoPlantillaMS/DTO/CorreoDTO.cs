/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
namespace EnviarCorreoPlantillaMS.DTO
{
    public class CorreoDTO
    {
        public string mensaje { get; set; }
        public string plantilla { get; set; } 
        
        public string to { get; set; }
        public string html { get; set; }
        public string nombre { get; set; }
        public string curso { get; set; }   
    }
}