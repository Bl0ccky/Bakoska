package GUI.TableModels;

import TextFiles.CalendarDate;
import TextFiles.IObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class CalendarTableModel extends MyTableItemModel{
    public CalendarTableModel(Hashtable<String, IObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        CalendarDate calendarDate = (CalendarDate) super.hashtable.get(super.keys.get(rowIndex));
        switch (columnIndex)
        {
            case 0 -> value = calendarDate.getService_id();
            case 1 -> value = calendarDate.getDate();
            case 2 -> value = calendarDate.getException_type();
        }

        return value;
    }
}
