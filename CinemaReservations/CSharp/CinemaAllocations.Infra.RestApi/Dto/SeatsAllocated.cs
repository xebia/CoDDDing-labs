using System.Collections.Generic;

namespace CinemaAllocations.Infra.RestApi.Dto
{
    public class SeatsAllocated
    {
        public SeatsAllocated(Domain.SeatsAllocated allocatedSeats)
        {
            PartyRequested = allocatedSeats.PartyRequested;
            ReservedSeats = allocatedSeats.ReservedSeats.ConvertAll(ToDtoSeat);
        }

        private static Seat ToDtoSeat(Domain.Seat seat)
        {
            return new Seat(seat);
        }

        public int PartyRequested { get; }

        public List<Seat> ReservedSeats { get; }
    }
}