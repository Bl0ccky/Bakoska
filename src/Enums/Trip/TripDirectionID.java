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

    public static Object getValueForExport(TripDirectionID tripDirectionID)
    {
        return switch (tripDirectionID) {
            case ONE_DIRECTION -> 0;
            case OPPOSITE_DIRECTION -> 1;
            default -> "";
        };
    }
}
