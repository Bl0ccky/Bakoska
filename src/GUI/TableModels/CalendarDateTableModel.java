package GUI.TableModels;

import TextFiles.Calendar;
import TextFiles.IObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class CalendarDateTableModel extends MyTableItemModel
{

    public CalendarDateTableModel(Hashtable<String, IObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object value = "";
        Calendar calendar = (Calendar) super.hashtable.get(super.keys.get(rowIndex));
        switch (columnIndex)
        {
            case 0 -> value = calendar.getService_id();
            case 1 -> value = calendar.getMonday();
            case 2 -> value = calendar.getTuesday();
            case 3 -> value = calendar.getWednesday();
            case 4 -> value = calendar.getThursday();
            case 5 -> value = calendar.getFriday();
            case 6 -> value = calendar.getSaturday();
            case 7 -> value = calendar.getSunday();
            case 8 -> value = calendar.getStart_date();
            case 9 -> value = calendar.getEnd_date();
        }

        return value;
    }
}