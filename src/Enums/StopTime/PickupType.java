package Enums.StopTime;

public enum PickupType
{
    REGULARLY_SCHEDULED_PICKUP,
    NO_PICKUP_AVAILABLE,
    MUST_PHONE_AGENCY_TO_ARRANGE_PICKUP,
    MUST_COORDINATE_WITH_DRIVER_TO_ARRANGE_PICKUP;

    public static PickupType getPickupType(int input)
    {
        return switch (input) {
            case 1 -> NO_PICKUP_AVAILABLE;
            case 2 -> MUST_PHONE_AGENCY_TO_ARRANGE_PICKUP;
            case 3 -> MUST_COORDINATE_WITH_DRIVER_TO_ARRANGE_PICKUP;
            default -> REGULARLY_SCHEDULED_PICKUP;
        };
    }
}
