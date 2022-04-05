package GUI.DetailPanels;

import GUI.MainFrame;
import GUI.TablePanels.TablePanel;
import GTFSFiles.GTFSObjectType;
import GTFSFiles.IGTFSObject;
import GTFSFiles.Route;
import GTFSFiles.Trip;
import GTFSFiles.TripDetail.SpecialStop;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Hashtable;

public class RouteDetailPanel extends DetailPanel implements ListSelectionListener
{
    private JButton detailButton;
    private Hashtable<String, IGTFSObject> tripDetailHashTable;
    private Trip selectedTrip;

    public RouteDetailPanel(TablePanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject) {
        super(panel, mainFrame, hashtable, gtfsObjectType, igtfsObject);
        this.table.getSelectionModel().addListSelectionListener(this);
        this.createDetailButton();
    }

    @Override
    void createLabelNames()
    {
        String[]labelNames = {"Route id", "Short name", "Long name", "Route Type"};
        Object[]labelValues = {
                ((Route)this.igtfsObject).getRoute_id(),
                ((Route)this.igtfsObject).getRoute_short_name(),
                ((Route)this.igtfsObject).getRoute_long_name(),
                ((Route)this.igtfsObject).getRoute_type()};
        this.createLabelSection("Route Detail", labelNames, labelValues);
    }

    @Override
    void createMapMarkers()
    {
        MapMarkerDot[] mapMarkerDots = new MapMarkerDot[this.tripDetailHashTable.size()];
        ArrayList<String> stopKeys = new ArrayList<>(this.tripDetailHashTable.keySet());
        for (int i = 0; i < this.tripDetailHashTable.size(); i++)
        {
            mapMarkerDots[i] = new MapMarkerDot(new Coordinate(((SpecialStop)this.tripDetailHashTable.get(stopKeys.get(i))).getStop_lat(), ((SpecialStop)this.tripDetailHashTable.get(stopKeys.get(i))).getStop_lon()));
            mapMarkerDots[i].setName(String.valueOf(((SpecialStop)this.tripDetailHashTable.get(stopKeys.get(i))).getStop_sequence()));
            //mapMarkerDots[i].setName(this.selectedTrip.getTrip_id());
        }
        this.createMapSection(mapMarkerDots);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.detailButton)
        {
            this.mainFrame.createDetailPanel(this.tripDetailHashTable, GTFSObjectType.TRIP, this.selectedTrip);
            CardLayout cardLayout = (CardLayout) this.contentPanel.getContentPanel().getContentPanel().getLayout();
            cardLayout.show(this.contentPanel.getContentPanel().getContentPanel(), "detailPanel");
        }
        else if(e.getSource() == this.backButton)
        {
            CardLayout cardLayout = (CardLayout) this.contentPanel.getContentPanel().getContentPanel().getLayout();
            cardLayout.show(this.contentPanel.getContentPanel().getContentPanel(), "adminPanel");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        if(!e.getValueIsAdjusting())
        {
            this.tripDetailHashTable = new Hashtable<>();
            this.selectedTrip = (Trip)this.hashtable.get(this.keys.get(this.table.convertRowIndexToModel(this.table.getSelectedRow())));
            Hashtable<String, IGTFSObject> stopTimesHashTable = this.mainFrame.getDataLoader().getAllStopTimes();
            Hashtable<String, IGTFSObject> stopHashTable = this.mainFrame.getDataLoader().getAllStops();
            this.selectedTrip.createSpecialStopHashTable(stopTimesHashTable, stopHashTable);
            tripDetailHashTable = this.selectedTrip.getSpecialStopHashTable();

            this.detailButton.setVisible(true);
            this.createMapMarkers();
        }
    }

    private void createDetailButton()
    {
        this.detailButton = new JButton("Detail");
        //this.detailButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        this.detailButton.setFocusable(false);
        this.detailButton.setVisible(false);
        this.detailButton.addActionListener(this);
        this.detailButton.setBounds(50, 430, 100, 30);
        this.add(detailButton);
    }


}
