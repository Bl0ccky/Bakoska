package Enums.Trip;

public enum TripDirectionID
{
    ONE_DIRECTION,
    OPPOSITE_DIRECTION,
    NO_INFO;

    public static TripDirectionID getTripDirectionID(int input)
    {
        return switch (input) {
            case 0 -> ONE_DIRECTION;
            case 1 -> OPPOSITE_DIRECTION;
            default -> NO_INFO;
        };
    }
}
