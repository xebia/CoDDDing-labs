using System;
using System.Collections.Generic;
using CinemaAllocations.Infra.DataPersistence;

namespace CinemaAllocations.Tests.Integration.Helpers
{
    public class MovieScreeningDto
    {
        public Dictionary<string, IReadOnlyList<SeatDto>> Rows { get; set; }

        internal MovieScreening ToDataModel(string showId)
        {
            throw new NotImplementedException("NOT IMPLEMENTED FUNCTION IN MovieScreeningDto");
        }
    }
}