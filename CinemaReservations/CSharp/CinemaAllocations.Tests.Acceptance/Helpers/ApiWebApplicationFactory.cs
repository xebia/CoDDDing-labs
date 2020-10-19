using System;
using CinemaAllocations.Infra.RestApi;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.AspNetCore.TestHost;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;

namespace CinemaAllocations.Tests.Acceptance.Helpers
{
    public class ApiWebApplicationFactory : WebApplicationFactory<Startup>
    {
        private static string _databaseName;

        internal static string DatabaseName
        {
            get
            {
                if (!string.IsNullOrWhiteSpace(_databaseName))
                    return _databaseName;

                _databaseName = Guid.NewGuid().ToString();

                return _databaseName;
            }
        }

        protected override void ConfigureWebHost(IWebHostBuilder builder)
        {
            builder.ConfigureTestServices(services =>
            {
                services.AddDbContext<Infra.DataPersistence.CinemaContext>(opt =>
                    opt.UseInMemoryDatabase(DatabaseName));
            });
        }
    }
}