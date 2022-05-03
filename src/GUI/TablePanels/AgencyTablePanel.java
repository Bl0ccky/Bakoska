package GUI.TablePanels;

import GUI.AdminPanel;
import GUI.MainFrame;
import GTFSFiles.Agency;
import GTFSFiles.IGTFSObject;

import javax.swing.*;
import java.util.Hashtable;

public class AgencyTablePanel extends TablePanel
{
    public AgencyTablePanel(AdminPanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSFiles.GTFSObjectType gtfsObjectType)
    {
        super(panel, mainFrame, hashtable, gtfsObjectType);
        for (int i = 0; i < 4; i++)
        {
            this.addLabels[i].setText(this.addLabels[i].getText()+" *");
        }
    }

    @Override
    boolean checkRemoveAction(int keyIndex)
    {
        String findingIDValue = ((Agency)this.hashtable.get(this.keys.get(keyIndex))).getAgency_id();
        return !this.contentPanel.getTablePanel(GTFSFiles.GTFSObjectType.ROUTE).tableContainsValueAt(findingIDValue, 1);
    }

    @Override
    boolean checkAddInputs()
    {
        for (int i = 0; i < 4; i++)
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

        if(((JTextField)this.addFormObjects.get(0)).getText().equals("")
                || ((JTextField)this.addFormObjects.get(1)).getText().equals("")
                || ((JTextField)this.addFormObjects.get(2)).getText().equals("")
                || ((JTextField)this.addFormObjects.get(3)).getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Some required fields are empty!", "Empty required fields", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText()))
            {
                this.setRedBorder(this.addFormObjects.get(0));
                JOptionPane.showMessageDialog(null, "Agency with this id already exists!", "Existing agency", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                this.setDefaultBorder(this.addFormObjects.get(0));
            }
        }

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
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.AGENCY);

            for (int i = 0; i < 4; i++)
            {
                if(((JTextField)this.addFormObjects.get(i)).getText().equals(""))
                {
                    this.setDefaultBorder(this.addFormObjects.get(i));
                }
            }
            JOptionPane.showMessageDialog(null, "New agency has been successfully created", "New agency created", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    void updateTable()
    {
        this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.AGENCY);
    }
}
