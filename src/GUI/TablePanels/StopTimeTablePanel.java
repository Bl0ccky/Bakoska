package GUI.TablePanels;

import Enums.StopTime.DropOffType;
import Enums.StopTime.PickupType;
import Enums.StopTime.TimePoint;
import GUI.AdminPanel;
import GUI.MainFrame;
import GTFSFiles.IGTFSObject;
import GTFSFiles.StopTime;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.util.Hashtable;

public class StopTimeTablePanel extends TablePanel {
    public StopTimeTablePanel(AdminPanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSFiles.GTFSObjectType gtfsObjectType) {
        super(panel, mainFrame, hashtable, gtfsObjectType);
        for (int i = 0; i < 5; i++)
        {
            this.addLabels[i].setText(this.addLabels[i].getText()+" *");
        }
    }

    @Override
    boolean checkRemoveAction(int keyIndex)
    {
        return true;
    }

    @Override
    boolean checkAddInputs()
    {
        if (((JTextField) this.addFormObjects.get(0)).getText().equals("")) {
            this.setRedBorder(addFormObjects.get(0));
        } else {
            this.setDefaultBorder(this.addFormObjects.get(0));
        }

        for (int i = 1; i < 3; i++)
        {
            if(((TimePicker)this.addFormObjects.get(i)).getTime() == null)
            {
                this.setRedBorder(this.addFormObjects.get(i));
            }
            else
            {
               this.addFormObjects.get(i).setBorder(null);
            }
        }

        for (int i = 3; i < 5; i++)
        {
            if(((JTextField)this.addFormObjects.get(i)).getText().equals(""))
            {
                this.setRedBorder(this.addFormObjects.get(i));
            }
            else
            {
                this.setDefaultBorder(this.addFormObjects.get(i));
            }
        }

        boolean shapeDistTraveled_isNumeric = true;
        if(!((JTextField) this.addFormObjects.get(8)).getText().equals(""))
        {
            shapeDistTraveled_isNumeric = false;
            if(!this.isNumeric(((JTextField) this.addFormObjects.get(8)).getText()))
            {
                this.setRedBorder(this.addFormObjects.get(8));
                JOptionPane.showMessageDialog(null, "Enter a numeric value!", "Incorrect input", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                this.setDefaultBorder(this.addFormObjects.get(8));
                shapeDistTraveled_isNumeric = true;
            }
        }


        if (((JTextField) this.addFormObjects.get(0)).getText().equals("")
                || ((TimePicker)this.addFormObjects.get(1)).getTime() == null
                || ((TimePicker) this.addFormObjects.get(2)).getTime() == null
                || ((JTextField)this.addFormObjects.get(3)).getText().equals("")
                || ((JTextField)this.addFormObjects.get(4)).getText().equals("")){
            JOptionPane.showMessageDialog(null, "Some required fields are empty!", "Empty required fields", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText() + "-" + ((JTextField)this.addFormObjects.get(3)).getText()))
            {
                this.setRedBorder(this.addFormObjects.get(0));
                this.setRedBorder(this.addFormObjects.get(3));
                JOptionPane.showMessageDialog(null, "StopTime with those values is already exists!", "Existing StopTime", JOptionPane.ERROR_MESSAGE);
            }
            else if(!this.isNumeric(((JTextField)this.addFormObjects.get(4)).getText()))
            {
                this.setRedBorder(this.addFormObjects.get(4));
                JOptionPane.showMessageDialog(null, "Enter a numeric value!", "Incorrect input", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                this.setDefaultBorder(this.addFormObjects.get(0));
                this.setDefaultBorder(this.addFormObjects.get(3));
                this.setDefaultBorder(this.addFormObjects.get(4));
            }
        }

        return !((JTextField)this.addFormObjects.get(0)).getText().equals("")
                && ((TimePicker)this.addFormObjects.get(1)).getTime() != null
                && ((TimePicker) this.addFormObjects.get(2)).getTime() != null
                && !((JTextField)this.addFormObjects.get(3)).getText().equals("")
                && this.isNumeric(((JTextField)this.addFormObjects.get(4)).getText())
                && shapeDistTraveled_isNumeric
                && !this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText() + "-" + ((JTextField)this.addFormObjects.get(3)).getText());
    }

    @Override
    void addNewObject()
    {
        if(this.checkAddInputs())
        {
            StopTime newStopTime = new StopTime();
            newStopTime.setTrip_id(((JTextField) this.addFormObjects.get(0)).getText());
            newStopTime.setArrival_time(((TimePicker) this.addFormObjects.get(1)).getTime());
            newStopTime.setDeparture_time(((TimePicker) this.addFormObjects.get(2)).getTime());
            newStopTime.setStop_id(((JTextField) this.addFormObjects.get(3)).getText());
            newStopTime.setStop_sequence(Integer.parseInt(((JTextField) this.addFormObjects.get(4)).getText()));
            newStopTime.setStop_headsign(((JTextField) this.addFormObjects.get(5)).getText());
            newStopTime.setPickup_type((PickupType) ((JComboBox<?>) this.addFormObjects.get(6)).getSelectedItem());
            newStopTime.setDrop_off_type((DropOffType) ((JComboBox<?>) this.addFormObjects.get(7)).getSelectedItem());
            if(!((JTextField) this.addFormObjects.get(8)).getText().equals(""))
            {
                if(this.isNumeric(((JTextField) this.addFormObjects.get(8)).getText()))
                {
                    newStopTime.setShape_dist_traveled(Float.parseFloat(((JTextField) this.addFormObjects.get(8)).getText()));
                }
            }
            newStopTime.setTimepoint((TimePoint) ((JComboBox<?>) this.addFormObjects.get(9)).getSelectedItem());
            this.hashtable.put(newStopTime.getKey(), newStopTime);
            this.keys.add(newStopTime.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.STOP_TIME);

            this.setDefaultBorder(this.addFormObjects.get(0));

            for (int i = 1; i < 3; i++)
            {
                this.addFormObjects.get(i).setBorder(null);
            }

            for (int i = 3; i < 5; i++)
            {
                this.setDefaultBorder(this.addFormObjects.get(i));
            }

            this.setDefaultBorder(this.addFormObjects.get(8));
            JOptionPane.showMessageDialog(null, "New StopTime has been successfully created", "New StopTime created", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    @Override
    void updateTable()
    {
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.STOP_TIME);
    }

}
