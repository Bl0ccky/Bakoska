package Enums.Stop;

public enum StopLocationType
{
    STOP,
    STATION,
    ENTRANCE_OR_EXIT,
    GENERIC_NODE,
    BOARDING_AREA;

    public static StopLocationType getStopLocationType(int input)
    {
        return switch (input) {
            case 1 -> STATION;
            case 2 -> ENTRANCE_OR_EXIT;
            case 3 -> GENERIC_NODE;
            case 4 -> BOARDING_AREA;
            default -> STOP;
        };
    }

    public static Object getValueForExport(StopLocationType stopLocationType)
    {
        return switch (stopLocationType) {
            case STATION -> 1;
            case ENTRANCE_OR_EXIT -> 2;
            case GENERIC_NODE -> 3;
            case BOARDING_AREA -> 4;
            default -> 0;
        };
    }
}
