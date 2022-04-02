package GUI.DetailPanels;

import App.MyTileSource;
import GUI.MainFrame;
import GUI.TableModels.*;
import GUI.TablePanels.TablePanel;
import TextFiles.GTFSObjectType;
import TextFiles.IGTFSObject;

import TextFiles.Stop;
import org.openstreetmap.gui.jmapviewer.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class DetailPanel extends JPanel implements ActionListener, ListSelectionListener {
    private final TablePanel contentPanel;
    private final MainFrame mainFrame;
    private final GTFSObjectType gtfsObjectType;
    protected MyTableItemModel myTableItemModel;
    private JTable table;
    private final Hashtable<String, IGTFSObject> hashtable;
    protected String[] columnNames;
    protected IGTFSObject igtfsObject;
    protected final ArrayList<String> keys;
    private JButton detailButton;
    private JButton backButton;

    public DetailPanel(TablePanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, Hashtable<String, IGTFSObject> secondHashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject) {
        this.contentPanel = panel;
        this.mainFrame = mainFrame;
        this.hashtable = hashtable;
        this.keys = new ArrayList<>(hashtable.keySet());
        this.gtfsObjectType = gtfsObjectType;
        this.igtfsObject = igtfsObject;


        if (this.gtfsObjectType == GTFSObjectType.ROUTE || this.gtfsObjectType == GTFSObjectType.STOP)
        {
            this.columnNames = mainFrame.getDataLoader().getHashTableColumnNames(GTFSObjectType.TRIP);
            this.myTableItemModel = new TripTableModel(this.hashtable, this.keys, this.columnNames);
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
            this.myTableItemModel = new TripDetailTableModel(this.hashtable, secondHashtable, this.keys, this.columnNames);


        }


        this.setLayout(null);

        this.createButtonSection();
        this.createLabelNames();
        this.createTableSection();
        this.createMapSection();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        if (e.getSource() == this.detailButton)
        {

            switch (this.gtfsObjectType)
            {
                case TRIP -> this.mainFrame.createDetailPanel(GTFSObjectType.STOP);

                case ROUTE -> this.mainFrame.createDetailPanel(GTFSObjectType.TRIP);

                default -> this.mainFrame.createDetailPanel(GTFSObjectType.ROUTE);
            }

            CardLayout cardLayout = (CardLayout) this.contentPanel.getContentPanel().getLayout();
            cardLayout.show(this.contentPanel.getContentPanel(), "detailPanel");
        }

         */
        if (e.getSource() == this.backButton) {
            CardLayout cardLayout = (CardLayout) this.contentPanel.getContentPanel().getContentPanel().getLayout();
            cardLayout.show(this.contentPanel.getContentPanel().getContentPanel(), "adminPanel");
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.detailButton.setVisible(true);
    }

    abstract void createLabelNames();
    protected void createLabelSection(String detailHeadText, String[] labelNames, Object[] labelValues)
    {

        JLabel detailHeadLabel = new JLabel(detailHeadText);
        detailHeadLabel.setBounds(150,30, 150, 30);
        detailHeadLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
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
                x = 275;
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
        this.table.getSelectionModel().addListSelectionListener(this);
        this.table.setRowSorter(new TableRowSorter<>(this.myTableItemModel));
        JScrollPane scrollPane = new JScrollPane(table);
        //TABULKA NALAVO
        scrollPane.setBounds(50, 470, 1350, 350);
        //TABULKA NAPRAVO
        //scrollPane.setBounds(700, 50, 900, 800);
        this.add(scrollPane);
    }

    private void createButtonSection() {
        this.detailButton = new JButton("Detail");
        //this.detailButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        this.detailButton.setFocusable(false);
        this.detailButton.setVisible(false);
        this.detailButton.addActionListener(this);
        this.detailButton.setBounds(50, 430, 100, 30);
        this.add(detailButton);

        this.backButton = new JButton("<<");
        this.backButton.setFocusable(false);
        this.backButton.addActionListener(this);
        this.backButton.setBounds(50, 30, 50, 30);
        this.add(backButton);
    }

    private void createMapSection() {
        JMapViewer map = new JMapViewer();
        map.setDisplayPosition(new Coordinate(((Stop)igtfsObject).getStop_lat(), ((Stop)igtfsObject).getStop_lon()), 12);
        MapMarkerDot mapMarkerDot = new MapMarkerDot(new Coordinate(((Stop)igtfsObject).getStop_lat(), ((Stop)igtfsObject).getStop_lon()));
        mapMarkerDot.setColor(Color.RED);
        mapMarkerDot.setBackColor(Color.RED);
        map.addMapMarker(mapMarkerDot);
        map.setTileLoader(new OsmTileLoader(map));
        map.setTileSource(new MyTileSource.Mapnik1());
        DefaultMapController mapController = new DefaultMapController(map);
        mapController.setMovementMouseButton(MouseEvent.BUTTON1);
        //MAPA NAPRAVO
        map.setBounds(600, 30, 800, 420);
        //MAPA NALAVO
        //map.setBounds(30, 300, 600, 550);
        this.add(map);
    }


}
