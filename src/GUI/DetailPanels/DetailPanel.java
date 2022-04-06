package GUI.DetailPanels;

import App.MyTileSource;
import GUI.MainFrame;
import GUI.TableModels.*;
import GUI.TablePanels.TablePanel;
import GTFSFiles.*;

import GTFSFiles.TripDetail.SpecialStop;
import org.openstreetmap.gui.jmapviewer.*;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class DetailPanel extends JPanel implements ActionListener{
    protected final TablePanel contentPanel;
    protected final MainFrame mainFrame;
    private final GTFSObjectType gtfsObjectType;
    protected MyTableItemModel myTableItemModel;
    protected JTable table;
    protected final Hashtable<String, IGTFSObject> hashtable;
    protected String[] columnNames;
    protected IGTFSObject igtfsObject;
    protected final ArrayList<String> keys;
    protected JButton backButton;
    protected JMapViewer map;

    public DetailPanel(TablePanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject) {
        this.contentPanel = panel;
        this.mainFrame = mainFrame;
        this.hashtable = hashtable;
        this.keys = new ArrayList<>(hashtable.keySet());
        this.gtfsObjectType = gtfsObjectType;
        this.igtfsObject = igtfsObject;
        this.map = new JMapViewer();
        this.map.setTileLoader(new OsmTileLoader(this.map));
        this.map.setTileSource(new MyTileSource.Mapnik1());
        DefaultMapController mapController = new DefaultMapController(this.map);
        mapController.setMovementMouseButton(MouseEvent.BUTTON1);
        this.add(this.map);



        if (this.gtfsObjectType == GTFSObjectType.ROUTE || this.gtfsObjectType == GTFSObjectType.STOP)
        {
            this.columnNames = mainFrame.getDataLoader().getHashTableColumnNames(GTFSObjectType.TRIP);
            this.myTableItemModel = new TripTableModel(this.mainFrame, this.hashtable, this.keys, this.columnNames);
        }
        else
        {
            this.columnNames = new String[]{
                    "stop_id",
                    "stop_code",
                    "stop_name",
                    "stop_desc",
                    "arrival_time",
                    "departure_time",
                    "stop_sequence",
                    "stop_lat",
                    "stop_lon",
                    "location_type",
                    "stop_timezone",
                    "wheelchair_boarding"
            };
            this.myTableItemModel = new TripDetailTableModel(this.mainFrame, this.hashtable, this.keys, this.columnNames);


        }


        this.setLayout(null);

        this.createButtonSection();
        this.createLabelNames();
        this.createTableSection();
        if(this instanceof StopDetailPanel || this instanceof TripDetailPanel)
        {
            this.createMapMarkers();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.backButton)
        {
            CardLayout cardLayout = (CardLayout) this.contentPanel.getContentPanel().getContentPanel().getLayout();
            cardLayout.show(this.contentPanel.getContentPanel().getContentPanel(), "adminPanel");
        }

    }

    public static void createTripDetail(Hashtable<String, IGTFSObject> detailPanelHashTable, Trip igtfsObject, MainFrame mainFrame) {
        Hashtable<String, IGTFSObject> stopTimeHashTable = mainFrame.getDataLoader().getAllStopTimes();
        ArrayList<String> stopTimeKeys = new ArrayList<>(stopTimeHashTable.keySet());

        Hashtable<String, IGTFSObject> stopHashTable = mainFrame.getDataLoader().getAllStops();
        ArrayList<String> stopKeys = new ArrayList<>(stopHashTable.keySet());

        String trip_tripId = igtfsObject.getTrip_id();

        for (String stopTimeKey : stopTimeKeys) {
            StopTime stopTime = (StopTime) stopTimeHashTable.get(stopTimeKey);
            String stopTime_tripId = stopTime.getTrip_id();

            if (stopTime_tripId.equals(trip_tripId)) {
                for (String stopKey : stopKeys) {
                    Stop stop = (Stop) stopHashTable.get(stopKey);
                    String stop_stopId = stop.getStop_id();
                    if (stop_stopId.equals(stopTime.getStop_id())) {
                        detailPanelHashTable.put(stop.getKey(), new SpecialStop(
                                stop.getStop_id(),
                                stop.getStop_code(),
                                stop.getStop_name(),
                                stop.getStop_desc(),
                                stopTime.getArrival_time(),
                                stopTime.getDeparture_time(),
                                stopTime.getStop_sequence(),
                                stop.getStop_lat(),
                                stop.getStop_lon(),
                                stop.getLocation_type(),
                                stop.getStop_timezone(),
                                stop.getWheelchair_boarding()));
                    }

                }

            }

        }
    }



    abstract void createLabelNames();
    protected void createLabelSection(String detailHeadText, String[] labelNames, Object[] labelValues)
    {

        JLabel detailHeadLabel = new JLabel(detailHeadText);
        detailHeadLabel.setBounds(150,30, 250, 30);
        detailHeadLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(detailHeadLabel);

        int x = 75;
        int y1 = 100;
        int y2 = 100;
        JLabel[] attributesNames = new JLabel[labelNames.length];
        JLabel[] attributesValues = new JLabel[labelValues.length];

        for (int i = 0; i < labelNames.length; i++) {
            attributesNames[i] = new JLabel(labelNames[i] + ":");
            attributesValues[i] = new JLabel(String.valueOf(labelValues[i]));

            if (i >= labelNames.length / 2) {
                x = 350;
                attributesNames[i].setBounds(x, y2, 160, 30);
                y2 += 40;
                attributesValues[i].setBounds(x, y2, 160, 30);
                y2 += 80;

            } else {
                attributesNames[i].setBounds(x, y1, 160, 30);
                y1 += 40;
                attributesValues[i].setBounds(x, y1, 160, 30);
                y1 += 80;
            }

            attributesNames[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            this.add(attributesNames[i]);
            this.add(attributesValues[i]);
        }

    }


    private void createTableSection() {
        this.table = new JTable(this.myTableItemModel);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.table.setRowSorter(new TableRowSorter<>(this.myTableItemModel));
        JScrollPane scrollPane = new JScrollPane(table);
        //TABULKA NALAVO
        scrollPane.setBounds(50, 470, 1350, 350);
        //TABULKA NAPRAVO
        //scrollPane.setBounds(700, 50, 900, 800);
        this.add(scrollPane);
    }

    private void createButtonSection() {
        this.backButton = new JButton("<<");
        this.backButton.setFocusable(false);
        this.backButton.addActionListener(this);
        this.backButton.setBounds(50, 30, 50, 30);
        this.add(backButton);
    }

    protected void createMapSection(MapMarkerDot[] mapMarkerDots) {
        //MAPA NAPRAVO
        this.map.setBounds(600, 30, 800, 420);
        //MAPA NALAVO
        //map.setBounds(30, 300, 600, 550);
        map.setDisplayPosition(new Coordinate(mapMarkerDots[0].getLat(), mapMarkerDots[0].getLon()), 12);
        this.map.removeAllMapMarkers();
        for (MapMarkerDot mapMarkerDot: mapMarkerDots)
        {
            mapMarkerDot.setColor(Color.RED);
            mapMarkerDot.setBackColor(Color.RED);
            map.addMapMarker(mapMarkerDot);
        }


        map.setDisplayToFitMapMarkers();


    }

    abstract void createMapMarkers();


}
