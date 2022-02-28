import TextFiles.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataLoader
{
    private String filePath;
    private final ArrayList<Agency> agency;
    private final ArrayList<Stop> stops;
    private final ArrayList<Route> routes;
    private final ArrayList<Trip> trips;
    private final ArrayList<StopTime> stopTimes;
    private final ArrayList<Calendar> calendars;
    private final ArrayList<CalendarDate> calendarDates;


    DataLoader(String filePath)
    {
        this.filePath = filePath;
        this.agency = new ArrayList<Agency>();
        this.stops = new ArrayList<Stop>();
        this.routes = new ArrayList<Route>();
        this.trips = new ArrayList<Trip>();
        this.stopTimes = new ArrayList<StopTime>();
        this.calendars = new ArrayList<Calendar>();
        this.calendarDates = new ArrayList<CalendarDate>();
        this.loadStops();
        this.loadRoutes();
    }

    private void loadAgency()
    {
        this.loadGTFS("agency.txt", 7, agencys, this.agency);
    }

    private void loadStops()
    {
        this.loadGTFS("stops.txt", 12, stop, this.stops);
    }

    private void loadRoutes()
    {
        this.loadGTFS("routes.txt", 9, route, this.routes);
    }

    private void loadTrips()
    {
        this.loadGTFS("trips.txt", 9, trip, this.trips);
    }

    private void loadStopTimes()
    {
        this.loadGTFS("stop_times.txt", 10, stopTime, this.stopTimes);
    }

    private void loadCalendars()
    {
        this.loadGTFS("calendar.txt", 10, calendar, this.calendars);
    }

    private void loadCalendarDates()
    {
        this.loadGTFS("calendar_dates.txt", 3, calendarDate, this.calendarDates);
    }

    private void loadGTFS(String fileName, int delimiterLimit,  ArrayList<T> arrayList)
    {
        Scanner scanner;
        try
        {
            scanner = new Scanner(new File(this.filePath+ "\\"+fileName));
            String line;
            while (scanner.hasNextLine())
            {
                line = scanner.nextLine();
                String[] attributes = line.split(",",delimiterLimit);
                T stop = Stop.createStop(attributes);
                arrayList.add(stop);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void getAllStops()
    {
        for (Stop stop: this.stops)
        {
            System.out.printf(
                    "%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\n",
                    stop.getStop_id(),
                    stop.getStop_code(),
                    stop.getStop_name(),
                    stop.getStop_desc(),
                    stop.getStop_lat(),
                    stop.getStop_lon(),
                    stop.getZone_id(),
                    stop.getStop_url(),
                    stop.getLocation_type(),
                    stop.getParent_station(),
                    stop.getStop_timezone(),
                    stop.getWheelchair_boarding());
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
