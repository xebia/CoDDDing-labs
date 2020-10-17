using System.Collections.Generic;

namespace CinemaAllocations.Domain
{
    public class NoPossibleAdjacentSeatsFound : SeatsAllocated
    {
        public NoPossibleAdjacentSeatsFound(int partyRequested) : base(new List<Seat>(), partyRequested)
        {
        }
    }
}