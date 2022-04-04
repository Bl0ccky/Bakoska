package TextFiles;

import Enums.StopTime.DropOffType;
import Enums.StopTime.PickupType;
import Enums.StopTime.TimePoint;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;


public class StopTime implements IGTFSObject
{
    private String trip_id;
    private LocalTime arrival_time;
    private LocalTime departure_time;
    private String stop_id;
    private int stop_sequence;
    private String stop_headsign;
    private PickupType pickup_type;
    private DropOffType drop_off_type;
    private float shape_dist_traveled;
    private TimePoint timepoint;

    public StopTime() {
        this.trip_id = "";
        this.arrival_time = LocalTime.now();
        this.departure_time = LocalTime.now();
        this.stop_id = "";
        this.stop_sequence = 0;
        this.stop_headsign = "";
        this.pickup_type = PickupType.REGULARLY_SCHEDULED_PICKUP;
        this.drop_off_type = DropOffType.REGULARLY_SCHEDULED_DROP_OFF;
        this.shape_dist_traveled = 0;
        this.timepoint = TimePoint.EXACT_TIMES;
    }

    @Override
    public void loadData(String[] attributes)
    {
        this.trip_id = attributes[0];
        DateTimeFormatter timeFormat = new DateTimeFormatterBuilder().appendPattern(GTFSObjectFactory.TimePattern).toFormatter();
        if(attributes[1] != null && !attributes[1].equals(""))
        {
            String[] time = attributes[1].split(":",3);
            int hours = Integer.parseInt(time[0]);
            if(hours > 23)
            {
                hours -= 24;
                String newTime = hours+":"+time[1]+":"+time[2];
                this.arrival_time = LocalTime.parse(newTime, timeFormat);
            }
            else
            {
                this.arrival_time = LocalTime.parse(attributes[2], timeFormat);
            }

        }

        if(attributes[2] != null && !attributes[2].equals(""))
        {
            String[] time = attributes[2].split(":",3);
            int hours = Integer.parseInt(time[0]);
            if(hours > 23)
            {
                hours -= 24;
                String newTime = hours+":"+time[1]+":"+time[2];
                this.departure_time = LocalTime.parse(newTime, timeFormat);
            }
            else
            {
                this.departure_time = LocalTime.parse(attributes[2], timeFormat);
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
        else
        {
            this.pickup_type = PickupType.REGULARLY_SCHEDULED_PICKUP;
        }

        if(attributes[7] != null && !attributes[7].equals(""))
        {
            this.drop_off_type = DropOffType.getDropOffType(Integer.parseInt(attributes[7]));
        }
        else
        {
            this.drop_off_type = DropOffType.REGULARLY_SCHEDULED_DROP_OFF;
        }

        if(attributes[8] != null && !attributes[8].equals(""))
        {
            this.shape_dist_traveled = Float.parseFloat(attributes[8]);
        }

        if(attributes[9] != null && !attributes[9].equals(""))
        {
            this.timepoint = TimePoint.getTimePoint(Integer.parseInt(attributes[9]));
        }
        else
        {
            this.timepoint = TimePoint.EXACT_TIMES;
        }

    }


    @Override
    public ArrayList<Object> getColumnTypes()
    {
        ArrayList<Object> columnTypes = new ArrayList<>();
        columnTypes.add(this.trip_id);
        columnTypes.add(this.arrival_time);
        columnTypes.add(this.departure_time);
        columnTypes.add(this.stop_id);
        columnTypes.add(this.stop_sequence);
        columnTypes.add(this.stop_headsign);
        columnTypes.add(this.pickup_type);
        columnTypes.add(this.drop_off_type);
        columnTypes.add(this.shape_dist_traveled);
        columnTypes.add(this.timepoint);

        return columnTypes;
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
