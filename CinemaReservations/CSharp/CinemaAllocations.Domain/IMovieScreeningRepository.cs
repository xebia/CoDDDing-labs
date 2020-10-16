namespace CinemaAllocations.Domain
{
    public interface IMovieScreeningRepository
    {
        MovieScreening FindMovieScreeningById(string screeningId);
    }
}