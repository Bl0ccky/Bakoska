package GUI.DetailPanels;

import GUI.MainFrame;
import GUI.TablePanels.TablePanel;
import GTFSFiles.*;
import GTFSFiles.TripDetail.SpecialStop;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import java.util.ArrayList;
import java.util.Hashtable;

public class TripDetailPanel extends DetailPanel{

    public TripDetailPanel(TablePanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject) {
        super(panel, mainFrame, hashtable, gtfsObjectType, igtfsObject);


    }

    @Override
    void createLabelNames()
    {
        String[]labelNames = {"Trip id", "Trip head sign", "1st arr-time", "Last dep-time", "First stop id", "First stop name", "Last stop id", "Last stop name"};
        ((Trip)igtfsObject).createDetailedAttributes();
        this.createLabelSection("Trip Detail", labelNames, ((Trip)igtfsObject).getDetailedAttributes());
    }

    @Override
    void createMapMarkers()
    {
        MapMarkerDot[] mapMarkerDots = new MapMarkerDot[this.hashtable.size()];
        ArrayList<String> stopKeys = new ArrayList<>(this.hashtable.keySet());
        for (int i = 0; i < this.hashtable.size(); i++)
        {
            mapMarkerDots[i] = new MapMarkerDot(new Coordinate(((SpecialStop)this.hashtable.get(stopKeys.get(i))).getStop_lat(), ((SpecialStop)this.hashtable.get(stopKeys.get(i))).getStop_lon()));
            mapMarkerDots[i].setName(String.valueOf(((SpecialStop)this.hashtable.get(stopKeys.get(i))).getStop_sequence()));
        }
        this.createMapSection(mapMarkerDots);
    }
}
