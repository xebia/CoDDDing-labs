using System.Collections.Generic;

namespace CinemaAllocations.Domain
{
    public class NoPossibleAllocationsFound : SeatsAllocated
    {
        public NoPossibleAllocationsFound(int partyRequested) : base(new List<Seat>(), partyRequested)
        {
        }
    }
}