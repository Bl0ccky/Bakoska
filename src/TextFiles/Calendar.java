package TextFiles;

import Enums.Calendar.DayServiceAvailability;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

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

    public Calendar() {
        this.service_id = "";
        this.monday = DayServiceAvailability.NO_INFO;
        this.tuesday = DayServiceAvailability.NO_INFO;
        this.wednesday = DayServiceAvailability.NO_INFO;
        this.thursday = DayServiceAvailability.NO_INFO;
        this.friday = DayServiceAvailability.NO_INFO;
        this.saturday = DayServiceAvailability.NO_INFO;
        this.sunday = DayServiceAvailability.NO_INFO;
        this.start_date = LocalDate.now();
        this.end_date = LocalDate.now();
    }

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
    public ArrayList<Object> getColumnTypes()
    {
        ArrayList<Object> columnTypes = new ArrayList<>();
        columnTypes.add(this.service_id);
        columnTypes.add(this.monday);
        columnTypes.add(this.tuesday);
        columnTypes.add(this.wednesday);
        columnTypes.add(this.thursday);
        columnTypes.add(this.friday);
        columnTypes.add(this.saturday);
        columnTypes.add(this.sunday);
        columnTypes.add(this.start_date);
        columnTypes.add(this.end_date);

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
