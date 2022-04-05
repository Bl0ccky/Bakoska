package Enums.StopTime;

public enum TimePoint
{
    APPROXIMATE_TIMES,
    EXACT_TIMES;

    public static TimePoint getTimePoint(int input)
    {
        if(input == 0)
        {
            return APPROXIMATE_TIMES;
        }
        else
        {
            return EXACT_TIMES;
        }
    }

    public static Object getValueForExport(TimePoint timePoint)
    {
        if(timePoint == APPROXIMATE_TIMES)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
