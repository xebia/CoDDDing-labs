using Microsoft.EntityFrameworkCore;

namespace CinemaAllocations.Infra.DataPersistence
{
    public class CinemaContext : DbContext
    {
        public CinemaContext(DbContextOptions<CinemaContext> options)
            : base(options)
        {
        }

        public DbSet<MovieScreening> MovieScreenings { get; set; }
        public DbSet<Row> Rows { get; set; }
        public DbSet<Seat> Seats { get; set; }
    }
}