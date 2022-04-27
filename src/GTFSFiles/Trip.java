package GTFSFiles;

import Enums.Trip.TripDirectionID;
import Enums.Trip.TripWheelchairAccessible;
import GTFSFiles.TripDetail.SpecialStop;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class Trip implements IGTFSObject {
    private String trip_id;
    private String route_id;
    private String service_id;
    private String trip_headsign;
    private String trip_short_name;
    private TripDirectionID direction_id;
    private String block_id;
    private String shape_id;
    private TripWheelchairAccessible wheelchair_accessible;
    private Hashtable<String, IGTFSObject> specialStopHashTable;
    private Object[] detailedAttributes;

    public Trip() {
        this.trip_id = "";
        this.route_id = "";
        this.service_id = "";
        this.trip_headsign = "";
        this.trip_short_name = "";
        this.direction_id = TripDirectionID.NO_INFO;
        this.block_id = "";
        this.shape_id = "";
        this.wheelchair_accessible = TripWheelchairAccessible.NO_INFO;
    }

    @Override
    public void loadData(String[] attributes, String[] columnNames) {
        for (int i = 0; i < columnNames.length; i++) {
            switch (columnNames[i]) {
                case "trip_id":
                    this.trip_id = attributes[i];
                    break;
                case "route_id":
                    this.route_id = attributes[i];
                    break;
                case "service_id":
                    this.service_id = attributes[i];
                    break;
                case "trip_headsign":
                    this.trip_headsign = attributes[i];
                    break;
                case "trip_short_name":
                    this.trip_short_name = attributes[i];
                    break;
                case "direction_id":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.direction_id = TripDirectionID.getTripDirectionID(Integer.parseInt(attributes[i]));
                    }
                    break;
                case "block_id":
                    this.block_id = attributes[i];
                    break;
                case "shape_id":
                    this.shape_id = attributes[i];
                    break;
                case "wheelchair_accessible":
                    if (attributes[i] != null && !attributes[i].equals("")) {
                        this.wheelchair_accessible = TripWheelchairAccessible.getTripWheelchairAccessible(Integer.parseInt(attributes[i]));
                    } else {
                        this.wheelchair_accessible = TripWheelchairAccessible.NO_INFO;
                    }
                    break;
            }
        }
    }

    @Override
    public ArrayList<Object> getColumnTypes() {
        ArrayList<Object> columnTypes = new ArrayList<>();
        columnTypes.add(this.trip_id);
        columnTypes.add(this.route_id);
        columnTypes.add(this.service_id);
        columnTypes.add(this.trip_headsign);
        columnTypes.add(this.trip_short_name);
        columnTypes.add(this.direction_id);
        columnTypes.add(this.block_id);
        columnTypes.add(this.shape_id);
        columnTypes.add(this.wheelchair_accessible);

        return columnTypes;
    }

    public void createSpecialStopHashTable(Hashtable<String, IGTFSObject> stopTimeHashTable, Hashtable<String, IGTFSObject> stopHashTable) {
        this.specialStopHashTable = new Hashtable<>();

        ArrayList<String> stopTimeKeys = new ArrayList<>(stopTimeHashTable.keySet());
        ArrayList<String> stopKeys = new ArrayList<>(stopHashTable.keySet());

        for (String stopTimeKey : stopTimeKeys) {
            StopTime stopTime = (StopTime) stopTimeHashTable.get(stopTimeKey);
            String stopTime_tripId = stopTime.getTrip_id();

            if (stopTime_tripId.equals(this.trip_id)) {
                for (String stopKey : stopKeys) {
                    Stop stop = (Stop) stopHashTable.get(stopKey);
                    String stop_stopId = stop.getStop_id();
                    if (stop_stopId.equals(stopTime.getStop_id())) {
                        this.specialStopHashTable.put(stop.getKey(), new SpecialStop(
                                stop.getStop_id(),
                                stop.getStop_code(),
                                stop.getStop_name(),
                                stop.getStop_desc(),
                                stopTime.getArrival_time(),
                                stopTime.getDeparture_time(),
                                stopTime.getStop_sequence(),
                                stop.getStop_lat(),
                                stop.getStop_lon(),
                                stop.getLocation_type(),
                                stop.getStop_timezone(),
                                stop.getWheelchair_boarding()));
                    }

                }

            }

        }

    }

    public void createDetailedAttributes() {
        ArrayList<String> specialStopKeys = new ArrayList<>(this.specialStopHashTable.keySet());

        int minStopSequence = Integer.MAX_VALUE;
        String first_stop_id = "";
        String first_stop_name = "";
        LocalTime first_stop_arrival_time = null;

        int maxStopSequence = 0;
        String last_stop_id = "";
        String last_stop_name = "";
        LocalTime last_stop_departure_time = null;

        for (String specialStopKey : specialStopKeys) {
            if (((SpecialStop) this.specialStopHashTable.get(specialStopKey)).getStop_sequence() < minStopSequence) {
                minStopSequence = ((SpecialStop) this.specialStopHashTable.get(specialStopKey)).getStop_sequence();
                first_stop_id = specialStopKey;
                first_stop_name = ((SpecialStop) this.specialStopHashTable.get(specialStopKey)).getStop_name();
                first_stop_arrival_time = ((SpecialStop) this.specialStopHashTable.get(specialStopKey)).getArrival_time();
            }
            if (((SpecialStop) this.specialStopHashTable.get(specialStopKey)).getStop_sequence() > maxStopSequence) {
                maxStopSequence = ((SpecialStop) this.specialStopHashTable.get(specialStopKey)).getStop_sequence();
                last_stop_id = specialStopKey;
                last_stop_name = ((SpecialStop) this.specialStopHashTable.get(specialStopKey)).getStop_name();
                last_stop_departure_time = ((SpecialStop) this.specialStopHashTable.get(specialStopKey)).getDeparture_time();
            }
        }

        this.detailedAttributes = new Object[]{
                this.trip_id,
                this.trip_headsign,
                first_stop_arrival_time,
                last_stop_departure_time,
                first_stop_id,
                first_stop_name,
                last_stop_id,
                last_stop_name};
    }

    public Object[] getDetailedAttributes() {
        return this.detailedAttributes;
    }

    @Override
    public ArrayList<Object> getAttributesForExportGTFS() {
        ArrayList<Object> attributesForExport = this.getColumnTypes();
        attributesForExport.set(5, TripDirectionID.getValueForExport(this.direction_id));
        attributesForExport.set(8, TripWheelchairAccessible.getValueForExport(this.wheelchair_accessible));
        return attributesForExport;
    }

    @Override
    public String getKey() {
        return this.trip_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getTrip_headsign() {
        return trip_headsign;
    }

    public void setTrip_headsign(String trip_headsign) {
        this.trip_headsign = trip_headsign;
    }

    public String getTrip_short_name() {
        return trip_short_name;
    }

    public void setTrip_short_name(String trip_short_name) {
        this.trip_short_name = trip_short_name;
    }

    public TripDirectionID getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(TripDirectionID direction_id) {
        this.direction_id = direction_id;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public String getShape_id() {
        return shape_id;
    }

    public void setShape_id(String shape_id) {
        this.shape_id = shape_id;
    }

    public TripWheelchairAccessible getWheelchair_accessible() {
        return wheelchair_accessible;
    }

    public void setWheelchair_accessible(TripWheelchairAccessible wheelchair_accessible) {
        this.wheelchair_accessible = wheelchair_accessible;
    }

    public Hashtable<String, IGTFSObject> getSpecialStopHashTable() {
        return specialStopHashTable;
    }
}
