package GUI.TablePanels;

import Enums.CalendarDate.ExceptionType;
import GUI.AdminPanel;
import GUI.MainFrame;
import GTFSFiles.CalendarDate;
import GTFSFiles.IGTFSObject;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class CalendarDateTablePanel extends TablePanel {
    public CalendarDateTablePanel(AdminPanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSFiles.GTFSObjectType gtfsObjectType) {
        super(panel, mainFrame, hashtable, gtfsObjectType);
        for (int i = 0; i < 3; i++)
        {
            this.addLabels[i].setText(this.addLabels[i].getText()+" *");
        }
    }

    @Override
    boolean checkRemoveAction(int keyIndex) {
        return true;
    }

    @Override
    boolean checkAddInputs() {


        if (((JTextField) this.addFormObjects.get(0)).getText().equals("")) {
            this.setRedBorder(addFormObjects.get(0));
        } else {
            this.setDefaultBorder(this.addFormObjects.get(0));
        }


        if (((DatePicker) this.addFormObjects.get(1)).getDate() == null) {
            this.setRedBorder(this.addFormObjects.get(1));
        } else {
            this.addFormObjects.get(1).setBorder(null);
        }

        if (((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem() == null) {
            this.setRedBorder(this.addFormObjects.get(2));
        } else {
            this.addFormObjects.get(2).setBorder(null);
        }


        if (((JTextField) this.addFormObjects.get(0)).getText().equals("")
                || ((DatePicker) this.addFormObjects.get(1)).getDate() == null
                || ((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Some required fields are empty!", "Empty required fields", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if (this.hashtable.containsKey(((JTextField) this.addFormObjects.get(0)).getText()
                    + "-" +((DatePicker) this.addFormObjects.get(1)).getDate()
                    + "-" +((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem())) {
                for (int i = 0; i < 3; i++)
                {
                    this.setRedBorder(this.addFormObjects.get(i));
                }
                JOptionPane.showMessageDialog(null, "CalendarDate with those values is already exists!", "Existing CalendarDate", JOptionPane.ERROR_MESSAGE);
            } else {
                this.setDefaultBorder(this.addFormObjects.get(0));
                this.addFormObjects.get(1).setBorder(null);
                this.addFormObjects.get(2).setBorder(null);
            }
        }


        return !((JTextField) this.addFormObjects.get(0)).getText().equals("")
                && ((DatePicker) this.addFormObjects.get(1)).getDate() != null
                && ((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem() != null
                && !this.hashtable.containsKey(((JTextField) this.addFormObjects.get(0)).getText()
                + "-" +((DatePicker) this.addFormObjects.get(1)).getDate()
                + "-" +((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem());
    }

    @Override
    void addNewObject() {
        if (this.checkAddInputs()) {
            CalendarDate newCalendarDate = new CalendarDate();
            newCalendarDate.setService_id(((JTextField) this.addFormObjects.get(0)).getText());
            newCalendarDate.setDate(((DatePicker) this.addFormObjects.get(1)).getDate());
            newCalendarDate.setException_type((ExceptionType) ((JComboBox<?>) this.addFormObjects.get(2)).getSelectedItem());
            this.hashtable.put(newCalendarDate.getKey(), newCalendarDate);
            this.keys.add(newCalendarDate.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.CALENDAR_DATE);

            this.setDefaultBorder(this.addFormObjects.get(0));
            this.addFormObjects.get(1).setBorder(null);
            this.addFormObjects.get(2).setBorder(null);
            JOptionPane.showMessageDialog(null, "New CalendarDate has been successfully created", "New CalendarDate created", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @Override
    void updateTable() {
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.CALENDAR_DATE);
    }

}
