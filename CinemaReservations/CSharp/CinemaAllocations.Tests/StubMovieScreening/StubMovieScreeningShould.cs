using CinemaAllocations.Domain;
using NFluent;
using Xunit;

namespace CinemaAllocations.Tests.StubMovieScreening
{
    public class StubMovieScreeningShould
    {
        [Fact]
        public void Find_movie_screening_one()
        {
            IMovieScreeningRepository repository = new StubMovieScreeningRepository();

            MovieScreening movieScreening = repository.FindMovieScreeningById("1");

            Check.That(movieScreening).IsNotNull();
            Check.That(movieScreening.Rows.Count).IsEqualTo(2);
        }
    }
}