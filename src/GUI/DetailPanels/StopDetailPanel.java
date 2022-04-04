package GUI.DetailPanels;

import GUI.MainFrame;
import GUI.TablePanels.TablePanel;
import TextFiles.GTFSObjectType;
import TextFiles.IGTFSObject;
import TextFiles.Stop;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import java.util.Hashtable;

public class StopDetailPanel extends DetailPanel
{

    public StopDetailPanel(TablePanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject) {
        super(panel, mainFrame, hashtable, gtfsObjectType, igtfsObject);
    }

    @Override
    void createLabelNames()
    {
        String[]labelNames = {"Stop id", "Stop name", "Stop lat", "Stop lon"};
        Object[]labelValues = {
                ((Stop)this.igtfsObject).getStop_id(),
                ((Stop)this.igtfsObject).getStop_name(),
                ((Stop)this.igtfsObject).getStop_lat(),
                ((Stop)this.igtfsObject).getStop_lon()};
        this.createLabelSection("Stop Detail", labelNames, labelValues);

    }

    @Override
    void createMapMarkers()
    {
        MapMarkerDot mapMarkerDot = new MapMarkerDot(new Coordinate(((Stop)igtfsObject).getStop_lat(), ((Stop)igtfsObject).getStop_lon()));
        MapMarkerDot[] mapMarkerDots = {mapMarkerDot};
        this.createMapSection(mapMarkerDots);
    }
}
