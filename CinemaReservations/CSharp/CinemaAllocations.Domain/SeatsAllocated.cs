using System.Collections.Generic;

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
    }
}