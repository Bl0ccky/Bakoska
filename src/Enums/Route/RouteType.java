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

    public static Object getValueForExport(RouteType routeType)
    {
        return switch (routeType) {
            case TRAM_OR_STREETCAR_OR_LIGHT_RAIL -> 0;
            case SUBWAY_OR_METRO -> 1;
            case RAIL -> 2;
            case BUS -> 3;
            case FERRY -> 4;
            case CABLE_TRAM -> 5;
            case AERIAL_LIFT -> 6;
            case FUNICULAR -> 7;
            case TROLLEYBUS -> 8;
            case MONORAIL -> 9;
            default -> "";
        };
    }
}
