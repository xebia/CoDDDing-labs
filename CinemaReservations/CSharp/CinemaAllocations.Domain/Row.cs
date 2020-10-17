using System.Collections.Generic;
using Value;

namespace CinemaAllocations.Domain
{
    public class Row : ValueType<Row>
    {
        public string Name { get; }
        public List<Seat> Seats { get; }

        public Row(string name, List<Seat> seats)
        {
            Name = name;
            Seats = seats;
        }
        
        public SeatsAllocated AllocateSeats(AllocateSeats allocateSeats) 
        {
            var allocation = new SeatAllocation(allocateSeats.PartyRequested);

            foreach (var seat in Seats) {

                if (seat.IsAvailable())
                {
                    allocation.AddSeat(seat);

                    if(allocation.IsFulfilled) {
                        return new SeatsAllocated(allocation.AllocatedSeats, allocateSeats.PartyRequested);
                    }
                }
            }
            return new NoPossibleAllocationsFound(allocateSeats.PartyRequested);
        }

        public Row MakeSeatsReserved(List<Seat> updatedSeats) 
        {
            foreach (var newSeat in updatedSeats)
            {
                Seats[Seats.FindIndex(s => s.Equals(newSeat))] = newSeat.ReserveSeats();
            }
            return new Row(Name, Seats);
        }

        protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
        {
            return new object[] { Name, new ListByValue<Seat>(new List<Seat>(Seats)) };
        }
    }

}