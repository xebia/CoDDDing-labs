using System.IO;
using Newtonsoft.Json;

namespace CinemaAllocations.Tests.Acceptance.Helpers
{
    public static class JsonFile
    {
        public static T ReadFromJsonFile<T>(string filePath) where T : new()
        {
            TextReader reader = null;
            try
            {
                reader = new StreamReader(filePath);
                var fileContents = reader.ReadToEnd();
                return JsonConvert.DeserializeObject<T>(fileContents);
            }
            finally
            {
                reader?.Close();
            }
        }
    }
}