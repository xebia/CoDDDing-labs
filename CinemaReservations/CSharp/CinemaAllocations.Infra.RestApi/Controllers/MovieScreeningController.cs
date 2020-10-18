using System;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace CinemaAllocations.Infra.RestApi.Controllers
{
    [ApiController]
    public class MovieScreeningController : ControllerBase
    {
        private readonly ILogger<MovieScreeningController> _logger;

        public MovieScreeningController(ILogger<MovieScreeningController> logger)
        {
            _logger = logger;
        }

        [HttpPost]
        [Route("moviescreening/{showId}/allocateseats/{partyRequested}")]
        public IActionResult AllocateSeats([FromQuery] string showId, [FromQuery] int partyRequested)
        {
            throw new NotImplementedException();
        }
    }
}