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
}
