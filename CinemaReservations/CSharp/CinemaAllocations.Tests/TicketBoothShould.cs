using CinemaAllocations.Domain;
using CinemaAllocations.Tests.StubMovieScreening;
using NFluent;
using Xunit;

namespace CinemaAllocations.Tests
{
    public class SeatsAllocatorShould
    {
        private const string FordTheaterId = "1";
        private const string O3AuditoriumId = "2";
        private const string DockStreetId = "3";
        private const string MadisonTheatherId = "5";
        
        [Fact]
        public void Reserve_one_seat_when_available()
        {
            const int partyRequested = 1;

            IMovieScreeningRepository repository = new StubMovieScreeningRepository();
            var ticketBooth = new TicketBooth(repository);

            var seatsAllocated = ticketBooth.AllocateSeats(new AllocateSeats(FordTheaterId, partyRequested));

            Check.That(seatsAllocated.ReservedSeats).HasSize(1);
            Check.That(seatsAllocated.ReservedSeats[0].ToString()).IsEqualTo("A3");
        }
        
        [Fact]
        public void Reserve_multiple_seats_when_available()
        {
            const int partyRequested = 3;

            IMovieScreeningRepository repository = new StubMovieScreeningRepository();
            var ticketBooth = new TicketBooth(repository);

            var seatsAllocated = ticketBooth.AllocateSeats(new AllocateSeats(DockStreetId, partyRequested));

            Check.That(seatsAllocated.ReservedSeats).HasSize(3);
            Check.That(seatsAllocated.SeatNames()).ContainsExactly("A6", "A7", "A8");
        }

        [Fact]
        public void Return_SeatsNotAvailable_when_all_seats_are_unavailable()
        {
            const int partyRequested = 1;

            IMovieScreeningRepository repository = new StubMovieScreeningRepository();
            var ticketBooth = new TicketBooth(repository);

            var seatsAllocated = ticketBooth.AllocateSeats(new AllocateSeats(MadisonTheatherId, partyRequested));

            Check.That(seatsAllocated).IsInstanceOf<NoPossibleAllocationsFound>();
        }

        [Fact]
        public void Return_TooManyTicketsRequested_when_9_tickets_are_requested()
        {
            const int partyRequested = 9;

            IMovieScreeningRepository repository = new StubMovieScreeningRepository();
            var ticketBooth = new TicketBooth(repository);

            var seatsAllocated = ticketBooth.AllocateSeats(new AllocateSeats(MadisonTheatherId, partyRequested));
            
            Check.That(seatsAllocated).IsInstanceOf<TooManyTicketsRequested>();

        }
        
        [Fact]
        public void Reserve_three_adjacent_seats_when_available()
        {
            const int partyRequested = 3;

            IMovieScreeningRepository repository = new StubMovieScreeningRepository();
            var ticketBooth = new TicketBooth(repository);

            var seatsAllocated = ticketBooth.AllocateSeats(new AllocateSeats(O3AuditoriumId, partyRequested));
            
            Check.That(seatsAllocated.ReservedSeats).HasSize(3);
            Check.That(seatsAllocated.SeatNames()).ContainsExactly("A8", "A9", "A10");

        }
        
        [Fact]
        public void Return_NoPossibleAdjacentSeatsFound_when_4_tickets_are_requested()
        {
            const int partyRequested = 4;

            IMovieScreeningRepository repository = new StubMovieScreeningRepository();
            var ticketBooth = new TicketBooth(repository);

            var seatsAllocated = ticketBooth.AllocateSeats(new AllocateSeats(O3AuditoriumId, partyRequested));
            
            Check.That(seatsAllocated).IsInstanceOf<NoPossibleAdjacentSeatsFound>();
        }
    }
}