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
}
