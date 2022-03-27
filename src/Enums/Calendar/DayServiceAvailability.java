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
}
