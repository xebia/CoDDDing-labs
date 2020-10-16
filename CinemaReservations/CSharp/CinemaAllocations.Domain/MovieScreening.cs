using System.Collections.Generic;

namespace CinemaAllocations.Domain
{
    public class MovieScreening
    {
        public IReadOnlyDictionary<string, Row> Rows => _rows;

        private readonly Dictionary<string, Row> _rows;

        public MovieScreening(Dictionary<string, Row> rows)
        {
            _rows = rows;
        }
    }
}