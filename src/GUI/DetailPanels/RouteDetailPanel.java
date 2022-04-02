package GUI.DetailPanels;

import GUI.MainFrame;
import GUI.TablePanels.TablePanel;
import TextFiles.GTFSObjectType;
import TextFiles.IGTFSObject;
import TextFiles.Route;

import java.util.Hashtable;

public class RouteDetailPanel extends DetailPanel
{

    public RouteDetailPanel(TablePanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject) {
        super(panel, mainFrame, hashtable, null, gtfsObjectType, igtfsObject);
    }

    @Override
    void createLabelNames()
    {
        String[]labelNames = {"route_id", "route_short_name", "route_long_name", "route_type"};
        Object[]labelValues = {
                ((Route)this.igtfsObject).getRoute_id(),
                ((Route)this.igtfsObject).getRoute_short_name(),
                ((Route)this.igtfsObject).getRoute_long_name(),
                ((Route)this.igtfsObject).getRoute_type()};
        this.createLabelSection("Route Detail", labelNames, labelValues);
    }
}
