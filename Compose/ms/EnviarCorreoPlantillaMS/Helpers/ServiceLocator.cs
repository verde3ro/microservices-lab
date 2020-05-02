using Consul;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace EnviarCorreoPlantillaMS.Helpers
{
    public class ServiceLocator
    {
        private static Uri uri;
        private static ConsulClient cliente;

        public static HttpClient LookUp(string nombreServicio) 
        {
            try 
            {
                cliente = new ConsulClient(c => c.Address = new Uri(Environment.GetEnvironmentVariable("CONSUL")));
                var service = cliente.Agent.Services().Result.Response;
                List<KeyValuePair<string, AgentService>> services = new List<KeyValuePair<string, AgentService>>();
                
                foreach (var servicio in service) 
                {
                    Console.WriteLine("Instancias en CONSUL: " + servicio.Value.ID);
                    
                    if (servicio.Value.Service == nombreServicio) 
                    {
                        services.Add(servicio);
                    }
                }

                HttpClient response = new HttpClient() { BaseAddress = Balancer(services) };
                
                return response;
            }
            catch(Exception )
            {
                throw;
            }
        }

        private static Uri Balancer(List<KeyValuePair<string, AgentService>> servicios) 
        {
            try 
            {
                Random aleatorio = new Random();
                List<AgentService> agentes = new List<AgentService>();
                
                //obtiene la lista de agentes en el servicio
                foreach (var servicio in servicios) 
                {
                    agentes.Add(servicio.Value);
                }
                //obtiene un índice al azar
                int indice = aleatorio.Next(0, agentes.Count);
                //obtiene el agente en la posición que indica el índice 
                AgentService agente = agentes[indice];
                //crea la uri a utilizar
                uri = new Uri($"http://{agente.Address}:{agente.Port}");

                Console.WriteLine("Consumiendo endpoint de CONSUL: " + uri);

                return uri;
            } 
            catch (Exception) 
            {
                throw;
            }
        }
    }
}
