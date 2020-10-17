using System.Collections.Generic;

namespace CinemaAllocations.Domain
{
    public class TooManyTicketsRequested : SeatsAllocated
    {
        public TooManyTicketsRequested(int partyRequested) : base(new List<Seat>(), partyRequested)
        {
        }
    }
}