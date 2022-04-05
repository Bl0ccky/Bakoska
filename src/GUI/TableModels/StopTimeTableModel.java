package GUI.TableModels;

import Enums.StopTime.DropOffType;
import Enums.StopTime.PickupType;
import Enums.StopTime.TimePoint;
import GTFSFiles.IGTFSObject;
import GTFSFiles.StopTime;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class StopTimeTableModel extends MyTableItemModel{
    public StopTimeTableModel(Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        StopTime stopTime = (StopTime)super.hashtable.get(super.keys.get(rowIndex));
        switch (columnIndex)
        {
            case 0 -> value = stopTime.getTrip_id();
            case 1 -> value = stopTime.getArrival_time();
            case 2 -> value = stopTime.getDeparture_time();
            case 3 -> value = stopTime.getStop_id();
            case 4 -> value = stopTime.getStop_sequence();
            case 5 -> value = stopTime.getStop_headsign();
            case 6 -> value = stopTime.getPickup_type();
            case 7 -> value = stopTime.getDrop_off_type();
            case 8 -> value = stopTime.getShape_dist_traveled();
            case 9 -> value = stopTime.getTimepoint();
        }

        return value;
    }

    @Override
    public Class<?> getColumnClass(int column)
    {
        return switch (column) {
            case 1, 2 -> LocalTime.class;
            case 4 -> Integer.class;
            case 6 -> PickupType.class;
            case 7 -> DropOffType.class;
            case 8 -> Float.class;
            case 9 -> TimePoint.class;
            default -> String.class;
        };
    }
}
