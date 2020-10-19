using System;
using CinemaAllocations.Domain;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace CinemaAllocations.Infra.RestApi.Controllers
{
    [ApiController]
    public class MovieScreeningController : ControllerBase
    {
        private readonly ILogger<MovieScreeningController> _logger;
        private readonly TicketBooth _ticketBooth;

        public MovieScreeningController(ILogger<MovieScreeningController> logger, TicketBooth ticketBooth)
        {
            _logger = logger ?? throw new ArgumentNullException(nameof(logger));
            _ticketBooth = ticketBooth ?? throw new ArgumentNullException(nameof(ticketBooth));
        }

        [HttpPost]
        [Route("moviescreening/{showId}/allocateseats/{partyRequested}")]
        public IActionResult AllocateSeats([FromRoute] string showId, [FromRoute] int partyRequested)
        {
            try
            {
                var allocatedSeats = _ticketBooth.AllocateSeats(
                    new AllocateSeats(showId, partyRequested)
                );

                switch (allocatedSeats)
                {
                    case NoPossibleAllocationsFound noPossibleAllocationsFound:
                        return NotFound(noPossibleAllocationsFound);
                    case TooManyTicketsRequested tooManyTicketsRequested:
                        return BadRequest(tooManyTicketsRequested);
                    case NoPossibleAdjacentSeatsFound noPossibleAdjacentSeatsFound:
                        return NotFound(noPossibleAdjacentSeatsFound);
                    default:
                        return new OkObjectResult(new Dto.SeatsAllocated(allocatedSeats));
                }
            }
            catch (Exception exception)
            {
                _logger.LogError(
                    5001,
                    exception,
                    "Unexpected exception, outside of our domain model. ShowId: {0}, PartyRequested {1}",
                    showId, partyRequested);
                return StatusCode(StatusCodes.Status500InternalServerError,
                    "Unexpected exception. Stay tune, we are sending pigeons!");
            }
        }
    }
}