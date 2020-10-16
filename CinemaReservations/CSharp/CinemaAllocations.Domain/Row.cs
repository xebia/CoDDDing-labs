using System.Collections.Generic;
using Value;

namespace CinemaAllocations.Domain
{
    public class Row : ValueType<Row>
    {
        public string Name { get; }
        public IEnumerable<Seat> Seats { get; }

        public Row(string name, IEnumerable<Seat> seats)
        {
            Name = name;
            Seats = seats;
        }

        protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
        {
            return new object[] {Name, new ListByValue<Seat>(new List<Seat>(Seats))};
        }
    }
}