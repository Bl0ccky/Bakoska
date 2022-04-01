package GUI.TableModels;

import Enums.CalendarDate.ExceptionType;
import TextFiles.CalendarDate;
import TextFiles.IGTFSObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class CalendarDateTableModel extends MyTableItemModel
{

    public CalendarDateTableModel(Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
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

    @Override
    public Class<?> getColumnClass(int column)
    {
        return switch (column) {
            case 1 -> LocalDate.class;
            case 2 -> ExceptionType.class;
            default -> String.class;
        };
    }
}
