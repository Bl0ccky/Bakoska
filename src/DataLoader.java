import TextFiles.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class DataLoader
{
    private String filePath;
    private final Hashtable<String, IObject> agency;
    private final Hashtable<String, IObject> stops;
    private final Hashtable<String, IObject> routes;
    private final Hashtable<String, IObject> trips;
    private final Hashtable<String, IObject> stopTimes;
    private final Hashtable<String, IObject> calendars;
    private final Hashtable<String, IObject> calendarDates;


    DataLoader(String filePath)
    {
        this.filePath = filePath;
        this.agency = new Hashtable<>();
        this.stops = new Hashtable<>();
        this.routes = new Hashtable<>();
        this.trips = new Hashtable<>();
        this.stopTimes = new Hashtable<>();
        this.calendars = new Hashtable<>();
        this.calendarDates = new Hashtable<>();
        this.loadAgency();
        this.loadStops();
        this.loadRoutes();
        this.loadTrips();
        this.loadStopTimes();
        this.loadCalendars();
        this.loadCalendarDates();
    }

    private void loadAgency()
    {
        this.loadGTFS("agency.txt", 7, ObjectType.AGENCY, this.agency);
    }

    private void loadStops()
    {
        this.loadGTFS("stops.txt", 12, ObjectType.STOP, this.stops);
    }

    private void loadRoutes()
    {
        this.loadGTFS("routes.txt", 9, ObjectType.ROUTE, this.routes);
    }

    private void loadTrips()
    {
        this.loadGTFS("trips.txt", 9, ObjectType.TRIP, this.trips);
    }

    private void loadStopTimes()
    {
        this.loadGTFS("stop_times.txt", 10, ObjectType.STOP_TIME, this.stopTimes);
    }

    private void loadCalendars()
    {
        this.loadGTFS("calendar.txt", 10, ObjectType.CALENDAR, this.calendars);
    }

    private void loadCalendarDates()
    {
        this.loadGTFS("calendar_dates.txt", 3, ObjectType.CALENDAR_DATE, this.calendarDates);
    }

    private void loadGTFS(String fileName, int delimiterLimit, ObjectType objectType, Hashtable<String, IObject> hashtable)
    {
        Scanner scanner;
        try
        {
            scanner = new Scanner(new File(this.filePath+ "\\"+fileName));
            String line = scanner.nextLine();
            while (scanner.hasNextLine())
            {
                line = scanner.nextLine();
                String[] attributes = line.split(",",delimiterLimit);
                IObject object = ObjectFactory.getObject(objectType);
                object.loadData(attributes);
                hashtable.put(object.getKey(), object);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void getAllObjects(Hashtable<String, IObject> hashtable)
    {
        Set<String> keys = hashtable.keySet();
        for (String key: keys)
        {
            hashtable.get(key).getAllData();
        }
    }

    public void getAllAgency()
    {
        this.getAllObjects(this.agency);
    }

    public void getAllCalendars()
    {
        this.getAllObjects(this.calendars);
    }

    public void getAllCalendarDates()
    {
        this.getAllObjects(this.calendarDates);
    }

    public void getAllRoutes()
    {
        this.getAllObjects(this.routes);
    }

    public void getAllStops()
    {
        this.getAllObjects(this.stops);
    }

    public void getAllStopTimes()
    {
        this.getAllObjects(this.stopTimes);
    }

    public void getAllTrips()
    {
        this.getAllObjects(this.trips);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
