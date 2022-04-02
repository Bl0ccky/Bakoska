package GUI.DetailPanels;

import GUI.MainFrame;
import GUI.TablePanels.TablePanel;
import TextFiles.GTFSObjectType;
import TextFiles.IGTFSObject;

import java.util.Hashtable;

public class TripDetailPanel extends DetailPanel{

    public TripDetailPanel(TablePanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> stopHashtable, Hashtable<String, IGTFSObject> stopTimeHashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject) {
        super(panel, mainFrame, stopHashtable, stopTimeHashtable, gtfsObjectType, igtfsObject);
    }

    @Override
    void createLabelNames() {

    }
}
