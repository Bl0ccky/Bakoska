package Enums.CalendarDate;

public enum ExceptionType
{
    ADDED,
    REMOVED;

    public static ExceptionType getExceptionType(int input)
    {
        return switch (input) {
            case 1 -> ADDED;
            case 2 -> REMOVED;
            default -> null;
        };
    }
}
