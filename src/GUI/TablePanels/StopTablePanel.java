package GUI.TablePanels;

import Enums.Stop.StopLocationType;
import Enums.Stop.StopWheelchairBoarding;
import GUI.AdminPanel;
import GUI.MainFrame;
import GTFSFiles.IGTFSObject;
import GTFSFiles.Stop;

import javax.swing.*;
import java.util.Hashtable;

public class StopTablePanel extends TablePanel{
    public StopTablePanel(AdminPanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSFiles.GTFSObjectType gtfsObjectType) {
        super(panel, mainFrame, hashtable, gtfsObjectType);
        this.addLabels[0].setText(this.addLabels[0].getText()+" *");
        this.addLabels[2].setText(this.addLabels[2].getText()+" *");
        for (int i = 4; i < 6; i++)
        {
            this.addLabels[i].setText(this.addLabels[i].getText()+" *");
        }
    }

    @Override
    boolean checkRemoveAction(int keyIndex) {
        String findingIDValue = ((Stop)this.hashtable.get(this.keys.get(keyIndex))).getStop_id();
        return !this.contentPanel.getTablePanel(GTFSFiles.GTFSObjectType.STOP_TIME).tableContainsValueAt(findingIDValue, 3);
    }

    @Override
    boolean checkAddInputs()
    {
        if (((JTextField) this.addFormObjects.get(0)).getText().equals("")) {
            this.setRedBorder(addFormObjects.get(0));
        } else {
            this.setDefaultBorder(this.addFormObjects.get(0));
        }

        if (((JTextField) this.addFormObjects.get(2)).getText().equals("")) {
            this.setRedBorder(addFormObjects.get(2));
        } else {
            this.setDefaultBorder(this.addFormObjects.get(2));
        }

        for (int i = 4; i < 6; i++)
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

        if (((JTextField) this.addFormObjects.get(0)).getText().equals("")
                || ((JTextField)this.addFormObjects.get(2)).getText().equals("")
                || ((JTextField) this.addFormObjects.get(4)).getText().equals("")
                || ((JTextField)this.addFormObjects.get(5)).getText().equals("")){
            JOptionPane.showMessageDialog(null, "Some required fields are empty!", "Empty required fields", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText()))
            {
                this.setRedBorder(this.addFormObjects.get(0));
                JOptionPane.showMessageDialog(null, "Route with this id already exists!", "Existing route", JOptionPane.ERROR_MESSAGE);
            }
            else if(!this.isNumeric(((JTextField)this.addFormObjects.get(4)).getText()))
            {
                this.setRedBorder(this.addFormObjects.get(4));
                JOptionPane.showMessageDialog(null, "Enter a numeric value!", "Incorrect input", JOptionPane.ERROR_MESSAGE);
            }
            else if(!this.isNumeric(((JTextField)this.addFormObjects.get(5)).getText()))
            {
                this.setRedBorder(this.addFormObjects.get(5));
                JOptionPane.showMessageDialog(null, "Enter a numeric value!", "Incorrect input", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                this.setDefaultBorder(this.addFormObjects.get(0));
                this.setDefaultBorder(this.addFormObjects.get(4));
                this.setDefaultBorder(this.addFormObjects.get(5));
            }
        }

        return !((JTextField)this.addFormObjects.get(0)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(2)).getText().equals("")
                && this.isNumeric(((JTextField) this.addFormObjects.get(4)).getText())
                && this.isNumeric(((JTextField)this.addFormObjects.get(5)).getText())
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
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.STOP);

            this.setDefaultBorder(this.addFormObjects.get(0));
            this.setDefaultBorder(this.addFormObjects.get(2));
            for (int i = 4; i < 6; i++)
            {
                this.setDefaultBorder(this.addFormObjects.get(i));
            }
            JOptionPane.showMessageDialog(null, "New stop has been successfully created", "New stop created", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @Override
    void updateTable()
    {
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.STOP);
    }

}
