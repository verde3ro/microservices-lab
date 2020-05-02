using EnviarCorreoPlantillaMS.Consul;
using EnviarCorreoPlantillaMS.Servicios;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.OpenApi.Models;
/**
 * Copyright Itehl Digital
 * Author Jorge Heredia
 * jorge.heredia@itehl.com
 * Código autorizado sólo a personas inscritas a los cursos
 */
namespace EnviarCorreoPlantillaMS
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
            
            services.AddConsulConfig(Configuration);

            services.AddMvc().AddNewtonsoftJson(options => options.UseMemberCasing())
                .SetCompatibilityVersion(CompatibilityVersion.Version_3_0)
                .AddMvcOptions(cfg => cfg.EnableEndpointRouting = false);

            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo {Title = "Enviar Correos", Version = "v1"});
            });

            services.AddControllers();

            services.AddCors(o => o.AddPolicy("LabPolicy", builder =>
            {
                builder.AllowAnyOrigin()
                    .AllowAnyMethod()
                    .AllowAnyHeader();
            }));

            services.AddTransient<IServicioEnviarCorreos, ServicioEnviarCorreos>();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment()) app.UseDeveloperExceptionPage();

            app.UseMvc();

            app.UseConsul();

            app.UseSwagger();

            app.UseSwaggerUI(c => { c.SwaggerEndpoint("/swagger/v1/swagger.json", "API Correos V1"); });

            app.UseCors("LabPolicy");
        }
    }
}