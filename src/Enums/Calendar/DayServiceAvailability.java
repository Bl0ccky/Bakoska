package Enums.Calendar;

public enum DayServiceAvailability
{
    AVAILABLE,
    NOT_AVAILABLE;

    public static DayServiceAvailability getDayServiceAvailability(int input)
    {
        return switch (input) {
            case 0 -> NOT_AVAILABLE;
            case 1 -> AVAILABLE;
            default -> null;
        };
    }
}
