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
                && ((TimePicker)this.addFormObjects.get(1)).getTime() != null
                && ((TimePicker) this.addFormObjects.get(2)).getTime() != null
                && !((JTextField)this.addFormObjects.get(3)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(4)).getText().equals("")
                && !this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText() + ((JTextField)this.addFormObjects.get(3)).getText());
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
                newStopTime.setShape_dist_traveled(Float.parseFloat(((JTextField) this.addFormObjects.get(8)).getText()));
            }
            newStopTime.setTimepoint((TimePoint) ((JComboBox<?>) this.addFormObjects.get(9)).getSelectedItem());
            this.hashtable.put(newStopTime.getKey(), newStopTime);
            this.keys.add(newStopTime.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.STOP_TIME);
        }

    }

    @Override
    void updateTable()
    {
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.STOP_TIME);
    }

}
