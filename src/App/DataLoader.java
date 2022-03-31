package App;

import TextFiles.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class DataLoader
{
    private String filePath;
    private Hashtable<String, IObject> agency;
    private Hashtable<String, IObject> stops;
    private Hashtable<String, IObject> routes;
    private Hashtable<String, IObject> trips;
    private Hashtable<String, IObject> stopTimes;
    private Hashtable<String, IObject> calendars;
    private Hashtable<String, IObject> calendarDates;
    private final Hashtable<ObjectType, String[]> hashTableColumnNames;
    private final Hashtable<ObjectType, Object[]> hashTableColumnTypes;

    public DataLoader(String filePath)
    {
        this.filePath = filePath;
        this.agency = new Hashtable<>();
        this.stops = new Hashtable<>();
        this.routes = new Hashtable<>();
        this.trips = new Hashtable<>();
        this.stopTimes = new Hashtable<>();
        this.calendars = new Hashtable<>();
        this.calendarDates = new Hashtable<>();
        this.hashTableColumnNames = new Hashtable<>();
        this.hashTableColumnTypes = new Hashtable<>();

    }

    public void loadAllData()
    {
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
        int columnTypesCounter = 0;
        try
        {
            scanner = new Scanner(new File(this.filePath+ "\\"+fileName));
            String columnNames = scanner.nextLine();
            this.getColumnNames(columnNames, objectType);
            String line;
            while (scanner.hasNextLine())
            {
                line = scanner.nextLine();
                String[] attributes = line.split(",",delimiterLimit);
                IObject object = ObjectFactory.getObject(objectType);
                object.loadData(attributes);
                if(columnTypesCounter == 0)
                {
                    this.hashTableColumnTypes.put(objectType, object.getColumnTypes(attributes));
                    columnTypesCounter++;
                }
                hashtable.put(object.getKey(), object);

            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void getColumnNames(String columnNames, ObjectType objectType)
    {
        String[] columns = columnNames.split(",");
        this.hashTableColumnNames.put(objectType, columns);
    }

    private void getAllObjects(Hashtable<String, IObject> hashtable)
    {
        Set<String> keys = hashtable.keySet();
        for (String key: keys)
        {
            hashtable.get(key).getAllData();
        }
    }

    public void writeAllAgency()
    {
        this.getAllObjects(this.agency);
    }

    public void writeAllCalendars()
    {
        this.getAllObjects(this.calendars);
    }

    public void writeAllCalendarDates()
    {
        this.getAllObjects(this.calendarDates);
    }

    public void writeAllRoutes()
    {
        this.getAllObjects(this.routes);
    }

    public void writeAllStops()
    {
        this.getAllObjects(this.stops);
    }

    public void writeAllStopTimes()
    {
        this.getAllObjects(this.stopTimes);
    }

    public void writeAllTrips()
    {
        this.getAllObjects(this.trips);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Hashtable<String, IObject> getAllAgency()
    {
        return this.agency;
    }
    public Hashtable<String, IObject> getAllCalendars()
    {
        return this.calendars;
    }
    public Hashtable<String, IObject> getAllCalendarDates()
    {
        return this.calendarDates;
    }
    public Hashtable<String, IObject> getAllRoutes()
    {
        return this.routes;
    }
    public Hashtable<String, IObject> getAllStops()
    {
        return this.stops;
    }
    public Hashtable<String, IObject> getAllStopTimes()
    {
        return this.stopTimes;
    }
    public Hashtable<String, IObject> getAllTrips()
    {
        return this.trips;
    }

    public String[] getHashTableColumnNames(ObjectType objectType)
    {
        return this.hashTableColumnNames.get(objectType);
    }

    public Object[] getHashTableColumnTypes(ObjectType objectType)
    {
        return this.hashTableColumnTypes.get(objectType);
    }

    public void updateHashTable(Hashtable<String, IObject> hashtable, ObjectType objectType)
    {
        switch (objectType)
        {
            case AGENCY -> this.agency = hashtable;
            case CALENDAR -> this.calendars = hashtable;
            case CALENDAR_DATE -> this.calendarDates = hashtable;
            case ROUTE -> this.routes = hashtable;
            case STOP -> this.stops = hashtable;
            case STOP_TIME -> this.stopTimes = hashtable;
            case TRIP -> this.trips = hashtable;
        }
    }

}
