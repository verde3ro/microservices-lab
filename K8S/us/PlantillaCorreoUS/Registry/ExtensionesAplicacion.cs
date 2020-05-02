using System;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using Consul;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;

namespace PlantillaCorreoUS.Registry
{
    public static class ExtensionesAplicacion
    {
        public static IServiceCollection AddConsulConfig(this IServiceCollection services, IConfiguration configuration)
        {
            services.AddSingleton<IConsulClient, ConsulClient>(p => new ConsulClient(consulConfig =>
            {
                var address = Environment.GetEnvironmentVariable(configuration.GetValue<string>("Consul:Host")) == null
                    ? configuration.GetValue<string>("Consul:HostTest")
                    : Environment.GetEnvironmentVariable(configuration.GetValue<string>("Consul:Host"));
                consulConfig.Address = new Uri(address);
            }));
            return services;
        }

        public static IApplicationBuilder UseConsul(this IApplicationBuilder app)
        {
            var consulClient = app.ApplicationServices.GetRequiredService<IConsulClient>();
            var config = app.ApplicationServices.GetRequiredService<IOptions<AjustesConsul>>().Value;
            var logger = app.ApplicationServices.GetRequiredService<ILoggerFactory>()
                .CreateLogger("ExtensionesAplicacion");
            var lifetime = app.ApplicationServices.GetRequiredService<IHostApplicationLifetime>();
            var name = Dns.GetHostName(); // get container id
            var ip = Dns.GetHostEntry(name).AddressList
                .FirstOrDefault(x => x.AddressFamily == AddressFamily.InterNetwork);
            var ctrl = ip.ToString();
            var address = " http://" + ip + ":" + (Environment.GetEnvironmentVariable(config.Puerto) == null
                ? config.PuertoTest
                : Environment.GetEnvironmentVariable(config.Puerto)) + "/";
            var uri = new Uri(address);
            var registration = new AgentServiceRegistration
            {
                ID = Environment.GetEnvironmentVariable(config.IdServicio) == null
                    ? config.IdServicio
                    : Environment.GetEnvironmentVariable(config.IdServicio),
                // servie name  
                Name = Environment.GetEnvironmentVariable(config.Proyecto),
                Address = $"{uri.Host}",
                Port = uri.Port
            };
            logger.LogInformation("Registering with Consul");
            consulClient.Agent.ServiceDeregister(registration.ID).ConfigureAwait(true);
            consulClient.Agent.ServiceRegister(registration).ConfigureAwait(true);
            lifetime.ApplicationStopping.Register(() =>
            {
                logger.LogInformation("Unregistering from Consul");
                consulClient.Agent.ServiceDeregister(registration.ID).ConfigureAwait(true);
            });
            return app;
        }
    }
}