package GUI.TableModels;

import Enums.StopTime.DropOffType;
import Enums.StopTime.PickupType;
import Enums.StopTime.TimePoint;
import GTFSFiles.GTFSObjectType;
import GTFSFiles.IGTFSObject;
import GTFSFiles.StopTime;
import GUI.MainFrame;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class StopTimeTableModel extends MyTableItemModel {

    public StopTimeTableModel(MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(mainFrame, hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        StopTime stopTime = (StopTime) super.hashtable.get(super.keys.get(rowIndex));
        switch (columnIndex) {
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
    public Class<?> getColumnClass(int column) {
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        StopTime stopTime = (StopTime) this.hashtable.get(this.keys.get(rowIndex));
        this.hashtable.remove(this.keys.get(rowIndex));
        switch (columnIndex) {
            case 0 -> stopTime.setTrip_id((String) aValue);
            case 1 -> stopTime.setArrival_time((LocalTime) aValue);
            case 2 -> stopTime.setDeparture_time((LocalTime) aValue);
            case 3 -> stopTime.setStop_id((String) aValue);
            //TODO osetrenie na cisla
            case 4 -> stopTime.setStop_sequence(Integer.parseInt((String) aValue));
            case 5 -> stopTime.setStop_headsign((String) aValue);
            case 6 -> stopTime.setPickup_type((PickupType) aValue);
            case 7 -> stopTime.setDrop_off_type((DropOffType) aValue);
            //TODO osetrenie na cisla
            case 8 -> stopTime.setShape_dist_traveled(Float.parseFloat((String) aValue));
            case 9 -> stopTime.setTimepoint((TimePoint) aValue);
        }

        this.hashtable.put(stopTime.getKey(), stopTime);
        this.keys.set(rowIndex, stopTime.getKey());
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSObjectType.STOP_TIME);
        this.fireTableDataChanged();


    }
}
