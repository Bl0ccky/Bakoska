package Enums.Trip;

public enum TripWheelchairAccessible
{
    NO_INFO,
    AT_LEAST_ONE_WHEELCHAIR_RIDER,
    NO_WHEELCHAIR_RIDERS;

    public static TripWheelchairAccessible getTripWheelchairAccessible(int input)
    {
        return switch (input) {
            case 1 -> AT_LEAST_ONE_WHEELCHAIR_RIDER;
            case 2 -> NO_WHEELCHAIR_RIDERS;
            default -> NO_INFO;
        };
    }

    public static Object getValueForExport(TripWheelchairAccessible tripWheelchairAccessible)
    {
        return switch (tripWheelchairAccessible) {
            case AT_LEAST_ONE_WHEELCHAIR_RIDER -> 1;
            case NO_WHEELCHAIR_RIDERS -> 2;
            default -> 0;
        };
    }
}
