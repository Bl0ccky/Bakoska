package TextFiles;

import Enums.Calendar.DayServiceAvailability;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar implements IObject
{
    private String service_id;
    private DayServiceAvailability monday;
    private DayServiceAvailability tuesday;
    private DayServiceAvailability wednesday;
    private DayServiceAvailability thursday;
    private DayServiceAvailability friday;
    private DayServiceAvailability saturday;
    private DayServiceAvailability sunday;
    private Date start_date;
    private Date end_date;

    public Calendar(){}

    @Override
    public void loadData(String[] attributes)
    {
        this.service_id = attributes[0];
        if(attributes[1] != null && !attributes[1].equals(""))
        {
            this.monday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[1]));
        }

        if(attributes[2] != null && !attributes[2].equals(""))
        {
            this.tuesday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[2]));
        }

        if(attributes[3] != null && !attributes[3].equals(""))
        {
            this.wednesday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[3]));
        }

        if(attributes[4] != null && !attributes[4].equals(""))
        {
            this.thursday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[4]));
        }

        if(attributes[5] != null && !attributes[5].equals(""))
        {
            this.friday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[5]));
        }

        if(attributes[6] != null && !attributes[6].equals(""))
        {
            this.saturday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[6]));
        }

        if(attributes[7] != null && !attributes[7].equals(""))
        {
            this.sunday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[7]));
        }

        if(attributes[8] != null && !attributes[8].equals(""))
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ObjectFactory.DatePattern);
            try {
                this.start_date = simpleDateFormat.parse(attributes[8]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(attributes[9] != null && !attributes[9].equals(""))
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ObjectFactory.DatePattern);
            try {
                this.end_date = simpleDateFormat.parse(attributes[9]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void getAllData()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ObjectFactory.DatePattern);
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
                simpleDateFormat.format(this.start_date),
                simpleDateFormat.format(this.end_date));
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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }


}
