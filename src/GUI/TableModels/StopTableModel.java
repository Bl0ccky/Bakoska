package GUI.TableModels;

import TextFiles.IObject;
import TextFiles.Stop;

import java.util.ArrayList;
import java.util.Hashtable;

public class StopTableModel extends MyTableItemModel{
    public StopTableModel(Hashtable<String, IObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        Stop stop = (Stop)super.hashtable.get(super.keys.get(rowIndex));
        switch (columnIndex)
        {
            case 0 -> value = stop.getStop_id();
            case 1 -> value = stop.getStop_code();
            case 2 -> value = stop.getStop_name();
            case 3 -> value = stop.getStop_desc();
            case 4 -> value = stop.getStop_lat();
            case 5 -> value = stop.getStop_lon();
            case 6 -> value = stop.getZone_id();
            case 7 -> value = stop.getStop_url();
            case 8 -> value = stop.getLocation_type();
            case 9 -> value = stop.getParent_station();
            case 10 -> value = stop.getStop_timezone();
            case 11 -> value = stop.getWheelchair_boarding();
        }

        return value;
    }
}