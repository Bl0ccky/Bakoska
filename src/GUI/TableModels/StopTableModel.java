package GUI.TableModels;

import Enums.Stop.StopLocationType;
import Enums.Stop.StopWheelchairBoarding;
import GTFSFiles.GTFSObjectType;
import GTFSFiles.IGTFSObject;
import GTFSFiles.Stop;
import GUI.MainFrame;
import java.util.ArrayList;
import java.util.Hashtable;

public class StopTableModel extends MyTableItemModel{

    public StopTableModel(MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(mainFrame, hashtable, keys, columnNames);
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

    @Override
    public Class<?> getColumnClass(int column)
    {
        return switch (column) {
            case 4, 5 -> Double.class;
            case 8 -> StopLocationType.class;
            case 11 -> StopWheelchairBoarding.class;
            default -> String.class;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Stop stop = (Stop)this.hashtable.get(this.keys.get(rowIndex));
        String findingIDValue = stop.getStop_id();
        if(!(this.mainFrame.getAdminPanel().getTablePanel(GTFSObjectType.STOP_TIME).tableContainsValueAt(findingIDValue, 3) && columnIndex == 0)&&
                !(this.hashtable.containsKey(String.valueOf(aValue)) && columnIndex == 0))
        {
            this.hashtable.remove(this.keys.get(rowIndex));
            switch (columnIndex)
            {
                case 0 -> stop.setStop_id((String) aValue);
                case 1 -> stop.setStop_code((String) aValue);
                case 2 -> stop.setStop_name((String) aValue);
                case 3 -> stop.setStop_desc((String) aValue);
                case 4 -> {
                    if(this.isNumeric(aValue))
                    {
                        stop.setStop_lat(Double.parseDouble((String) aValue));
                    }
                }
                case 5 -> {
                    if(this.isNumeric(aValue))
                    {
                        stop.setStop_lon(Double.parseDouble((String) aValue));
                    }
                }
                case 6 -> stop.setZone_id((String) aValue);
                case 7 -> stop.setStop_url((String) aValue);
                case 8 -> stop.setLocation_type((StopLocationType) aValue);
                case 9 -> stop.setParent_station((String) aValue);
                case 10 -> stop.setStop_timezone((String) aValue);
                case 11 -> stop.setWheelchair_boarding((StopWheelchairBoarding) aValue);
            }

            this.hashtable.put(stop.getKey(), stop);
            this.keys.set(rowIndex, stop.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSObjectType.STOP);
            this.fireTableDataChanged();
        }
    }


}
