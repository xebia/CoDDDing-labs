using System.Net.Http;
using Xunit;

namespace CinemaAllocations.Tests.Acceptance.Helpers
{
    public abstract class AcceptanceTests : IClassFixture<ApiWebApplicationFactory>
    {
        protected readonly ApiWebApplicationFactory _factory;
        protected readonly HttpClient _client;

        protected AcceptanceTests(ApiWebApplicationFactory fixture)
        {
            _factory = fixture;
            _client = _factory.CreateClient();
        }
    }
}