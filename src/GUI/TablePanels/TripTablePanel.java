package GUI.TablePanels;

import Enums.Trip.TripDirectionID;
import Enums.Trip.TripWheelchairAccessible;
import GUI.AdminPanel;
import GUI.MainFrame;
import TextFiles.IObject;
import TextFiles.ObjectType;
import TextFiles.Trip;


import javax.swing.*;
import java.util.Hashtable;

public class TripTablePanel extends TablePanel
{

    public TripTablePanel(AdminPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType) {
        super(panel, mainFrame, hashtable, objectType);
    }

    @Override
    boolean checkRemoveAction(int keyIndex) {
        String findingIDValue = ((Trip)this.hashtable.get(this.keys.get(keyIndex))).getTrip_id();
        return !this.contentPanel.getTablePanel(ObjectType.STOP_TIME).tableContainsValueAt(findingIDValue, 0);
    }

    @Override
    boolean checkAddInputs()
    {
        return !((JTextField)this.addFormObjects.get(0)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(1)).getText().equals("")
                && !((JTextField) this.addFormObjects.get(2)).getText().equals("")
                && !this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText());
    }

    @Override
    void addNewObject()
    {
        if(this.checkAddInputs())
        {
            Trip newTrip = new Trip();
            newTrip.setTrip_id(((JTextField) this.addFormObjects.get(0)).getText());
            newTrip.setRoute_id(((JTextField) this.addFormObjects.get(1)).getText());
            newTrip.setService_id(((JTextField) this.addFormObjects.get(2)).getText());
            newTrip.setTrip_headsign(((JTextField) this.addFormObjects.get(3)).getText());
            newTrip.setTrip_short_name(((JTextField) this.addFormObjects.get(4)).getText());
            newTrip.setDirection_id((TripDirectionID) ((JComboBox<?>) this.addFormObjects.get(5)).getSelectedItem());
            newTrip.setBlock_id(((JTextField) this.addFormObjects.get(6)).getText());
            newTrip.setShape_id(((JTextField) this.addFormObjects.get(7)).getText());
            newTrip.setWheelchair_accessible((TripWheelchairAccessible) ((JComboBox<?>) this.addFormObjects.get(8)).getSelectedItem());
            this.hashtable.put(newTrip.getKey(), newTrip);
            this.keys.add(newTrip.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, ObjectType.TRIP);
            this.myTableItemModel.fireTableDataChanged();
        }

    }

    @Override
    void updateTable()
    {
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, ObjectType.TRIP);
    }

}
