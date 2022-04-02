package GUI.DetailPanels;

import GUI.MainFrame;
import GUI.TablePanels.TablePanel;
import TextFiles.GTFSObjectType;
import TextFiles.IGTFSObject;
import TextFiles.Stop;

import java.util.Hashtable;

public class StopDetailPanel extends DetailPanel
{

    public StopDetailPanel(TablePanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject) {
        super(panel, mainFrame, hashtable, null, gtfsObjectType, igtfsObject);
    }

    @Override
    void createLabelNames()
    {
        String[]labelNames = {"stop_id", "stop_name", "stop_lat", "stop_lon"};
        Object[]labelValues = {
                ((Stop)this.igtfsObject).getStop_id(),
                ((Stop)this.igtfsObject).getStop_name(),
                ((Stop)this.igtfsObject).getStop_lat(),
                ((Stop)this.igtfsObject).getStop_lon()};
        this.createLabelSection("Stop Detail", labelNames, labelValues);

    }
}
