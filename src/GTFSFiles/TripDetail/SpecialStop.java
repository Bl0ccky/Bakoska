package GTFSFiles.TripDetail;

import Enums.Stop.StopLocationType;
import Enums.Stop.StopWheelchairBoarding;
import GTFSFiles.IGTFSObject;

import java.time.LocalTime;
import java.util.ArrayList;

public class SpecialStop implements IGTFSObject
{
    private String stop_id;
    private String stop_code;
    private String stop_name;
    private String stop_desc;
    private LocalTime arrival_time;
    private LocalTime departure_time;
    private int stop_sequence;
    private double stop_lat;
    private double stop_lon;
    private StopLocationType location_type;
    private String stop_timezone;
    private StopWheelchairBoarding wheelchair_boarding;

    public SpecialStop(String stop_id, String stop_code, String stop_name, String stop_desc, LocalTime arrival_time, LocalTime departure_time, int stop_sequence, double stop_lat, double stop_lon, StopLocationType location_type, String stop_timezone, StopWheelchairBoarding wheelchair_boarding) {
        this.stop_id = stop_id;
        this.stop_code = stop_code;
        this.stop_name = stop_name;
        this.stop_desc = stop_desc;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.stop_sequence = stop_sequence;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
        this.location_type = location_type;
        this.stop_timezone = stop_timezone;
        this.wheelchair_boarding = wheelchair_boarding;
    }

    @Override
    public void loadData(String[] attributes)
    {

    }

    @Override
    public ArrayList<Object> getColumnTypes() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Object> getAttributesForExportGTFS() {
        return new ArrayList<>();
    }

    @Override
    public String getKey() {
        return this.stop_id;
    }

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public String getStop_code() {
        return stop_code;
    }

    public void setStop_code(String stop_code) {
        this.stop_code = stop_code;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public String getStop_desc() {
        return stop_desc;
    }

    public void setStop_desc(String stop_desc) {
        this.stop_desc = stop_desc;
    }

    public LocalTime getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(LocalTime arrival_time) {
        this.arrival_time = arrival_time;
    }

    public LocalTime getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(LocalTime departure_time) {
        this.departure_time = departure_time;
    }

    public int getStop_sequence() {
        return stop_sequence;
    }

    public void setStop_sequence(int stop_sequence) {
        this.stop_sequence = stop_sequence;
    }

    public double getStop_lat() {
        return stop_lat;
    }

    public void setStop_lat(double stop_lat) {
        this.stop_lat = stop_lat;
    }

    public double getStop_lon() {
        return stop_lon;
    }

    public void setStop_lon(double stop_lon) {
        this.stop_lon = stop_lon;
    }

    public StopLocationType getLocation_type() {
        return location_type;
    }

    public void setLocation_type(StopLocationType location_type) {
        this.location_type = location_type;
    }

    public String getStop_timezone() {
        return stop_timezone;
    }

    public void setStop_timezone(String stop_timezone) {
        this.stop_timezone = stop_timezone;
    }

    public StopWheelchairBoarding getWheelchair_boarding() {
        return wheelchair_boarding;
    }

    public void setWheelchair_boarding(StopWheelchairBoarding wheelchair_boarding) {
        this.wheelchair_boarding = wheelchair_boarding;
    }
}
