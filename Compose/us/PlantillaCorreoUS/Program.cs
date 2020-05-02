using System;
using System.IO;
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;

namespace PlantillaCorreoUS
{
    public class Program
    {
        public static void Main(string[] args)
        {
            BuildWebHost(args).Run();
        }

        public static IWebHost BuildWebHost(string[] args)
        {
            var builder = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("appsettings.json");
            var Configuration = builder.Build();

            #region establecer puerto para el api en el contenedor y la prueba de integraci�n

            var section = Configuration.GetSection("ServiceConfig");
            var port = (string) section.GetValue(typeof(string), "AjustesConsul:PuertoTest");
            var puerto = Environment.GetEnvironmentVariable("PUERTO") == null
                ? port
                : Environment.GetEnvironmentVariable("PUERTO");
            var url = "http://0.0.0.0:" + puerto + "/";

            #endregion

            return WebHost.CreateDefaultBuilder(args)
                .UseContentRoot(Directory.GetCurrentDirectory())
                .UseConfiguration(Configuration)
                .UseUrls(url)
                .UseStartup<Startup>()
                .Build();
        }
    }
}