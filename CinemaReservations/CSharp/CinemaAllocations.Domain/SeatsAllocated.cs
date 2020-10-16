using System.Collections.Generic;

namespace CinemaAllocations.Domain
{
    public class SeatsAllocated
    {
        public int PartyRequested { get; }
        
        private readonly List<Seat> _seats;
        
        public IReadOnlyList<Seat> Seats => _seats;

        public SeatsAllocated(List<Seat> seats, int partyRequested)
        {
            _seats = seats;
            PartyRequested = partyRequested;
        }
    }
}