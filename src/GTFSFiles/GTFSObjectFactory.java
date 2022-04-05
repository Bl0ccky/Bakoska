package GTFSFiles;

public final class GTFSObjectFactory
{
    public static String DatePattern = "yyyyMMdd";
    public static String TimePattern = "H:mm:ss";
    private GTFSObjectFactory(){}

    public static IGTFSObject getGtfsObject(GTFSObjectType gtfsObjectType)
    {
        return switch (gtfsObjectType) {
            case AGENCY -> new Agency();
            case CALENDAR -> new Calendar();
            case CALENDAR_DATE -> new CalendarDate();
            case ROUTE -> new Route();
            case STOP -> new Stop();
            case STOP_TIME -> new StopTime();
            case TRIP -> new Trip();
        };

    }
}
