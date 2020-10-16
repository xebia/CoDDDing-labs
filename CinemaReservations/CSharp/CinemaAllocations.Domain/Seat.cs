using System.Collections.Generic;
using Value;

namespace CinemaAllocations.Domain
{
    public class Seat : ValueType<Seat>
    {
        public string RowName { get; }
        public uint Number { get; }

        public SeatAvailability SeatAvailability { get; }

        protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
        {
            return new object[] {RowName, Number, SeatAvailability};
        }

        public override string ToString()
        {
            return $"{RowName}{Number}";
        }

        public Seat(string rowName, uint number, SeatAvailability seatAvailability)
        {
            RowName = rowName;
            Number = number;
            SeatAvailability = seatAvailability;
        }
    }
}