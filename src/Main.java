public class Main {
    public static void main(String[] args)
    {
        DataLoader dataLoader = new DataLoader("D:\\Vysoká škola\\5. semester\\Bakalarka\\gtfs - Krakow");
        //dataLoader.getAllStops();
        //dataLoader.getAllRoutes();
        //dataLoader.getAllCalendars();
        dataLoader.getAllStopTimes();
    }
}
