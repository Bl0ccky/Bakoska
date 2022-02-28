package TextFiles;

import Enums.Calendar.DayServiceAvailability;

import java.util.Date;

public class Calendar
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

    public Calendar(String service_id, DayServiceAvailability monday, DayServiceAvailability tuesday, DayServiceAvailability wednesday, DayServiceAvailability thursday, DayServiceAvailability friday, DayServiceAvailability saturday, DayServiceAvailability sunday, Date start_date, Date end_date)
    {
        this.service_id = service_id;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.start_date = start_date;
        this.end_date = end_date;
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
