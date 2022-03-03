import TextFiles.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataLoader
{
    private String filePath;
    private final ArrayList<IObject> agency;
    private final ArrayList<IObject> stops;
    private final ArrayList<IObject> routes;
    private final ArrayList<IObject> trips;
    private final ArrayList<IObject> stopTimes;
    private final ArrayList<IObject> calendars;
    private final ArrayList<IObject> calendarDates;


    DataLoader(String filePath)
    {
        this.filePath = filePath;
        this.agency = new ArrayList<>();
        this.stops = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.trips = new ArrayList<>();
        this.stopTimes = new ArrayList<>();
        this.calendars = new ArrayList<>();
        this.calendarDates = new ArrayList<>();
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

    private void loadGTFS(String fileName, int delimiterLimit, ObjectType objectType, ArrayList<IObject> arrayList)
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
                arrayList.add(object);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void getAllAgency()
    {
        for (IObject object: this.agency)
        {
            object.getAllData();
        }
    }

    public void getAllCalendars()
    {
        for (IObject object: this.calendars)
        {
            object.getAllData();
        }
    }
    public void getAllCalendarDates()
    {
        for (IObject object: this.calendarDates)
        {
            object.getAllData();
        }
    }

    public void getAllRoutes()
    {
        for (IObject object: this.routes)
        {
            object.getAllData();
        }
    }

    public void getAllStops()
    {
        for (IObject object: this.stops)
        {
            object.getAllData();
        }
    }

    public void getAllStopTimes()
    {
        for (IObject object: this.stopTimes)
        {
            object.getAllData();
        }
    }

    public void getAllTrips()
    {
        for (IObject object: this.trips)
        {
            object.getAllData();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
