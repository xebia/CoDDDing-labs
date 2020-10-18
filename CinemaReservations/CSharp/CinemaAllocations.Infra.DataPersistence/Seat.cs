using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace CinemaAllocations.Infra.DataPersistence
{
    [Owned]
    public class Seat
    {
        public uint Number { get; set; }

        public string Availability { get; set; }
        
        [ForeignKey("RowId")] 
        public virtual Row Row { get; set; }
    }
}