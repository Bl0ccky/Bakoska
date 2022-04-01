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
    private Hashtable<String, IGTFSObject> agency;
    private Hashtable<String, IGTFSObject> stops;
    private Hashtable<String, IGTFSObject> routes;
    private Hashtable<String, IGTFSObject> trips;
    private Hashtable<String, IGTFSObject> stopTimes;
    private Hashtable<String, IGTFSObject> calendars;
    private Hashtable<String, IGTFSObject> calendarDates;
    private final Hashtable<GTFSObjectType, String[]> hashTableColumnNames;
    private final Hashtable<GTFSObjectType, Object[]> hashTableColumnTypes;

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
        this.loadGTFS("agency.txt", 7, GTFSObjectType.AGENCY, this.agency);
    }

    private void loadStops()
    {
        this.loadGTFS("stops.txt", 12, GTFSObjectType.STOP, this.stops);
    }

    private void loadRoutes()
    {
        this.loadGTFS("routes.txt", 9, GTFSObjectType.ROUTE, this.routes);
    }

    private void loadTrips()
    {
        this.loadGTFS("trips.txt", 9, GTFSObjectType.TRIP, this.trips);
    }

    private void loadStopTimes()
    {
        this.loadGTFS("stop_times.txt", 10, GTFSObjectType.STOP_TIME, this.stopTimes);
    }

    private void loadCalendars()
    {
        this.loadGTFS("calendar.txt", 10, GTFSObjectType.CALENDAR, this.calendars);
    }

    private void loadCalendarDates()
    {
        this.loadGTFS("calendar_dates.txt", 3, GTFSObjectType.CALENDAR_DATE, this.calendarDates);
    }

    private void loadGTFS(String fileName, int delimiterLimit, GTFSObjectType gtfsObjectType, Hashtable<String, IGTFSObject> hashtable)
    {
        Scanner scanner;
        int columnTypesCounter = 0;
        try
        {
            scanner = new Scanner(new File(this.filePath+ "\\"+fileName));
            String columnNames = scanner.nextLine();
            this.getColumnNames(columnNames, gtfsObjectType);
            String line;
            while (scanner.hasNextLine())
            {
                line = scanner.nextLine();
                String[] attributes = line.split(",",delimiterLimit);
                IGTFSObject object = GTFSObjectFactory.getGtfsObject(gtfsObjectType);
                object.loadData(attributes);
                if(columnTypesCounter == 0)
                {
                    this.hashTableColumnTypes.put(gtfsObjectType, object.getColumnTypes(attributes));
                    columnTypesCounter++;
                }
                hashtable.put(object.getKey(), object);

            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void getColumnNames(String columnNames, GTFSObjectType gtfsObjectType)
    {
        String[] columns = columnNames.split(",");
        this.hashTableColumnNames.put(gtfsObjectType, columns);
    }

    private void getAllObjects(Hashtable<String, IGTFSObject> hashtable)
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

    public Hashtable<String, IGTFSObject> getAllAgency()
    {
        return this.agency;
    }
    public Hashtable<String, IGTFSObject> getAllCalendars()
    {
        return this.calendars;
    }
    public Hashtable<String, IGTFSObject> getAllCalendarDates()
    {
        return this.calendarDates;
    }
    public Hashtable<String, IGTFSObject> getAllRoutes()
    {
        return this.routes;
    }
    public Hashtable<String, IGTFSObject> getAllStops()
    {
        return this.stops;
    }
    public Hashtable<String, IGTFSObject> getAllStopTimes()
    {
        return this.stopTimes;
    }
    public Hashtable<String, IGTFSObject> getAllTrips()
    {
        return this.trips;
    }

    public String[] getHashTableColumnNames(GTFSObjectType gtfsObjectType)
    {
        return this.hashTableColumnNames.get(gtfsObjectType);
    }

    public Object[] getHashTableColumnTypes(GTFSObjectType gtfsObjectType)
    {
        return this.hashTableColumnTypes.get(gtfsObjectType);
    }

    public void updateHashTable(Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType)
    {
        switch (gtfsObjectType)
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
