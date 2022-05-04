package GUI.TableModels;

import Enums.Trip.TripDirectionID;
import Enums.Trip.TripWheelchairAccessible;
import GTFSFiles.*;
import GUI.MainFrame;

import java.util.ArrayList;
import java.util.Hashtable;

public class TripTableModel extends MyTableItemModel{

    public TripTableModel(MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(mainFrame, hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        Trip trip = (Trip)super.hashtable.get(super.keys.get(rowIndex));
        switch (columnIndex)
        {
            case 0 -> value = trip.getTrip_id();
            case 1 -> value = trip.getRoute_id();
            case 2 -> value = trip.getService_id();
            case 3 -> value = trip.getTrip_headsign();
            case 4 -> value = trip.getTrip_short_name();
            case 5 -> value = trip.getDirection_id();
            case 6 -> value = trip.getBlock_id();
            case 7 -> value = trip.getShape_id();
            case 8 -> value = trip.getWheelchair_accessible();
        }

        return value;
    }

    @Override
    public Class<?> getColumnClass(int column)
    {
        return switch (column) {
            case 5 -> TripDirectionID.class;
            case 8 -> TripWheelchairAccessible.class;
            default -> String.class;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Trip trip = (Trip)this.hashtable.get(this.keys.get(rowIndex));
        String findingIDValue = trip.getRoute_id();
        if(!(this.mainFrame.getAdminPanel().getTablePanel(GTFSObjectType.STOP_TIME).tableContainsValueAt(findingIDValue, 0) && columnIndex == 0) &&
                !(this.hashtable.containsKey(String.valueOf(aValue)) && columnIndex == 0))
        {
            this.hashtable.remove(this.keys.get(rowIndex));
            switch (columnIndex)
            {
                case 0 -> trip.setTrip_id((String) aValue);
                case 1 -> trip.setRoute_id((String) aValue);
                case 2 -> trip.setService_id((String) aValue);
                case 3 -> trip.setTrip_headsign((String) aValue);
                case 4 -> trip.setTrip_short_name((String) aValue);
                case 5 -> trip.setDirection_id((TripDirectionID) aValue);
                case 6 -> trip.setBlock_id((String) aValue);
                case 7 -> trip.setShape_id((String) aValue);
                case 8 -> trip.setWheelchair_accessible((TripWheelchairAccessible) aValue);
            }

            this.hashtable.put(trip.getKey(), trip);
            this.keys.set(rowIndex, trip.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSObjectType.TRIP);
            this.fireTableDataChanged();
        }

    }
}
