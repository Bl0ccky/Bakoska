package Enums.Route;

public enum RouteType
{
    TRAM_OR_STREETCAR_OR_LIGHT_RAIL,
    SUBWAY_OR_METRO,
    RAIL,
    BUS,
    FERRY,
    CABLE_TRAM,
    AERIAL_LIFT,
    FUNICULAR,
    TROLLEYBUS,
    MONORAIL,
    NO_INFO;

    public static RouteType getRouteType(int input)
    {
        return switch (input) {
            case 0 -> TRAM_OR_STREETCAR_OR_LIGHT_RAIL;
            case 1 -> SUBWAY_OR_METRO;
            case 2 -> RAIL;
            case 3 -> BUS;
            case 4 -> FERRY;
            case 5 -> CABLE_TRAM;
            case 6 -> AERIAL_LIFT;
            case 7 -> FUNICULAR;
            case 11 -> TROLLEYBUS;
            case 12 -> MONORAIL;
            default -> NO_INFO;
        };
    }
}
