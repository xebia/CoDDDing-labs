namespace CinemaAllocations.Domain
{
    public class TicketBooth
    {
        private readonly IMovieScreeningRepository _screeningRepository;

        public TicketBooth(IMovieScreeningRepository movieScreeningRepository)
        {
            _screeningRepository = movieScreeningRepository;
        }

        public SeatsAllocated AllocateSeats(AllocateSeats allocateSeats)
        {
            MovieScreening movieScreening = _screeningRepository.FindMovieScreeningById(allocateSeats.ShowId);
            return null;
        }
    }
}