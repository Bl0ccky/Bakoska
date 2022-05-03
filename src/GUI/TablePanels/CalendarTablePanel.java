package GUI.TablePanels;

import Enums.Calendar.DayServiceAvailability;
import GUI.AdminPanel;
import GUI.MainFrame;
import GTFSFiles.Calendar;
import GTFSFiles.IGTFSObject;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.util.Hashtable;


public class CalendarTablePanel extends TablePanel
{

    public CalendarTablePanel(AdminPanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSFiles.GTFSObjectType gtfsObjectType) {
        super(panel, mainFrame, hashtable, gtfsObjectType);
        for (int i = 0; i < 10; i++)
        {
            this.addLabels[i].setText(this.addLabels[i].getText()+" *");
        }
    }

    @Override
    boolean checkRemoveAction(int keyIndex) {
        String findingIDValue = ((Calendar)this.hashtable.get(this.keys.get(keyIndex))).getService_id();
        return !this.contentPanel.getTablePanel(GTFSFiles.GTFSObjectType.CALENDAR_DATE).tableContainsValueAt(findingIDValue, 0);
    }

    @Override
    boolean checkAddInputs()
    {
        if (((JTextField) this.addFormObjects.get(0)).getText().equals("")) {
            this.setRedBorder(addFormObjects.get(0));
        } else {
            this.setDefaultBorder(this.addFormObjects.get(0));
        }

        for (int i = 1; i < 8; i++)
        {
            if(((JComboBox<?>) this.addFormObjects.get(1)).getSelectedItem() == null)
            {
                this.setRedBorder(this.addFormObjects.get(i));
            }
            else
            {
                this.addFormObjects.get(i).setBorder(null);
            }
        }

        for (int i = 8; i < 10; i++)
        {
            if(((DatePicker)this.addFormObjects.get(i)).getDate() == null)
            {
                this.setRedBorder(this.addFormObjects.get(i));
            }
            else
            {
                this.addFormObjects.get(i).setBorder(null);
            }
        }

        if (((JTextField) this.addFormObjects.get(0)).getText().equals("")
                || ((JComboBox<?>) this.addFormObjects.get(1)).getSelectedItem() == null
                || ((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem() == null
                || ((JComboBox<?>) this.addFormObjects.get(3)).getSelectedItem() == null
                || ((JComboBox<?>) this.addFormObjects.get(4)).getSelectedItem() == null
                || ((JComboBox<?>) this.addFormObjects.get(5)).getSelectedItem() == null
                || ((JComboBox<?>) this.addFormObjects.get(6)).getSelectedItem() == null
                || ((JComboBox<?>) this.addFormObjects.get(7)).getSelectedItem() == null
                || ((DatePicker) this.addFormObjects.get(8)).getDate() == null
                || ((DatePicker) this.addFormObjects.get(9)).getDate() == null) {
            JOptionPane.showMessageDialog(null, "Some required fields are empty!", "Empty required fields", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText()))
            {
                this.setRedBorder(this.addFormObjects.get(0));
                JOptionPane.showMessageDialog(null, "Calendar with this id already exists!", "Existing calendar", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                this.setDefaultBorder(this.addFormObjects.get(0));
            }
        }

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
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.CALENDAR);

            this.setDefaultBorder(this.addFormObjects.get(0));
            for (int i = 1; i < 10; i++)
            {
                this.addFormObjects.get(i).setBorder(null);
            }
            JOptionPane.showMessageDialog(null, "New calendar has been successfully created", "New calendar created", JOptionPane.INFORMATION_MESSAGE);
        }


    }

    @Override
    void updateTable()
    {
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.CALENDAR);
    }


}
