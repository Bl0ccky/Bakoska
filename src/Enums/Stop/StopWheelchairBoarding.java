package Enums.Stop;

public enum StopWheelchairBoarding
{
    NO_INFO,
    POSSIBLE_WHEELCHAIR,
    NOT_POSSIBLE_WHEELCHAIR;

    public static StopWheelchairBoarding getStopWheelchairBoarding(int input)
    {
        return switch (input) {
            case 1 -> POSSIBLE_WHEELCHAIR;
            case 2 -> NOT_POSSIBLE_WHEELCHAIR;
            default -> NO_INFO;
        };
    }

    public static Object getValueForExport(StopWheelchairBoarding stopWheelchairBoarding)
    {
        return switch (stopWheelchairBoarding) {
            case POSSIBLE_WHEELCHAIR -> 1;
            case NOT_POSSIBLE_WHEELCHAIR -> 2;
            default -> 0;
        };
    }
}
