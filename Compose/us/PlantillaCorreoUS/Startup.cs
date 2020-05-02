using AutoMapper;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Options;
using Microsoft.OpenApi.Models;
using PlantillaCorreoUS.DBConfig;
using PlantillaCorreoUS.Registry;
using PlantillaCorreoUS.Mapeo;
using PlantillaCorreoUS.Model;
using PlantillaCorreoUS.Servicio;
using Microsoft.Extensions.Diagnostics.HealthChecks;
using PlantillaCorreoUS.Controllers;
using System;
using Microsoft.AspNetCore.Diagnostics.HealthChecks;
using PlantillaCorreoUS.Controllers.Fwk;

namespace PlantillaCorreoUS
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.Configure<AjustesConsul>(Configuration.GetSection(nameof(AjustesConsul)));

            services.Configure<DBConfiguracion>(Configuration.GetSection(nameof(DBConfiguracion)));

            services.AddSingleton<IDBConfiguracion>(sp => sp.GetRequiredService<IOptions<DBConfiguracion>>().Value);

            services.AddSingleton<PlantillaCorreoContext>();

            services.AddConsulConfig(Configuration);
            
            services.AddControllers();

            services.AddLogging();

            var configuration = new MapperConfiguration(cfg =>
            {
                cfg.AddProfile<PlantillaCorreoConfiguracionMapeo>();
            });

            services.AddAutoMapper(typeof(Startup));

            services.AddMvc().AddNewtonsoftJson(options => options.UseMemberCasing())
                .SetCompatibilityVersion(CompatibilityVersion.Version_3_0)
                .AddMvcOptions(cfg => cfg.EnableEndpointRouting = false);

            services.AddCors(o => o.AddPolicy("LabPolicy", builder =>
            {
                builder.AllowAnyOrigin()
                    .AllowAnyMethod()
                    .AllowAnyHeader();
            }));

            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo {Title = "Api Correos", Version = "v1"});
            });

            services.AddTransient<IPlantillaCorreoES, PlantillaCorreoESImpl>();
            services.AddHealthChecks()
                .AddCheck<BaseController>("company_health_check", failureStatus: HealthStatus.Unhealthy);
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment()) app.UseDeveloperExceptionPage();

            app.UseConsul();

            app.UseSwagger();

            app.UseMvc();

            var configuracion = app.ApplicationServices.GetRequiredService<IOptions<DBConfiguracion>>().Value;

            PlantillaCorreoMigracion.Migrar(new PlantillaCorreoContext(configuracion));

            app.UseSwaggerUI(c => { c.SwaggerEndpoint("/swagger/v1/swagger.json", "API Correos V1"); });

            app.UseCors("LabPolicy");
            app.UseHealthChecks("/us/plantilla/health", port: Environment.GetEnvironmentVariable("PUERTO"), new HealthCheckOptions
            {
                AllowCachingResponses = false
            });
        }
    }
}