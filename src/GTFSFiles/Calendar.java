package GTFSFiles;

import Enums.Calendar.DayServiceAvailability;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

public class Calendar implements IGTFSObject {
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
    public void loadData(String[] attributes, String[] columnNames)
    {
        DateTimeFormatter dateFormat = new DateTimeFormatterBuilder().appendPattern(GTFSObjectFactory.DatePattern).toFormatter();
        for (int i = 0; i < columnNames.length; i++) {
            switch (columnNames[i]) {
                case "service_id":
                    this.service_id = attributes[i];
                    break;
                case "monday":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.monday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[i]));
                    } else {
                        this.monday = DayServiceAvailability.NO_INFO;
                    }
                    break;
                case "tuesday":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.tuesday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[i]));
                    } else {
                        this.tuesday = DayServiceAvailability.NO_INFO;
                    }
                    break;
                case "wednesday":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.wednesday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[i]));
                    } else {
                        this.wednesday = DayServiceAvailability.NO_INFO;
                    }
                    break;
                case "thursday":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.thursday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[i]));
                    } else {
                        this.thursday = DayServiceAvailability.NO_INFO;
                    }
                    break;
                case "friday":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.friday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[i]));
                    } else {
                        this.friday = DayServiceAvailability.NO_INFO;
                    }
                    break;
                case "saturday":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.saturday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[i]));
                    } else {
                        this.saturday = DayServiceAvailability.NO_INFO;
                    }
                    break;
                case "sunday":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.sunday = DayServiceAvailability.getDayServiceAvailability(Integer.parseInt(attributes[i]));
                    } else {
                        this.sunday = DayServiceAvailability.NO_INFO;
                    }
                    break;
                case "start_date":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.start_date = LocalDate.parse(attributes[i], dateFormat);
                    }
                    break;
                case "end_date":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.end_date = LocalDate.parse(attributes[i], dateFormat);
                    }
                    break;
            }
        }

    }


    @Override
    public ArrayList<Object> getColumnTypes() {
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
    public ArrayList<Object> getAttributesForExportGTFS() {
        ArrayList<Object> attributesForExport = new ArrayList<>();
        attributesForExport.add(this.service_id);
        attributesForExport.add(DayServiceAvailability.getValueForExport(this.monday));
        attributesForExport.add(DayServiceAvailability.getValueForExport(this.tuesday));
        attributesForExport.add(DayServiceAvailability.getValueForExport(this.wednesday));
        attributesForExport.add(DayServiceAvailability.getValueForExport(this.thursday));
        attributesForExport.add(DayServiceAvailability.getValueForExport(this.friday));
        attributesForExport.add(DayServiceAvailability.getValueForExport(this.saturday));
        attributesForExport.add(DayServiceAvailability.getValueForExport(this.sunday));
        attributesForExport.add(this.start_date.format(DateTimeFormatter.ofPattern(GTFSObjectFactory.DatePattern)));
        attributesForExport.add(this.end_date.format(DateTimeFormatter.ofPattern(GTFSObjectFactory.DatePattern)));
        return attributesForExport;
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
