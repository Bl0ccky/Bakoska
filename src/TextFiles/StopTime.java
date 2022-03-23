package TextFiles;

import Enums.StopTime.DropOffType;
import Enums.StopTime.PickupType;
import Enums.StopTime.TimePoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StopTime implements IObject
{
    private String trip_id;
    private Date arrival_time;
    private Date departure_time;
    private String stop_id;
    private int stop_sequence;
    private String stop_headsign;
    private PickupType pickup_type;
    private DropOffType drop_off_type;
    private float shape_dist_traveled;
    private TimePoint timepoint;

    public StopTime(){}

    @Override
    public void loadData(String[] attributes)
    {
        this.trip_id = attributes[0];

        if(attributes[1] != null && !attributes[1].equals(""))
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ObjectFactory.TimePattern);
            try {
                this.arrival_time = simpleDateFormat.parse(attributes[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(attributes[2] != null && !attributes[2].equals(""))
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ObjectFactory.TimePattern);
            try {
                this.departure_time = simpleDateFormat.parse(attributes[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        this.stop_id = attributes[3];

        if(attributes[4] != null && !attributes[4].equals(""))
        {
            this.stop_sequence = Integer.parseInt(attributes[4]);
        }

        this.stop_headsign = attributes[5];

        if(attributes[6] != null && !attributes[6].equals(""))
        {
            this.pickup_type = PickupType.getPickupType(Integer.parseInt(attributes[6]));
        }

        if(attributes[7] != null && !attributes[7].equals(""))
        {
            this.drop_off_type = DropOffType.getDropOffType(Integer.parseInt(attributes[7]));
        }

        if(attributes[8] != null && !attributes[8].equals(""))
        {
            this.shape_dist_traveled = Float.parseFloat(attributes[8]);
        }

        if(attributes[9] != null && !attributes[9].equals(""))
        {
            this.timepoint = TimePoint.getTimePoint(Integer.parseInt(attributes[9]));
        }

    }

    @Override
    public void getAllData()
    {
        System.out.printf(
                "%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\n",
                this.trip_id,
                this.arrival_time,
                this.departure_time,
                this.stop_id,
                this.stop_sequence,
                this.stop_headsign,
                this.pickup_type,
                this.drop_off_type,
                this.shape_dist_traveled,
                this.timepoint);
    }

    @Override
    public String getKey() {
        return this.trip_id + "-" + this.stop_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public Date getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Date arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Date getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Date departure_time) {
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
