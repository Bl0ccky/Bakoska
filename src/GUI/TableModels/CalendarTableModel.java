package GUI.TableModels;

import Enums.Calendar.DayServiceAvailability;
import GTFSFiles.Calendar;
import GTFSFiles.GTFSObjectType;
import GTFSFiles.IGTFSObject;
import GUI.MainFrame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class CalendarTableModel extends MyTableItemModel{

    public CalendarTableModel(MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(mainFrame, hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
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

    @Override
    public Class<?> getColumnClass(int column)
    {
        return switch (column) {
            case 1,2,3,4,5,6,7 -> DayServiceAvailability.class;
            case 8, 9 -> LocalDate.class;
            default -> String.class;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Calendar calendar = (Calendar)this.hashtable.get(this.keys.get(rowIndex));

        String findingIDValue = calendar.getService_id();
        if(!(this.mainFrame.getAdminPanel().getTablePanel(GTFSObjectType.CALENDAR_DATE).tableContainsValueAt(findingIDValue, 0) && columnIndex == 0))
        {
            this.hashtable.remove(this.keys.get(rowIndex));
            switch (columnIndex)
            {
                case 0 -> calendar.setService_id((String) aValue);
                case 1 -> calendar.setMonday((DayServiceAvailability) aValue);
                case 2 -> calendar.setTuesday((DayServiceAvailability) aValue);
                case 3 -> calendar.setWednesday((DayServiceAvailability) aValue);
                case 4 -> calendar.setThursday((DayServiceAvailability) aValue);
                case 5 -> calendar.setFriday((DayServiceAvailability) aValue);
                case 6 -> calendar.setSaturday((DayServiceAvailability) aValue);
                case 7 -> calendar.setSunday((DayServiceAvailability) aValue);
                case 8 -> calendar.setStart_date((LocalDate) aValue);
                case 9 -> calendar.setEnd_date((LocalDate) aValue);
            }

            this.hashtable.put(calendar.getKey(), calendar);
            this.keys.set(rowIndex, calendar.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSObjectType.CALENDAR);
            this.fireTableDataChanged();
        }
    }
}
