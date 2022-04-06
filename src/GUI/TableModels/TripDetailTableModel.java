package GUI.TableModels;

import Enums.Stop.StopLocationType;
import Enums.Stop.StopWheelchairBoarding;
import GTFSFiles.IGTFSObject;
import GTFSFiles.TripDetail.SpecialStop;
import GUI.MainFrame;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class TripDetailTableModel extends MyTableItemModel{

    public TripDetailTableModel(MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(mainFrame, hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object value = "";
        SpecialStop specialStop = (SpecialStop)this.hashtable.get(this.keys.get(rowIndex));
        switch (columnIndex)
        {
            case 0 -> value = specialStop.getStop_id();
            case 1 -> value = specialStop.getStop_code();
            case 2 -> value = specialStop.getStop_name();
            case 3 -> value = specialStop.getStop_desc();
            case 4 -> value = specialStop.getArrival_time();
            case 5 -> value = specialStop.getDeparture_time();
            case 6 -> value = specialStop.getStop_sequence();
            case 7 -> value = specialStop.getStop_lat();
            case 8 -> value = specialStop.getStop_lon();
            case 9 -> value = specialStop.getLocation_type();
            case 10 -> value = specialStop.getStop_timezone();
            case 11 -> value = specialStop.getWheelchair_boarding();
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
