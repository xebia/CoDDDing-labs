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
            var movieScreeningDataModel = _myContext.MovieScreenings.SingleOrDefault(x => x.Id == screeningId);

            // there is a open bug with EF Core and the load of the related entities
            if (movieScreeningDataModel != null && movieScreeningDataModel.Rows == null)
            {
                movieScreeningDataModel.Rows = _myContext.Rows
                    .Where(x => x.MovieScreening.Id.Equals(movieScreeningDataModel.Id)).ToList();
            }

            return movieScreeningDataModel?.ToDomainModel();
        }

        public void Dispose()
        {
            _myContext?.Dispose();
        }
    }
}