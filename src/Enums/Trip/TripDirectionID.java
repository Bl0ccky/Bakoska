package Enums.Trip;

public enum TripDirectionID
{
    ONE_DIRECTION,
    OPPOSITE_DIRECTION;

    public static TripDirectionID getTripDirectionID(int input)
    {
        return switch (input) {
            case 0 -> ONE_DIRECTION;
            case 1 -> OPPOSITE_DIRECTION;
            default -> null;
        };
    }
}
