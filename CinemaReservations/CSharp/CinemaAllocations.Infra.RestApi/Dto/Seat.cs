namespace CinemaAllocations.Infra.RestApi.Dto
{
    public class Seat
    {
        public Seat(Domain.Seat seat)
        {
            RowName = seat.RowName;
            Number = seat.Number;
            SeatName = seat.ToString();
        }
        
        public string RowName { get; }
        
        public uint Number { get; }

        public string SeatName { get; set; }
    }
}