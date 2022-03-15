package GUI.TableModels;

import TextFiles.IObject;
import TextFiles.StopTime;

import java.util.ArrayList;
import java.util.Hashtable;

public class StopTimeTableModel extends MyTableItemModel{
    public StopTimeTableModel(Hashtable<String, IObject> hashtable, ArrayList<String> keys, String[] columnNames) {
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
}
