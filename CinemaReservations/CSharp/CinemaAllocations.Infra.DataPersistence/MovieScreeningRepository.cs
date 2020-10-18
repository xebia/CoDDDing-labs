using System;
using System.Linq;
using CinemaAllocations.Domain;

namespace CinemaAllocations.Infra.DataPersistence
{
    public class MovieScreeningRepository : IMovieScreeningRepository, IDisposable
    {
        private readonly CinemaContext _myContext;

        public MovieScreeningRepository(CinemaContext myContext)
        {
            _myContext = myContext ?? throw new ArgumentNullException(nameof(myContext));
        }

        public Domain.MovieScreening FindMovieScreeningById(string screeningId)
        {
            throw new NotImplementedException("NOT IMPLEMENTED FUNCTION IN MovieScreeningRepository");
        }

        public void Dispose()
        {
            _myContext?.Dispose();
        }
    }
}