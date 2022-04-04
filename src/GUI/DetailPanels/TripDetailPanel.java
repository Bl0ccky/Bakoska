package GUI.DetailPanels;

import GUI.MainFrame;
import GUI.TablePanels.TablePanel;
import TextFiles.*;
import TextFiles.TripDetail.SpecialStop;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class TripDetailPanel extends DetailPanel{

    public TripDetailPanel(TablePanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject) {
        super(panel, mainFrame, hashtable, gtfsObjectType, igtfsObject);


    }

    @Override
    void createLabelNames()
    {
        String[]labelNames = {"Trip id", "Trip head sign", "1st arr-time", "Last dep-time", "First stop", "Last stop"};
        ArrayList<String> specialStopKeys = new ArrayList<>(this.hashtable.keySet());

        int minStopSequence = Integer.MAX_VALUE;
        String first_stop_name = "";
        LocalTime first_stop_arrival_time = null;

        int maxStopSequence = 0;
        String last_stop_name = "";
        LocalTime last_stop_departure_time = null;

        for (String specialStopTimeKey : specialStopKeys)
        {
            if(((SpecialStop)this.hashtable.get(specialStopTimeKey)).getStop_sequence() < minStopSequence)
            {
                minStopSequence = ((SpecialStop)this.hashtable.get(specialStopTimeKey)).getStop_sequence();
                first_stop_name = ((SpecialStop)this.hashtable.get(specialStopTimeKey)).getStop_name();
                first_stop_arrival_time = ((SpecialStop)this.hashtable.get(specialStopTimeKey)).getArrival_time();
            }
            else if(((SpecialStop)this.hashtable.get(specialStopTimeKey)).getStop_sequence() > maxStopSequence)
            {
                maxStopSequence = ((SpecialStop)this.hashtable.get(specialStopTimeKey)).getStop_sequence();
                last_stop_name = ((SpecialStop)this.hashtable.get(specialStopTimeKey)).getStop_name();
                last_stop_departure_time = ((SpecialStop)this.hashtable.get(specialStopTimeKey)).getDeparture_time();
            }
        }

        Object[]labelValues = {
                ((Trip)this.igtfsObject).getTrip_id(),
                ((Trip)this.igtfsObject).getTrip_headsign(),
                first_stop_arrival_time,
                last_stop_departure_time,
                first_stop_name,
                last_stop_name};
        this.createLabelSection("Trip Detail", labelNames, labelValues);

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
