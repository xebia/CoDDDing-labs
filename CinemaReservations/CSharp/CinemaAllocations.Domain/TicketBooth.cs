namespace CinemaAllocations.Domain
{
    public class TicketBooth
    {
        private readonly IMovieScreeningRepository _movieScreeningRepository;
        private readonly int MAXIMUM_NUMBER_OF_ALLOWED_TICKETS = 8;

        public TicketBooth(IMovieScreeningRepository repo)
        {
            _movieScreeningRepository = repo;
        }

        public SeatsAllocated AllocateSeats(AllocateSeats allocateSeats)
        {
            if (allocateSeats.PartyRequested > MAXIMUM_NUMBER_OF_ALLOWED_TICKETS)
            {
                return new TooManyTicketsRequested(allocateSeats.PartyRequested);
            }

            var movieScreening = _movieScreeningRepository.FindMovieScreeningById(allocateSeats.ShowId);
            return movieScreening.AllocateSeats(allocateSeats);
        }
    }
}