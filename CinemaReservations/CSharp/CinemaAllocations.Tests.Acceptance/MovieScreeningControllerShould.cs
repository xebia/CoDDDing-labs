using System;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using CinemaAllocations.Tests.Acceptance.Helpers;
using Newtonsoft.Json;
using NFluent;
using Xunit;

namespace CinemaAllocations.Tests.Acceptance
{
    public class MovieScreeningControllerShould : AcceptanceTests
    {
        public MovieScreeningControllerShould(ApiWebApplicationFactory fixture)
            : base(fixture)
        {
        }

        [Fact]
        public async Task Reserve_one_seat_when_available()
        {
            // The solution started here: https://timdeschryver.dev/blog/how-to-test-your-csharp-web-api you need to do some magic to inject the DB. ;)

            var (response, seatsAllocated) =
                await AllocateSeats<Helpers.Dto.SeatsAllocated>(Given.The.FordTheaterId, 1);

            Check.That(response.StatusCode).IsEqualTo(HttpStatusCode.OK);
            Check.That(seatsAllocated.ReservedSeats).HasSize(1);
        }

        [Fact]
        public async Task Reserve_multiple_seats_when_available()
        {
            var (response, seatsAllocated) = await AllocateSeats<Helpers.Dto.SeatsAllocated>(Given.The.DockStreetId, 3);

            Check.That(response.StatusCode).IsEqualTo(HttpStatusCode.OK);
            Check.That(seatsAllocated.ReservedSeats).HasSize(3);
            Check.That(seatsAllocated.SeatNames()).ContainsExactly("A6", "A7", "A8");
        }

        [Fact]
        public async Task Return_SeatsNotAvailable_when_all_seats_are_unavailable()
        {
            var (response, noPossibleAllocationsFound) =
                await AllocateSeats<Helpers.Dto.NoPossibleAllocationsFound>(Given.The.MadisonTheatherId, 1);

            Check.That(response.StatusCode).IsEqualTo(HttpStatusCode.NotFound);
            Check.That(noPossibleAllocationsFound).IsInstanceOf<Helpers.Dto.NoPossibleAllocationsFound>();
        }

        [Fact]
        public async Task Return_TooManyTicketsRequested_when_9_tickets_are_requested()
        {
            var (response, tooManyTicketsRequested) =
                await AllocateSeats<Helpers.Dto.TooManyTicketsRequested>(Given.The.MadisonTheatherId, 9);
            
            Check.That(response.StatusCode).IsEqualTo(HttpStatusCode.BadRequest);
            Check.That(tooManyTicketsRequested).IsInstanceOf<Helpers.Dto.TooManyTicketsRequested>();
        }

        [Fact]
        public async Task Reserve_three_adjacent_seats_when_available()
        {
            var (response, seatsAllocated) = await AllocateSeats<Helpers.Dto.SeatsAllocated>(Given.The.O3AuditoriumId, 3);

            Check.That(response.StatusCode).IsEqualTo(HttpStatusCode.OK);
            Check.That(seatsAllocated.ReservedSeats).HasSize(3);
            Check.That(seatsAllocated.SeatNames()).ContainsExactly("A8", "A9", "A10");
        }

        [Fact]
        public async Task Return_NoPossibleAdjacentSeatsFound_when_4_tickets_are_requested()
        {
            var (response, noPossibleAdjacentSeatsFound) =
                await AllocateSeats<Helpers.Dto.NoPossibleAdjacentSeatsFound>(Given.The.O3AuditoriumId, 4);

            Check.That(response.StatusCode).IsEqualTo(HttpStatusCode.NotFound);
            Check.That(noPossibleAdjacentSeatsFound).IsInstanceOf<Helpers.Dto.NoPossibleAdjacentSeatsFound>();
        }

        private async Task<Tuple<HttpResponseMessage, TEvent>> AllocateSeats<TEvent>(string showId, int partyRequested)
        {
            var response = await _client.PostAsync($"/moviescreening/{showId}/allocateseats/{partyRequested}", null);
            var outputEvent = JsonConvert.DeserializeObject<TEvent>(await response.Content.ReadAsStringAsync());

            return new Tuple<HttpResponseMessage, TEvent>(response, outputEvent);
        }
    }
}