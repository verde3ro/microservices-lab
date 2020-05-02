using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EnviarCorreoPlantillaMS.DTO
{
    public class CorreoDTO
    {
        public string to { get; set; }
        public string plantilla { get; set; }
        public string html { get; set; }
        public string mensaje { get; set; }
        public string nombre { get; set; }
        public string curso { get; set; }
    }
}
