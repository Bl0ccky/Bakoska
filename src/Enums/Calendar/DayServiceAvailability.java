package Enums.Calendar;

public enum DayServiceAvailability
{
    AVAILABLE,
    NOT_AVAILABLE,
    NO_INFO;

    public static DayServiceAvailability getDayServiceAvailability(int input)
    {
        return switch (input) {
            case 0 -> NOT_AVAILABLE;
            case 1 -> AVAILABLE;
            default -> NO_INFO;
        };
    }

    public static Object getValueForExport(DayServiceAvailability dayServiceAvailability)
    {
        return switch (dayServiceAvailability) {
            case NOT_AVAILABLE -> 0;
            case AVAILABLE -> 1;
            default -> "";
        };
    }
}
