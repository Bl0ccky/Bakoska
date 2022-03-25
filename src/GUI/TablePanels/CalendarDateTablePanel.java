package GUI.TablePanels;

import GUI.MainFrame;
import TextFiles.IObject;
import TextFiles.ObjectType;

import javax.swing.*;
import java.util.Hashtable;

public class CalendarDateTablePanel extends TablePanel{
    public CalendarDateTablePanel(JPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType) {
        super(panel, mainFrame, hashtable, objectType);
    }

    @Override
    boolean checkAddInputs()
    {
        return !this.addTextFields[0].getText().equals("")
                && !this.addTextFields[1].getText().equals("")
                && !this.addTextFields[2].getText().equals("")
                && !this.hashtable.containsKey(this.addTextFields[0].getText());
    }

    @Override
    void addNewObject() {

    }

}
