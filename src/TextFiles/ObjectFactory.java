package TextFiles;

public final class ObjectFactory
{
    public static String DatePattern = "YYYYMMDD";
    public static String TimePattern = "kk:mm:ss";
    private ObjectFactory(){}

    public static IObject getObject(ObjectType objectType)
    {
        return switch (objectType) {
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
