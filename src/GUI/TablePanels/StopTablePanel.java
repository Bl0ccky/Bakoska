package GUI.TablePanels;

import Enums.Stop.StopLocationType;
import Enums.Stop.StopWheelchairBoarding;
import GUI.MainFrame;
import TextFiles.IObject;
import TextFiles.ObjectType;
import TextFiles.Stop;

import javax.swing.*;
import java.util.Hashtable;

public class StopTablePanel extends TablePanel{
    public StopTablePanel(JPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType) {
        super(panel, mainFrame, hashtable, objectType);
    }

    @Override
    boolean checkAddInputs()
    {
        return !((JTextField)this.addFormObjects.get(0)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(2)).getText().equals("")
                && !((JTextField) this.addFormObjects.get(4)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(5)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(6)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(9)).getText().equals("")
                && !this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText());
    }

    @Override
    void addNewObject()
    {
        if(this.checkAddInputs())
        {
            Stop newStop = new Stop();
            newStop.setStop_id(((JTextField) this.addFormObjects.get(0)).getText());
            newStop.setStop_code(((JTextField) this.addFormObjects.get(1)).getText());
            newStop.setStop_name(((JTextField) this.addFormObjects.get(2)).getText());
            newStop.setStop_desc(((JTextField) this.addFormObjects.get(3)).getText());
            newStop.setStop_lat(Double.parseDouble(((JTextField) this.addFormObjects.get(4)).getText()));
            newStop.setStop_lon(Double.parseDouble(((JTextField) this.addFormObjects.get(5)).getText()));
            newStop.setZone_id(((JTextField) this.addFormObjects.get(6)).getText());
            newStop.setStop_url(((JTextField) this.addFormObjects.get(7)).getText());
            newStop.setLocation_type((StopLocationType) ((JComboBox<?>) this.addFormObjects.get(8)).getSelectedItem());
            newStop.setParent_station(((JTextField) this.addFormObjects.get(9)).getText());
            newStop.setStop_timezone(((JTextField) this.addFormObjects.get(10)).getText());
            newStop.setWheelchair_boarding((StopWheelchairBoarding) ((JComboBox<?>) this.addFormObjects.get(11)).getSelectedItem());
            this.hashtable.put(newStop.getKey(), newStop);
            this.keys.add(newStop.getKey());
            this.myTableItemModel.fireTableDataChanged();
        }

    }

}
