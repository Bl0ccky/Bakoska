package GUI.TableModels;

import Enums.Route.RouteType;
import GTFSFiles.IGTFSObject;
import GTFSFiles.Route;

import java.util.ArrayList;
import java.util.Hashtable;

public class RouteTableModel extends MyTableItemModel{
    public RouteTableModel(Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        Route route = (Route)super.hashtable.get(super.keys.get(rowIndex));
        switch (columnIndex)
        {
            case 0 -> value = route.getRoute_id();
            case 1 -> value = route.getAgency_id();
            case 2 -> value = route.getRoute_short_name();
            case 3 -> value = route.getRoute_long_name();
            case 4 -> value = route.getRoute_desc();
            case 5 -> value = route.getRoute_type();
            case 6 -> value = route.getRoute_url();
            case 7 -> value = route.getRoute_color();
            case 8 -> value = route.getRoute_text_color();
        }

        return value;
    }

    @Override
    public Class<?> getColumnClass(int column)
    {
        if(column == 5)
        {
            return RouteType.class;
        }
        else
        {
            return String.class;
        }
    }
}
