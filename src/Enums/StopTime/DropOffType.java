package Enums.StopTime;

public enum DropOffType
{
    REGULARLY_SCHEDULED_DROP_OFF,
    NO_DROP_OFF_AVAILABLE,
    MUST_PHONE_AGENCY_TO_ARRANGE_DROP_OFF,
    MUST_COORDINATE_WITH_DRIVER_TO_ARRANGE_DROP_OFF;

    public static DropOffType getDropOffType(int input)
    {
        return switch (input) {
            case 1 -> NO_DROP_OFF_AVAILABLE;
            case 2 -> MUST_PHONE_AGENCY_TO_ARRANGE_DROP_OFF;
            case 3 -> MUST_COORDINATE_WITH_DRIVER_TO_ARRANGE_DROP_OFF;
            default -> REGULARLY_SCHEDULED_DROP_OFF;
        };
    }
}
