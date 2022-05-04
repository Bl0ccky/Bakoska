package GUI.TableModels;

import Enums.Route.RouteType;
import GTFSFiles.GTFSObjectType;
import GTFSFiles.IGTFSObject;
import GTFSFiles.Route;
import GUI.MainFrame;

import java.util.ArrayList;
import java.util.Hashtable;

public class RouteTableModel extends MyTableItemModel{

    public RouteTableModel(MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(mainFrame, hashtable, keys, columnNames);
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Route route = (Route)this.hashtable.get(this.keys.get(rowIndex));
        String findingIDValue = route.getRoute_id();
        if(!(this.mainFrame.getAdminPanel().getTablePanel(GTFSObjectType.TRIP).tableContainsValueAt(findingIDValue, 1) && columnIndex == 0) &&
                !(this.hashtable.containsKey(String.valueOf(aValue)) && columnIndex == 0))
        {
            this.hashtable.remove(this.keys.get(rowIndex));
            switch (columnIndex)
            {
                case 0 -> route.setRoute_id((String) aValue);
                case 1 -> route.setAgency_id((String) aValue);
                case 2 -> route.setRoute_short_name((String) aValue);
                case 3 -> route.setRoute_long_name((String) aValue);
                case 4 -> route.setRoute_desc((String) aValue);
                case 5 -> route.setRoute_type((RouteType) aValue);
                case 6 -> route.setRoute_url((String) aValue);
                case 7 -> route.setRoute_color((String) aValue);
                case 8 -> route.setRoute_text_color((String) aValue);
            }

            this.hashtable.put(route.getKey(), route);
            this.keys.set(rowIndex, route.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSObjectType.ROUTE);
            this.fireTableDataChanged();
        }

    }
}
