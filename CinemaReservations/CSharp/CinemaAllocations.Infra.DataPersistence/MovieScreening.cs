using System;
using System.Collections.Generic;
using CinemaAllocations.Domain;

namespace CinemaAllocations.Infra.DataPersistence
{
    public class MovieScreening
    {
        public string Id { get; set; }

        public virtual List<Row> Rows { get; set; }

        public Domain.MovieScreening ToDomainModel()
        {
            var rows = new Dictionary<string, Domain.Row>(Rows.Count);

            foreach (var rowDataModel in Rows)
            {
                var seats = new List<Domain.Seat>(rowDataModel.Seats.Count);
                
                foreach (var seatDataModel in rowDataModel.Seats)
                {
                    Enum.TryParse(seatDataModel.Availability, out SeatAvailability seatAvailability);

                    var seat = new Domain.Seat(
                        rowDataModel.Name,
                        seatDataModel.Number,
                        seatAvailability);

                    seats.Add(seat);
                }

                rows.Add(rowDataModel.Name, new Domain.Row(rowDataModel.Name, seats));
            }

            return new Domain.MovieScreening(rows);
        }
    }
}