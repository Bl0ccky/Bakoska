package GUI.TablePanels;

import Enums.Calendar.DayServiceAvailability;
import GUI.MainFrame;
import TextFiles.Calendar;
import TextFiles.IObject;
import TextFiles.ObjectType;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.util.Hashtable;


public class CalendarTablePanel extends TablePanel
{

    public CalendarTablePanel(JPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType) {
        super(panel, mainFrame, hashtable, objectType);
    }

    @Override
    boolean checkAddInputs()
    {
        return !((JTextField)this.addFormObjects.get(0)).getText().equals("")
                && ((JComboBox<?>) this.addFormObjects.get(1)).getSelectedItem() != null
                && ((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem() != null
                && ((JComboBox<?>) this.addFormObjects.get(3)).getSelectedItem() != null
                && ((JComboBox<?>) this.addFormObjects.get(4)).getSelectedItem() != null
                && ((JComboBox<?>) this.addFormObjects.get(5)).getSelectedItem() != null
                && ((JComboBox<?>) this.addFormObjects.get(6)).getSelectedItem() != null
                && ((JComboBox<?>) this.addFormObjects.get(7)).getSelectedItem() != null
                && ((DatePicker) this.addFormObjects.get(8)).getDate() != null
                && ((DatePicker) this.addFormObjects.get(9)).getDate() != null
                && !this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText());
    }

    @Override
    void addNewObject()
    {
        if(this.checkAddInputs())
        {
            Calendar newCalendar = new Calendar();
            newCalendar.setService_id(((JTextField)this.addFormObjects.get(0)).getText());
            newCalendar.setMonday((DayServiceAvailability)((JComboBox<?>)this.addFormObjects.get(1)).getSelectedItem());
            newCalendar.setTuesday((DayServiceAvailability)((JComboBox<?>)this.addFormObjects.get(2)).getSelectedItem());
            newCalendar.setWednesday((DayServiceAvailability)((JComboBox<?>)this.addFormObjects.get(3)).getSelectedItem());
            newCalendar.setThursday((DayServiceAvailability)((JComboBox<?>)this.addFormObjects.get(4)).getSelectedItem());
            newCalendar.setFriday((DayServiceAvailability)((JComboBox<?>)this.addFormObjects.get(5)).getSelectedItem());
            newCalendar.setSaturday((DayServiceAvailability)((JComboBox<?>)this.addFormObjects.get(6)).getSelectedItem());
            newCalendar.setSunday((DayServiceAvailability)((JComboBox<?>)this.addFormObjects.get(7)).getSelectedItem());
            newCalendar.setStart_date(((DatePicker)this.addFormObjects.get(8)).getDate());
            newCalendar.setEnd_date(((DatePicker)this.addFormObjects.get(9)).getDate());
            this.hashtable.put(newCalendar.getKey(), newCalendar);
            this.keys.add(newCalendar.getKey());
            this.myTableItemModel.fireTableDataChanged();
        }


    }


}
