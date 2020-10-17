using System.Collections.Generic;
using System.Linq;

namespace CinemaAllocations.Domain
{
    public class SeatsAllocated
    {
        public int PartyRequested { get; }
    
        public List<Seat> ReservedSeats { get; }

        public SeatsAllocated(List<Seat> reservedSeats, int partyRequested)
        {
            ReservedSeats = reservedSeats;
            PartyRequested = partyRequested;
        }

        public IEnumerable<string> SeatNames()
        {
            return ReservedSeats.OrderBy(seat => seat.Number).Select(seat => seat.ToString());
        }
    }
}