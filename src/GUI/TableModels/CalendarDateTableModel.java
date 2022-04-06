package GUI.TableModels;

import Enums.CalendarDate.ExceptionType;
import GTFSFiles.CalendarDate;
import GTFSFiles.GTFSObjectType;
import GTFSFiles.IGTFSObject;
import GUI.MainFrame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class CalendarDateTableModel extends MyTableItemModel {
    public CalendarDateTableModel(MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(mainFrame, hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        CalendarDate calendarDate = (CalendarDate) super.hashtable.get(super.keys.get(rowIndex));
        switch (columnIndex) {
            case 0 -> value = calendarDate.getService_id();
            case 1 -> value = calendarDate.getDate();
            case 2 -> value = calendarDate.getException_type();
        }

        return value;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return switch (column) {
            case 1 -> LocalDate.class;
            case 2 -> ExceptionType.class;
            default -> String.class;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        CalendarDate calendarDate = (CalendarDate) this.hashtable.get(this.keys.get(rowIndex));
        this.hashtable.remove(this.keys.get(rowIndex));
        switch (columnIndex) {
            case 0 -> calendarDate.setService_id((String) aValue);
            case 1 -> calendarDate.setDate((LocalDate) aValue);
            case 2 -> calendarDate.setException_type((ExceptionType)aValue);
        }

        this.hashtable.put(calendarDate.getKey(), calendarDate);
        this.keys.set(rowIndex, calendarDate.getKey());
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSObjectType.CALENDAR_DATE);
        this.fireTableDataChanged();

    }
}
