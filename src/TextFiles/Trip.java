package TextFiles;

import Enums.Trip.TripDirectionID;
import Enums.Trip.TripWheelchairAccessible;

public class Trip implements IObject
{
    private String trip_id;
    private String route_id;
    private String service_id;
    private String trip_headsign;
    private String trip_short_name;
    private TripDirectionID direction_id;
    private String block_id;
    private String shape_id;
    private TripWheelchairAccessible wheelchair_accessible;

    public Trip(){}

    @Override
    public void loadData(String[] attributes)
    {
        this.trip_id = attributes[0];
        this.route_id = attributes[1];
        this.service_id = attributes[2];
        this.trip_headsign = attributes[3];
        this.trip_short_name = attributes[4];

        if(attributes[5] != null && !attributes[5].equals(""))
        {
            this.direction_id = TripDirectionID.getTripDirectionID(Integer.parseInt(attributes[5]));
        }

        this.block_id = attributes[6];
        this.shape_id = attributes[7];

        if(attributes[8] != null && !attributes[8].equals(""))
        {
            this.wheelchair_accessible = TripWheelchairAccessible.getTripWheelchairAccessible(Integer.parseInt(attributes[8]));
        }

    }

    @Override
    public void getAllData()
    {
        System.out.printf(
                "%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\n",
                this.trip_id,
                this.route_id,
                this.service_id,
                this.trip_headsign,
                this.trip_short_name,
                this.direction_id,
                this.block_id,
                this.shape_id,
                this.wheelchair_accessible);

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


}
