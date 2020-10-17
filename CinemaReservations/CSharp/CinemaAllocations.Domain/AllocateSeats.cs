namespace CinemaAllocations.Domain
{
    public class AllocateSeats
    {
        public string ShowId { get; }
     
        public int PartyRequested { get; }

        public AllocateSeats(string showId, int partyRequested)
        {
            ShowId = showId;
            PartyRequested = partyRequested;
        }
    }
}