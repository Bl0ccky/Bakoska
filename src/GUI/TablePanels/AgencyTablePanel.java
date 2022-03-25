package GUI.TablePanels;

import GUI.MainFrame;
import TextFiles.Agency;
import TextFiles.IObject;
import TextFiles.ObjectType;

import javax.swing.*;
import java.util.Hashtable;

public class AgencyTablePanel extends TablePanel
{
    public AgencyTablePanel(JPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType)
    {
        super(panel, mainFrame, hashtable, objectType);
    }

    @Override
    boolean checkAddInputs()
    {
        return !this.addTextFields[0].getText().equals("")
                && !this.addTextFields[1].getText().equals("")
                && !this.addTextFields[2].getText().equals("")
                && !this.addTextFields[3].getText().equals("")
                && !this.hashtable.containsKey(this.addTextFields[0].getText());
    }

    @Override
    void addNewObject()
    {
        if(this.checkAddInputs())
        {
            Agency newAgency = new Agency();
            newAgency.setAgency_id(this.addTextFields[0].getText());
            newAgency.setAgency_name(this.addTextFields[1].getText());
            newAgency.setAgency_url(this.addTextFields[2].getText());
            newAgency.setAgency_timezone(this.addTextFields[3].getText());
            newAgency.setAgency_lang(this.addTextFields[4].getText());
            newAgency.setAgency_phone(this.addTextFields[5].getText());
            newAgency.setAgency_fare_url(this.addTextFields[6].getText());
            this.hashtable.put(newAgency.getKey(), newAgency);
            this.keys.add(newAgency.getKey());
            this.myTableItemModel.fireTableDataChanged();
        }
    }
}
