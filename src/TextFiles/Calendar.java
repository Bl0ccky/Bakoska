package TextFiles;

import Enums.Calendar.DayServiceAvailability;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Calendar implements IGTFSObject
{
    private String service_id;
    private DayServiceAvailability monday;
    private DayServiceAvailability tuesday;
    private DayServiceAvailability wednesday;
    private DayServiceAvailability thursday;
    private DayServiceAvailability friday;
    private DayServiceAvailability saturday;
    private DayServiceAvailability sunday;
    private LocalDate start_date;
    private LocalDate end_date;

    public Calendar(){}

    @Override
    public void loadData(String[] attributes)
    {
        DateTimeFormatter dateFormat = new DateTimeFormatterBuilder().appendPattern(GTFSObjectFactory.DatePattern).toFormatter();
        this.service_id = attributes[0];
        if(attributes[1] != null && !attributes[1].equals(""))
        {
            this.monday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[1]));
        }
        else
        {
            this.monday = DayServiceAvailability.NO_INFO;
        }

        if(attributes[2] != null && !attributes[2].equals(""))
        {
            this.tuesday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[2]));
        }
        else
        {
            this.tuesday = DayServiceAvailability.NO_INFO;
        }

        if(attributes[3] != null && !attributes[3].equals(""))
        {
            this.wednesday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[3]));
        }
        else
        {
            this.wednesday = DayServiceAvailability.NO_INFO;
        }

        if(attributes[4] != null && !attributes[4].equals(""))
        {
            this.thursday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[4]));
        }
        else
        {
            this.thursday = DayServiceAvailability.NO_INFO;
        }

        if(attributes[5] != null && !attributes[5].equals(""))
        {
            this.friday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[5]));
        }
        else
        {
            this.friday = DayServiceAvailability.NO_INFO;
        }

        if(attributes[6] != null && !attributes[6].equals(""))
        {
            this.saturday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[6]));
        }
        else
        {
            this.saturday = DayServiceAvailability.NO_INFO;
        }


        if(attributes[7] != null && !attributes[7].equals(""))
        {
            this.sunday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[7]));
        }
        else
        {
            this.sunday = DayServiceAvailability.NO_INFO;
        }

        if(attributes[8] != null && !attributes[8].equals(""))
        {
            this.start_date = LocalDate.parse(attributes[8], dateFormat);
        }

        if(attributes[9] != null && !attributes[9].equals(""))
        {
            this.end_date = LocalDate.parse(attributes[9], dateFormat);
        }

    }

    @Override
    public void getAllData()
    {
        System.out.printf(
                "%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\n",
                this.service_id,
                this.monday,
                this.tuesday,
                this.wednesday,
                this.thursday,
                this.friday,
                this.saturday,
                this.sunday,
                this.start_date,
                this.end_date);
    }

    @Override
    public Object[] getColumnTypes(String[] attributes)
    {
        Object[] columnTypes = new Object[attributes.length];
        columnTypes[0] = this.service_id;
        columnTypes[1] = this.monday;
        columnTypes[2] = this.tuesday;
        columnTypes[3] = this.wednesday;
        columnTypes[4] = this.thursday;
        columnTypes[5] = this.friday;
        columnTypes[6] = this.saturday;
        columnTypes[7] = this.sunday;
        columnTypes[8] = this.start_date;
        columnTypes[9] = this.end_date;

        return columnTypes;
    }

    @Override
    public String getKey() {
        return this.service_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public DayServiceAvailability getMonday() {
        return monday;
    }

    public void setMonday(DayServiceAvailability monday) {
        this.monday = monday;
    }

    public DayServiceAvailability getTuesday() {
        return tuesday;
    }

    public void setTuesday(DayServiceAvailability tuesday) {
        this.tuesday = tuesday;
    }

    public DayServiceAvailability getWednesday() {
        return wednesday;
    }

    public void setWednesday(DayServiceAvailability wednesday) {
        this.wednesday = wednesday;
    }

    public DayServiceAvailability getThursday() {
        return thursday;
    }

    public void setThursday(DayServiceAvailability thursday) {
        this.thursday = thursday;
    }

    public DayServiceAvailability getFriday() {
        return friday;
    }

    public void setFriday(DayServiceAvailability friday) {
        this.friday = friday;
    }

    public DayServiceAvailability getSaturday() {
        return saturday;
    }

    public void setSaturday(DayServiceAvailability saturday) {
        this.saturday = saturday;
    }

    public DayServiceAvailability getSunday() {
        return sunday;
    }

    public void setSunday(DayServiceAvailability sunday) {
        this.sunday = sunday;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }


}
