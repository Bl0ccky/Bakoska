package TextFiles;

import Enums.StopTime.DropOffType;
import Enums.StopTime.PickupType;
import Enums.StopTime.TimePoint;

import java.sql.Time;

public class StopTime
{
    private String trip_id;
    private Time arrival_time;
    private Time departure_time;
    private String stop_id;
    private int stop_sequence;
    private String stop_headsign;
    private PickupType pickup_type;
    private DropOffType drop_off_type;
    private float shape_dist_traveled;
    private TimePoint timepoint;

    public StopTime(String trip_id, Time arrival_time, Time departure_time, String stop_id, int stop_sequence, String stop_headsign, PickupType pickup_type, DropOffType drop_off_type, float shape_dist_traveled, TimePoint timepoint)
    {
        this.trip_id = trip_id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.stop_id = stop_id;
        this.stop_sequence = stop_sequence;
        this.stop_headsign = stop_headsign;
        this.pickup_type = pickup_type;
        this.drop_off_type = drop_off_type;
        this.shape_dist_traveled = shape_dist_traveled;
        this.timepoint = timepoint;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public Time getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Time arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public int getStop_sequence() {
        return stop_sequence;
    }

    public void setStop_sequence(int stop_sequence) {
        this.stop_sequence = stop_sequence;
    }

    public String getStop_headsign() {
        return stop_headsign;
    }

    public void setStop_headsign(String stop_headsign) {
        this.stop_headsign = stop_headsign;
    }

    public PickupType getPickup_type() {
        return pickup_type;
    }

    public void setPickup_type(PickupType pickup_type) {
        this.pickup_type = pickup_type;
    }

    public DropOffType getDrop_off_type() {
        return drop_off_type;
    }

    public void setDrop_off_type(DropOffType drop_off_type) {
        this.drop_off_type = drop_off_type;
    }

    public float getShape_dist_traveled() {
        return shape_dist_traveled;
    }

    public void setShape_dist_traveled(float shape_dist_traveled) {
        this.shape_dist_traveled = shape_dist_traveled;
    }

    public TimePoint getTimepoint() {
        return timepoint;
    }

    public void setTimepoint(TimePoint timepoint) {
        this.timepoint = timepoint;
    }
}
