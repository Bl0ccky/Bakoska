package Enums.StopTime;

public enum TimePoint
{
    APPROXIMATE_TIMES,
    EXACT_TIMES;

    public static TimePoint getTimePoint(int input)
    {
        if(input == 1)
        {
            return EXACT_TIMES;
        }
        else
        {
            return APPROXIMATE_TIMES;
        }
    }
}
