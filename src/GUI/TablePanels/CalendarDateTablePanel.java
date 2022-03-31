package GUI.TablePanels;

import Enums.CalendarDate.ExceptionType;
import GUI.AdminPanel;
import GUI.MainFrame;
import TextFiles.CalendarDate;
import TextFiles.IObject;
import TextFiles.ObjectType;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.util.Hashtable;

public class CalendarDateTablePanel extends TablePanel{
    public CalendarDateTablePanel(AdminPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType) {
        super(panel, mainFrame, hashtable, objectType);
    }

    @Override
    boolean checkRemoveAction(int keyIndex)
    {
       return true;
    }

    @Override
    boolean checkAddInputs()
    {
        return !((JTextField)this.addFormObjects.get(0)).getText().equals("")
                && ((DatePicker) this.addFormObjects.get(1)).getDate() != null
                && ((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem() != null
                && !this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText()
                + ((DatePicker) this.addFormObjects.get(1)).getDate()
                + ((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem());
    }

    @Override
    void addNewObject()
    {
        if(this.checkAddInputs())
        {
            CalendarDate newCalendarDate = new CalendarDate();
            newCalendarDate.setService_id(((JTextField) this.addFormObjects.get(0)).getText());
            newCalendarDate.setDate(((DatePicker) this.addFormObjects.get(1)).getDate());
            newCalendarDate.setException_type((ExceptionType) ((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem());
            this.hashtable.put(newCalendarDate.getKey(), newCalendarDate);
            this.keys.add(newCalendarDate.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, ObjectType.CALENDAR_DATE);
            this.myTableItemModel.fireTableDataChanged();
        }

    }

    @Override
    void updateTable()
    {
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, ObjectType.CALENDAR_DATE);
    }

}
