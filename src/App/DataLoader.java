package App;


import GTFSFiles.Agency;
import GTFSFiles.Calendar;
import GTFSFiles.CalendarDate;
import GTFSFiles.GTFSObjectFactory;
import GTFSFiles.GTFSObjectType;
import GTFSFiles.IGTFSObject;
import GTFSFiles.Route;
import GTFSFiles.Stop;
import GTFSFiles.StopTime;
import GTFSFiles.Trip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

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
    private final Hashtable<GTFSObjectType, ArrayList<Object>> hashTableColumnTypes;

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
        this.loadGTFS("agency.txt", GTFSObjectType.AGENCY, this.agency);
    }

    private void loadStops()
    {
        this.loadGTFS("stops.txt", GTFSObjectType.STOP, this.stops);
    }

    private void loadRoutes()
    {
        this.loadGTFS("routes.txt", GTFSObjectType.ROUTE, this.routes);
    }

    private void loadTrips()
    {
        this.loadGTFS("trips.txt", GTFSObjectType.TRIP, this.trips);
    }

    private void loadStopTimes()
    {
        this.loadGTFS("stop_times.txt", GTFSObjectType.STOP_TIME, this.stopTimes);
    }

    private void loadCalendars()
    {
        this.loadGTFS("calendar.txt", GTFSObjectType.CALENDAR, this.calendars);
    }

    private void loadCalendarDates()
    {
        this.loadGTFS("calendar_dates.txt", GTFSObjectType.CALENDAR_DATE, this.calendarDates);
    }

    private void loadGTFS(String fileName, GTFSObjectType gtfsObjectType, Hashtable<String, IGTFSObject> hashtable)
    {

        Scanner scanner;
        int columnTypesCounter = 0;
        try
        {
            scanner = new Scanner(new File(this.filePath+ "\\"+fileName), StandardCharsets.UTF_8);
            String columnNames = scanner.nextLine();
            this.createColumnNames(columnNames, gtfsObjectType);
            String line;
            if(!scanner.hasNextLine())
            {
                this.createColumnTypesForNewFile(gtfsObjectType);
            }
            while (scanner.hasNextLine())
            {
                line = scanner.nextLine();
                String[] attributes = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)",-1);
                IGTFSObject object = GTFSObjectFactory.getGtfsObject(gtfsObjectType);
                object.loadData(attributes, this.hashTableColumnNames.get(gtfsObjectType));
                if(columnTypesCounter == 0)
                {
                    this.hashTableColumnTypes.put(gtfsObjectType, object.getColumnTypes());
                    columnTypesCounter++;
                }
                hashtable.put(object.getKey(), object);

            }
            scanner.close();
            this.createColumnNamesForNewFile(gtfsObjectType);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeAgency()
    {
        this.writeGTFS("agency.txt", GTFSObjectType.AGENCY, this.agency);
    }

    public void writeStops()
    {
        this.writeGTFS("stops.txt", GTFSObjectType.STOP, this.stops);
    }

    public void writeRoutes()
    {
        this.writeGTFS("routes.txt", GTFSObjectType.ROUTE, this.routes);
    }

    public void writeTrips()
    {
        this.writeGTFS("trips.txt", GTFSObjectType.TRIP, this.trips);
    }

    public void writeStopTimes()
    {
        this.writeGTFS("stop_times.txt", GTFSObjectType.STOP_TIME, this.stopTimes);
    }

    public void writeCalendars()
    {
        this.writeGTFS("calendar.txt", GTFSObjectType.CALENDAR, this.calendars);
    }

    public void writeCalendarDates()
    {
        this.writeGTFS("calendar_dates.txt", GTFSObjectType.CALENDAR_DATE, this.calendarDates);
    }

    private void writeGTFS(String fileName, GTFSObjectType gtfsObjectType, Hashtable<String, IGTFSObject> hashtable)
    {
        FileWriter fileWriter;
        String[] columnNames = this.getHashTableColumnNames(gtfsObjectType);
        try
        {
            fileWriter = getFileWriter(fileName, columnNames);
            if(!hashtable.isEmpty())
            {
                ArrayList<String> keys = new ArrayList<>(hashtable.keySet());
                for (String key : keys)
                {
                    ArrayList<Object> attributes = hashtable.get(key).getAttributesForExportGTFS();
                    for (int i = 0; i < attributes.size(); i++)
                    {
                        fileWriter.write(String.valueOf(attributes.get(i)));
                        if(i != attributes.size()-1)
                        {
                            fileWriter.write(",");
                        }
                    }
                    fileWriter.write("\n");
                }

            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeStopsCSV()
    {
        this.writeCSV("detailed_stops.csv", GTFSObjectType.STOP, this.stops);
    }

    public void writeTripCSV()
    {
        this.writeCSV("detailed_trips.csv", GTFSObjectType.TRIP, this.trips);

    }

    private void writeCSV(String fileName,GTFSObjectType gtfsObjectType, Hashtable<String, IGTFSObject> hashtable)
    {
        FileWriter fileWriter;
        String[] columnNames = this.getDetailedColumnNames(gtfsObjectType);
        if(gtfsObjectType == GTFSObjectType.TRIP)
        {
            this.createTripDetails();
        }
        try
        {
            fileWriter = getFileWriter(fileName, columnNames);
            if(!hashtable.isEmpty())
            {
                ArrayList<String> keys = new ArrayList<>(hashtable.keySet());
                for (String key : keys)
                {
                    Object[] attributes;
                    if(gtfsObjectType == GTFSObjectType.TRIP)
                    {
                        attributes = ((Trip)hashtable.get(key)).getDetailedAttributes();
                    }
                    else
                    {
                        attributes = ((Stop)hashtable.get(key)).getDetailedAttributes();
                    }

                    for (int i = 0; i < attributes.length; i++)
                    {
                        fileWriter.write(String.valueOf(attributes[i]));
                        if(i != attributes.length-1)
                        {
                            fileWriter.write(",");
                        }
                    }
                    fileWriter.write("\n");
                }
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private FileWriter getFileWriter(String fileName, String[] columnNames) throws IOException {
        FileWriter fileWriter;
        fileWriter = new FileWriter(this.filePath+ "\\"+fileName);
        for (int i = 0; i < columnNames.length; i++)
        {
            fileWriter.write(columnNames[i]);
            if(i != columnNames.length-1)
            {
                fileWriter.write(",");
            }
        }
        fileWriter.write("\n");
        return fileWriter;
    }


    public void createTripDetails()
    {
        ArrayList<String> tripKeys = new ArrayList<>(this.trips.keySet());
        int counter = 0;
        int allTrips = tripKeys.size();
        for (String tripKey : tripKeys)
        {
            ((Trip)this.trips.get(tripKey)).createSpecialStopHashTable(this.stopTimes, this.stops);
            ((Trip)this.trips.get(tripKey)).createDetailedAttributes();

            System.out.println(counter++ + "/" + allTrips);
        }
    }

    public String[] createColumnNamesForNewFile(GTFSObjectType gtfsObjectType)
    {
        String[] columnNames = {};
        switch (gtfsObjectType) {
            case AGENCY -> columnNames = new String[]{"agency_id", "agency_name", "agency_url", "agency_timezone", "agency_lang", "agency_phone", "agency_fare_url"};
            case CALENDAR -> columnNames = new String[]{"service_id", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday", "start_date", "end_date"};
            case CALENDAR_DATE -> columnNames = new String[]{"service_id", "date", "exception_type"};
            case ROUTE -> columnNames = new String[]{"route_id", "agency_id", "route_short_name", "route_long_name", "route_desc", "route_type", "route_url", "route_color", "route_text_color"};
            case STOP -> columnNames = new String[]{"stop_id", "stop_code", "stop_name", "stop_desc", "stop_lat", "stop_lon", "zone_id", "stop_url", "location_type", "parent_station", "stop_timezone", "wheelchair_boarding"};
            case STOP_TIME -> columnNames = new String[]{"trip_id", "arrival_time", "departure_time", "stop_id", "stop_sequence", "stop_headsign", "pickup_type", "drop_off_type", "shape_dist_traveled", "timepoint"};
            case TRIP -> columnNames = new String[]{"trip_id", "route_id", "service_id", "trip_headsign", "trip_short_name", "direction_id", "block_id", "shape_id", "wheelchair_accessible"};
        }
        this.hashTableColumnNames.put(gtfsObjectType, columnNames);
        return columnNames;
    }

    public void createColumnTypesForNewFile(GTFSObjectType gtfsObjectType)
    {
        ArrayList<Object> columnTypes = null;
        switch (gtfsObjectType) {
            case AGENCY -> columnTypes =  new Agency().getColumnTypes();
            case CALENDAR -> columnTypes = new Calendar().getColumnTypes();
            case CALENDAR_DATE -> columnTypes = new CalendarDate().getColumnTypes();
            case ROUTE -> columnTypes = new Route().getColumnTypes();
            case STOP -> columnTypes = new Stop().getColumnTypes();
            case STOP_TIME -> columnTypes = new StopTime().getColumnTypes();
            case TRIP -> columnTypes = new Trip().getColumnTypes();
        }
        this.hashTableColumnTypes.put(gtfsObjectType, columnTypes);
    }

    private void createColumnNames(String columnNames, GTFSObjectType gtfsObjectType)
    {
        String[] columns = columnNames.split(",");
        this.hashTableColumnNames.put(gtfsObjectType, columns);
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

    public ArrayList<Object> getHashTableColumnTypes(GTFSObjectType gtfsObjectType)
    {
        return this.hashTableColumnTypes.get(gtfsObjectType);
    }

    public String[] getDetailedColumnNames(GTFSObjectType gtfsObjectType)
    {
        if(gtfsObjectType == GTFSObjectType.TRIP)
        {
            return new String[]{"trip_id", "trip_headsign", "first_stop_arrival_time", "last_stop_departure_time", "first_stop_id", "first_stop_name", "last_stop_id", "last_stop_name"};
        }
        else
        {
            return new String[]{"stop_id", "stop_name", "stop_lat", "stop_lon"};
        }
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
