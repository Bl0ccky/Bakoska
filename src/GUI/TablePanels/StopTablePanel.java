package GUI.TablePanels;

import GUI.MainFrame;
import TextFiles.IObject;
import TextFiles.ObjectType;

import javax.swing.*;
import java.util.Hashtable;

public class StopTablePanel extends TablePanel{
    public StopTablePanel(JPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType) {
        super(panel, mainFrame, hashtable, objectType);
    }
}