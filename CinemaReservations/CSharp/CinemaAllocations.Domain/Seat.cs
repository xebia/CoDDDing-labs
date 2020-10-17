using System.Collections.Generic;
using System.Linq;
using Value;

namespace CinemaAllocations.Domain
{
    public class Seat : ValueType<Seat>
    {
        public string RowName { get; }
   
        public uint Number { get; }

        public SeatAvailability SeatAvailability { get; }

        public Seat(string rowName, uint number, SeatAvailability seatAvailability)
        {
            RowName = rowName;
            Number = number;
            SeatAvailability = seatAvailability;
        }

        public Seat ReserveSeats()
        {
            return new Seat(RowName, Number, SeatAvailability.Reserved);
        }

        public bool IsAvailable()
        {
            return SeatAvailability == SeatAvailability.Available;
        }

        public bool SameSeatLocation(Seat seat)
        {
            return RowName.Equals(seat.RowName) && Number == seat.Number;
        }

        public bool IsAdjacentWith(List<Seat> seats)
        {
            var orderedSeats = seats.OrderBy(s => s.Number).ToList();

            foreach (var seat in orderedSeats)
            {
                if (Number + 1 == seat.Number || Number - 1 == seat.Number)
                    return true;
            }

            return false;
        }

        public override string ToString()
        {
            return $"{RowName}{Number}";
        }

        protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
        {
            return new object[] {RowName, Number, SeatAvailability};
        }
    }
}