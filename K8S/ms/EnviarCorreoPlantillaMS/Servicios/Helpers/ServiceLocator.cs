using System;
using System.Linq;
using System.Net.Http;
using Consul;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
namespace EnviarCorreoPlantillaMS.Servicios.Helpers
{
    public class ServiceLocator
    {
        private static Uri uri;
        private static ConsulClient cliente;

        public static HttpClient Buscar(string nombreServicio)
        {
            
            cliente = new ConsulClient();
            
            AgentService service = cliente.Agent.Services().Result.Response.Where(s => s.Key == nombreServicio).ToList().First()
                .Value;
          
            Console.Write("ENDPOINT CONSUL" + service.ToString());
            
            uri = new Uri("http://" + service.Address + ":" + service.Port + "/");
            
            var clienteHttp = new HttpClient {BaseAddress = uri};
            
            return clienteHttp;
        }
    }
}