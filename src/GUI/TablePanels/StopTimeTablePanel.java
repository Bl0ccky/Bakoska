package GUI.TablePanels;

import GUI.MainFrame;
import TextFiles.IObject;
import TextFiles.ObjectType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.Hashtable;

public class StopTimeTablePanel extends TablePanel {
    public StopTimeTablePanel(JPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType) {
        super(panel, mainFrame, hashtable, objectType);
    }

}
