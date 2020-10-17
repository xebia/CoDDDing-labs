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

        public SeatsAllocated AllocateSeats(AllocateSeats allocateSeats)
        {
            var numberOfSeatsAvailable = 0;
            foreach (var row in _rows.Values)
            {
                var seatsAllocated = row.AllocateSeats(allocateSeats);
                if (seatsAllocated.GetType() != typeof(NoPossibleAllocationsFound))
                {
                    var updatedRow = row.MakeSeatsReserved(seatsAllocated.ReservedSeats);
                    _rows[updatedRow.Name] = updatedRow;
                    return seatsAllocated;
                }

                numberOfSeatsAvailable = numberOfSeatsAvailable + row.ReturnNumberOfSeatsAvailable();
            }

            if (numberOfSeatsAvailable >= allocateSeats.PartyRequested)
            {
                return new NoPossibleAdjacentSeatsFound(allocateSeats.PartyRequested);
            }

            return new NoPossibleAllocationsFound(allocateSeats.PartyRequested);
        }
    }
}