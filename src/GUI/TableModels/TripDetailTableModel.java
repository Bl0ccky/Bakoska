package GUI.TableModels;

import Enums.Stop.StopLocationType;
import Enums.Stop.StopWheelchairBoarding;
import TextFiles.IGTFSObject;
import TextFiles.Stop;
import TextFiles.StopTime;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class TripDetailTableModel extends MyTableItemModel{
    private final Hashtable<String, IGTFSObject> stopTimeHashTable;
    private final ArrayList<String> stopTimeKeys;
    public TripDetailTableModel(Hashtable<String, IGTFSObject> stopHashtable, Hashtable<String, IGTFSObject> stopTimeHashTable, ArrayList<String> keys, String[] columnNames) {
        super(stopHashtable, keys, columnNames);
        this.stopTimeHashTable = stopTimeHashTable;
        this.stopTimeKeys = new ArrayList<>(stopTimeHashTable.keySet());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object value = "";
        Stop stop = (Stop)super.hashtable.get(super.keys.get(rowIndex));
        StopTime stopTime = (StopTime)this.stopTimeHashTable.get(this.stopTimeKeys.get(rowIndex));
        switch (columnIndex)
        {
            case 0 -> value = stop.getStop_id();
            case 1 -> value = stop.getStop_code();
            case 2 -> value = stop.getStop_name();
            case 3 -> value = stop.getStop_desc();
            case 4 -> value = stopTime.getArrival_time();
            case 5 -> value = stopTime.getDeparture_time();
            case 6 -> value = stopTime.getStop_sequence();
            case 7 -> value = stop.getStop_lat();
            case 8 -> value = stop.getStop_lon();
            case 9 -> value = stop.getLocation_type();
            case 10 -> value = stop.getStop_timezone();
            case 11 -> value = stop.getWheelchair_boarding();
        }

        return value;
    }

    @Override
    public Class<?> getColumnClass(int column)
    {
        return switch (column) {
            case 4, 5 -> LocalTime.class;
            case 6 -> Integer.class;
            case 7, 8 -> Double.class;
            case 9 -> StopLocationType.class;
            case 11 -> StopWheelchairBoarding.class;
            default -> String.class;
        };
    }
}
