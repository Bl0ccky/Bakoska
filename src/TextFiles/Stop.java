package TextFiles;

import Enums.Stop.StopLocationType;
import Enums.Stop.StopWheelchairBoarding;

public class Stop
{
    private String stop_id;
    private String stop_code;
    private String stop_name;
    private String stop_desc;
    private double stop_lat;
    private double stop_lon;
    private String zone_id;
    private String stop_url;
    private StopLocationType location_type;
    private String parent_station;
    private String stop_timezone;
    private StopWheelchairBoarding wheelchair_boarding;

    public Stop(String stop_id, String stop_code, String stop_name, String stop_desc, double stop_lat, double stop_lon, String zone_id, String stop_url, StopLocationType location_type, String parent_station, String stop_timezone, StopWheelchairBoarding wheelchair_boarding)
    {
        this.stop_id = stop_id;
        this.stop_code = stop_code;
        this.stop_name = stop_name;
        this.stop_desc = stop_desc;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
        this.zone_id = zone_id;
        this.stop_url = stop_url;
        this.location_type = location_type;
        this.parent_station = parent_station;
        this.stop_timezone = stop_timezone;
        this.wheelchair_boarding = wheelchair_boarding;
    }

    public static Stop createStop(String[] attributes)
    {
        String stop_id = attributes[0];
        String stop_code = attributes[1];
        String stop_name = attributes[2];
        String stop_desc = attributes[3];

        double stop_lat = 0;
        if(attributes[4] != null && !attributes[4].equals(""))
        {
            stop_lat = Double.parseDouble(attributes[4]);
        }

        double stop_lon = 0;
        if(attributes[5] != null && !attributes[5].equals(""))
        {
            stop_lon = Double.parseDouble(attributes[5]);
        }

        String zone_id = attributes[6];
        String stop_url = attributes[7];

        StopLocationType location_type = null;
        if(attributes[8] != null && !attributes[8].equals(""))
        {
            location_type = StopLocationType.getStopLocationType(Integer.parseInt(attributes[8]));
        }

        String parent_station = attributes[9];
        String stop_timezone = attributes[10];

        StopWheelchairBoarding wheelchair_boarding = null;
        if(attributes[11] != null && !attributes[11].equals(""))
        {
            wheelchair_boarding = StopWheelchairBoarding.getStopWheelchairBoarding(Integer.parseInt(attributes[11]));
        }

        return new Stop(stop_id, stop_code, stop_name, stop_desc, stop_lat, stop_lon, zone_id, stop_url, location_type, parent_station, stop_timezone, wheelchair_boarding);
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

    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getStop_url() {
        return stop_url;
    }

    public void setStop_url(String stop_url) {
        this.stop_url = stop_url;
    }

    public StopLocationType getLocation_type() {
        return location_type;
    }

    public void setLocation_type(StopLocationType location_type) {
        this.location_type = location_type;
    }

    public String getParent_station() {
        return parent_station;
    }

    public void setParent_station(String parent_station) {
        this.parent_station = parent_station;
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
