package GUI.TablePanels;

import GUI.AdminPanel;
import GUI.MainFrame;
import TextFiles.Agency;
import TextFiles.IGTFSObject;

import javax.swing.*;
import java.util.Hashtable;

public class AgencyTablePanel extends TablePanel
{
    public AgencyTablePanel(AdminPanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, TextFiles.GTFSObjectType gtfsObjectType)
    {
        super(panel, mainFrame, hashtable, gtfsObjectType);
    }

    @Override
    boolean checkRemoveAction(int keyIndex)
    {
        String findingIDValue = ((Agency)this.hashtable.get(this.keys.get(keyIndex))).getAgency_id();
        return !this.contentPanel.getTablePanel(TextFiles.GTFSObjectType.ROUTE).tableContainsValueAt(findingIDValue, 1);
    }

    @Override
    boolean checkAddInputs()
    {
        return !((JTextField)this.addFormObjects.get(0)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(1)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(2)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(3)).getText().equals("")
                && !this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText());
    }

    @Override
    void addNewObject()
    {
        if(this.checkAddInputs())
        {
            Agency newAgency = new Agency();
            newAgency.setAgency_id(((JTextField)this.addFormObjects.get(0)).getText());
            newAgency.setAgency_name(((JTextField)this.addFormObjects.get(1)).getText());
            newAgency.setAgency_url(((JTextField)this.addFormObjects.get(2)).getText());
            newAgency.setAgency_timezone(((JTextField)this.addFormObjects.get(3)).getText());
            newAgency.setAgency_lang(((JTextField)this.addFormObjects.get(4)).getText());
            newAgency.setAgency_phone(((JTextField)this.addFormObjects.get(5)).getText());
            newAgency.setAgency_fare_url(((JTextField)this.addFormObjects.get(6)).getText());
            this.hashtable.put(newAgency.getKey(), newAgency);
            this.keys.add(newAgency.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, TextFiles.GTFSObjectType.AGENCY);
        }
    }

    @Override
    void updateTable()
    {
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, TextFiles.GTFSObjectType.AGENCY);
    }
}
