package GUI.TableModels;

import Enums.StopTime.DropOffType;
import Enums.StopTime.PickupType;
import Enums.StopTime.TimePoint;
import Enums.Trip.TripDirectionID;
import Enums.Trip.TripWheelchairAccessible;
import TextFiles.IObject;
import TextFiles.Trip;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class TripTableModel extends MyTableItemModel{
    public TripTableModel(Hashtable<String, IObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(hashtable, keys, columnNames);
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
}
