package GTFSFiles;

import Enums.Stop.StopLocationType;
import Enums.Stop.StopWheelchairBoarding;

import java.util.ArrayList;

public class Stop implements IGTFSObject{
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

    public Stop() {
        this.stop_id = "";
        this.stop_code = "";
        this.stop_name = "";
        this.stop_desc = "";
        this.stop_lat = 0;
        this.stop_lon = 0;
        this.zone_id = "";
        this.stop_url = "";
        this.location_type = StopLocationType.STOP;
        this.parent_station = "";
        this.stop_timezone = "";
        this.wheelchair_boarding = StopWheelchairBoarding.NO_INFO;
    }

    @Override
    public void loadData(String[] attributes)
    {
        this.stop_id = attributes[0];
        this.stop_code = attributes[1];
        this.stop_name = attributes[2];
        this.stop_desc = attributes[3];

        if(attributes[4] != null && !attributes[4].equals(""))
        {
            this.stop_lat = Double.parseDouble(attributes[4]);
        }

        stop_lon = 0;
        if(attributes[5] != null && !attributes[5].equals(""))
        {
            this.stop_lon = Double.parseDouble(attributes[5]);
        }

        this.zone_id = attributes[6];
        this.stop_url = attributes[7];

        if(attributes[8] != null && !attributes[8].equals(""))
        {
            this.location_type = StopLocationType.getStopLocationType(Integer.parseInt(attributes[8]));
        }
        else
        {
            this.location_type = StopLocationType.STOP;
        }

        this.parent_station = attributes[9];
        this.stop_timezone = attributes[10];

        if(attributes[11] != null && !attributes[11].equals(""))
        {
            this.wheelchair_boarding = StopWheelchairBoarding.getStopWheelchairBoarding(Integer.parseInt(attributes[11]));
        }
        else
        {
            this.wheelchair_boarding = StopWheelchairBoarding.NO_INFO;
        }

    }

    @Override
    public ArrayList<Object> getColumnTypes() {
        ArrayList<Object> columnTypes = new ArrayList<>();
        columnTypes.add(this.stop_id);
        columnTypes.add(this.stop_code);
        columnTypes.add(this.stop_name);
        columnTypes.add(this.stop_desc);
        columnTypes.add(this.stop_lat);
        columnTypes.add(this.stop_lon);
        columnTypes.add(this.zone_id);
        columnTypes.add(this.stop_url);
        columnTypes.add(this.location_type);
        columnTypes.add(this.parent_station);
        columnTypes.add(this.stop_timezone);
        columnTypes.add(this.wheelchair_boarding);

        return columnTypes;
    }

    @Override
    public ArrayList<Object> getAttributesForExportGTFS()
    {
        ArrayList<Object> attributesForExport = this.getColumnTypes();
        attributesForExport.set(8, StopLocationType.getValueForExport(this.location_type));
        attributesForExport.set(11, StopWheelchairBoarding.getValueForExport(this.wheelchair_boarding));
        return attributesForExport;
    }

    public Object[] getDetailedAttributes()
    {
        return new Object[]{this.stop_id, this.stop_name, this.stop_lat, this.stop_lon};
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
