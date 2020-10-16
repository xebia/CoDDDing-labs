using System.Collections.Generic;

namespace CinemaAllocations.Domain
{
    public class NoMovieScreeningFound : SeatsAllocated
    {
        public NoMovieScreeningFound(int partyRequested) : base(new List<Seat>(), partyRequested)
        {
        }
    }
}