using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Diagnostics.HealthChecks;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace PlantillaCorreoUS.Controllers.Fwk
{
    public class BaseController:Controller, IHealthCheck
    {

        [Route("us/plantilla/health")]
        [ApiExplorerSettings(IgnoreApi = true)]
        public Task<HealthCheckResult> CheckHealthAsync(HealthCheckContext context, CancellationToken cancellationToken = default)
        {
            try
            {
                return Task.FromResult(HealthCheckResult.Healthy());
            }
            catch (Exception ex)
            {
                return Task.FromResult(HealthCheckResult.Unhealthy(ex.ToString()));
            }
        }
    }
}
