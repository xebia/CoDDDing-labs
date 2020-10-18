using CinemaAllocations.Domain;
using NFluent;
using Xunit;

namespace CinemaAllocations.Tests.Integration
{
    public class TicketBoothShould
    {
        [Fact]
        public void Reserve_one_seat_when_available()
        {
            const int partyRequested = 1;

            using (var repository = Given.The.FordTheater)
            {
                var ticketBooth = new TicketBooth(repository);

                var seatsAllocated =
                    ticketBooth.AllocateSeats(new AllocateSeats(Given.The.FordTheaterId, partyRequested));

                Check.That(seatsAllocated.ReservedSeats).HasSize(1);
                Check.That(seatsAllocated.ReservedSeats[0].ToString()).IsEqualTo("A3");
            }
        }

        [Fact]
        public void Reserve_multiple_seats_when_available()
        {
            const int partyRequested = 3;

            using (var repository = Given.The.DockStreet)
            {
                var ticketBooth = new TicketBooth(repository);

                var seatsAllocated =
                    ticketBooth.AllocateSeats(new AllocateSeats(Given.The.DockStreetId, partyRequested));

                Check.That(seatsAllocated.ReservedSeats).HasSize(3);
                Check.That(seatsAllocated.SeatNames()).ContainsExactly("A6", "A7", "A8");
            }
        }

        [Fact]
        public void Return_SeatsNotAvailable_when_all_seats_are_unavailable()
        {
            const int partyRequested = 1;

            using (var repository = Given.The.MadisonTheather)
            {
                var ticketBooth = new TicketBooth(repository);

                var seatsAllocated =
                    ticketBooth.AllocateSeats(new AllocateSeats(Given.The.MadisonTheatherId, partyRequested));

                Check.That(seatsAllocated).IsInstanceOf<NoPossibleAllocationsFound>();
            }
        }

        [Fact]
        public void Return_TooManyTicketsRequested_when_9_tickets_are_requested()
        {
            const int partyRequested = 9;

            using (var repository = Given.The.MadisonTheather)
            {
                var ticketBooth = new TicketBooth(repository);

                var seatsAllocated =
                    ticketBooth.AllocateSeats(new AllocateSeats(Given.The.MadisonTheatherId, partyRequested));

                Check.That(seatsAllocated).IsInstanceOf<TooManyTicketsRequested>();
            }
        }

        [Fact]
        public void Reserve_three_adjacent_seats_when_available()
        {
            const int partyRequested = 3;

            using (var repository = Given.The.O3Auditorium)
            {
                var ticketBooth = new TicketBooth(repository);

                var seatsAllocated =
                    ticketBooth.AllocateSeats(new AllocateSeats(Given.The.O3AuditoriumId, partyRequested));

                Check.That(seatsAllocated.ReservedSeats).HasSize(3);
                Check.That(seatsAllocated.SeatNames()).ContainsExactly("A8", "A9", "A10");
            }
        }

        [Fact]
        public void Return_NoPossibleAdjacentSeatsFound_when_4_tickets_are_requested()
        {
            const int partyRequested = 4;

            using(var repository = Given.The.O3Auditorium)
            {
                var ticketBooth = new TicketBooth(repository);

                var seatsAllocated =
                    ticketBooth.AllocateSeats(new AllocateSeats(Given.The.O3AuditoriumId, partyRequested));

                Check.That(seatsAllocated).IsInstanceOf<NoPossibleAdjacentSeatsFound>();
            }
        }
    }
}