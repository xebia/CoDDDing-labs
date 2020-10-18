using System.Collections.Generic;
using CinemaAllocations.Infra.DataPersistence;

namespace CinemaAllocations.Tests.Integration.Helpers
{
    public class MovieScreeningDto
    {
        public Dictionary<string, IReadOnlyList<SeatDto>> Rows { get; set; }

        internal MovieScreening ToDataModel(string showId)
        {
            var movieScreening =  new MovieScreening
            {
                Id = showId,
                Rows = new List<Row>(Rows.Count)
            };

            foreach (var rowDto in Rows)
            {
                var row = new Row
                {
                    Id = string.Concat(showId, '-', ExtractRowName(rowDto.Key)),
                    Name = ExtractRowName(rowDto.Key),
                    Seats = new List<Seat>(rowDto.Value.Count)
                };

                foreach (var seatDto in rowDto.Value)
                {
                    var seat = new Seat
                    {
                        Availability = seatDto.SeatAvailability,
                        Number = ExtractNumber(seatDto.Name)
                    };
                    
                    row.Seats.Add(seat);
                }
                
                movieScreening.Rows.Add(row);
            }

            return movieScreening;
        }
        
        private static uint ExtractNumber(string name)
        {
            return uint.Parse(name.Substring(1));
        }

        private static string ExtractRowName(string name)
        {
            return name[0].ToString();
        }
    }
}