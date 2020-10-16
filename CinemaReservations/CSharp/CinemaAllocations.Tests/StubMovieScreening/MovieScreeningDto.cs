using System.Collections.Generic;

namespace CinemaAllocations.Tests.StubMovieScreening
{
    public class MovieScreeningDto
    {
        public Dictionary<string, IReadOnlyList<SeatDto>> Rows { get; set; }
    }
}