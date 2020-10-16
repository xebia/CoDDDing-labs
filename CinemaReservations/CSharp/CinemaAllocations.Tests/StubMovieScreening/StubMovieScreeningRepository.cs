using System;
using System.Collections.Generic;
using System.IO;
using System.Reflection;
using System.Runtime.InteropServices;
using CinemaAllocations.Domain;

namespace CinemaAllocations.Tests.StubMovieScreening
{
    public class StubMovieScreeningRepository : IMovieScreeningRepository
    {
        private readonly Dictionary<string, MovieScreeningDto> _movieScreeningRepository =
            new Dictionary<string, MovieScreeningDto>();

        public StubMovieScreeningRepository()
        {
            var directoryName = $"{GetExecutingAssemblyDirectoryFullPath()}\\MovieScreenings\\";

            if (RuntimeInformation.IsOSPlatform(OSPlatform.Linux) || RuntimeInformation.IsOSPlatform(OSPlatform.OSX))
            {
                directoryName = $"{GetExecutingAssemblyDirectoryFullPath()}/MovieScreenings/";
            }

            foreach (var fileFullName in Directory.EnumerateFiles($"{directoryName}"))
            {
                var fileName = Path.GetFileName(fileFullName);
                var eventId = Path.GetFileName(fileName.Split("-")[0]);
                _movieScreeningRepository[eventId] = JsonFile.ReadFromJsonFile<MovieScreeningDto>(fileFullName);
            }
        }

        public MovieScreening FindMovieScreeningById(string screeningId)
        {
            MovieScreeningDto movieScreeningDto;

            if (_movieScreeningRepository.ContainsKey(screeningId))
            {
                movieScreeningDto = _movieScreeningRepository[screeningId];
            }
            else
            {
                throw new ArgumentException("MovieScreening not found for screening ID: " + screeningId);
            }

            var rows = new Dictionary<string, Row>();

            foreach (var rowDto in movieScreeningDto.Rows)
            {
                var seats = new List<Seat>();
                foreach (var seatDto in rowDto.Value)
                {
                    var rowName = ExtractRowName(seatDto.Name);
                    var number = ExtractNumber(seatDto.Name);

                    var seatAvailability = ExtractAvailability(seatDto.SeatAvailability);


                    seats.Add(new Seat(rowName, number, seatAvailability));
                }

                rows.Add(rowDto.Key, new Row(rowDto.Key, seats));
            }

            return new MovieScreening(rows);
        }

        private static string GetExecutingAssemblyDirectoryFullPath()
        {
            var directoryName = Path.GetDirectoryName(Assembly.GetExecutingAssembly().CodeBase);

            if (directoryName.StartsWith(@"file:\"))
            {
                directoryName = directoryName.Substring(6);
            }

            if (directoryName.StartsWith(@"file:/"))
            {
                directoryName = directoryName.Substring(5);
            }

            return directoryName;
        }

        private static SeatAvailability ExtractAvailability(string seatAvailability)
        {
            switch (seatAvailability)
            {
                case "Available":
                    return SeatAvailability.Available;
                case "Reserved":
                    return SeatAvailability.Reserved;
                case "Confirmed":
                    return SeatAvailability.Confirmed;
                default:
                    throw new ArgumentException("Unexpected value: " + seatAvailability);
            }
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