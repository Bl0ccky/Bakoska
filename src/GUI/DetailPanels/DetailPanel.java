package GUI.DetailPanels;

import App.MyTileSource;
import Enums.Calendar.DayServiceAvailability;
import Enums.CalendarDate.ExceptionType;
import Enums.Route.RouteType;
import Enums.Stop.StopLocationType;
import Enums.Stop.StopWheelchairBoarding;
import Enums.StopTime.DropOffType;
import Enums.StopTime.PickupType;
import Enums.StopTime.TimePoint;
import Enums.Trip.TripDirectionID;
import Enums.Trip.TripWheelchairAccessible;
import GUI.MainFrame;
import GUI.TableModels.*;
import GUI.TablePanels.TablePanel;
import TextFiles.GTFSObjectType;
import TextFiles.IGTFSObject;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.tableeditors.DateTableEditor;
import com.github.lgooddatepicker.tableeditors.TimeTableEditor;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.OsmTileLoader;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class DetailPanel extends JPanel implements ActionListener, ListSelectionListener {
    private final TablePanel contentPanel;
    private final MainFrame mainFrame;
    private final GTFSObjectType gtfsObjectType;
    private final MyTableItemModel myTableItemModel;
    private JTable table;
    private final Hashtable<String, IGTFSObject> hashtable;
    private final String[] columnNames;
    protected final ArrayList<String> keys;
    private JButton detailButton;
    private JButton backButton;

    public DetailPanel(TablePanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType) {
        this.contentPanel = panel;
        this.mainFrame = mainFrame;
        this.hashtable = hashtable;
        this.keys = new ArrayList<>(hashtable.keySet());
        this.gtfsObjectType = gtfsObjectType;


        if (this.gtfsObjectType == GTFSObjectType.TRIP) {
            this.columnNames = mainFrame.getDataLoader().getHashTableColumnNames(GTFSObjectType.STOP);
            this.myTableItemModel = new StopTableModel(this.hashtable, this.keys, this.columnNames);
        } else {
            this.columnNames = mainFrame.getDataLoader().getHashTableColumnNames(GTFSObjectType.TRIP);
            this.myTableItemModel = new TripTableModel(this.hashtable, this.keys, this.columnNames);
        }

        this.setLayout(null);

        this.createButtonSection();
        this.createLabelSection();
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

    private void createLabelSection() {
        int x = 30;
        int y1 = 50;
        int y2 = 50;
        JLabel[] attributesLabels = new JLabel[this.columnNames.length];

        for (int i = 0; i < this.columnNames.length; i++) {
            attributesLabels[i] = new JLabel(columnNames[i]);

            if (i >= this.columnNames.length / 2) {
                x = 150;
                attributesLabels[i].setBounds(x, y2, 160, 30);
                y2 += 40;

            } else {
                attributesLabels[i].setBounds(x, y1, 160, 30);
                y1 += 40;
            }

            this.add(attributesLabels[i]);
        }

    }


    private void createTableSection() {
        this.table = new JTable(this.myTableItemModel);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.table.getSelectionModel().addListSelectionListener(this);
        this.table.setRowSorter(new TableRowSorter<>(this.myTableItemModel));
        JScrollPane scrollPane = new JScrollPane(table);
        //TABULKA NALAVO
        //scrollPane.setBounds(30, 300, 600, 550);
        //TABULKA NAPRAVO
        scrollPane.setBounds(700, 50, 900, 800);
        this.add(scrollPane);
    }

    private void createButtonSection() {
        this.detailButton = new JButton("Detail");
        this.detailButton.setFocusable(false);
        this.detailButton.setVisible(false);
        this.detailButton.addActionListener(this);
        this.detailButton.setBounds(700, 10, 100, 30);
        this.add(detailButton);

        this.backButton = new JButton("<");
        this.backButton.setFocusable(false);
        this.backButton.addActionListener(this);
        this.backButton.setBounds(30, 10, 50, 30);
        this.add(backButton);
    }

    private void createMapSection() {
        JMapViewer map = new JMapViewer();
        map.setDisplayPosition(new Coordinate(48.8566, 2.3522), 12);
        map.setTileLoader(new OsmTileLoader(map));
        map.setTileSource(new MyTileSource.Mapnik1());
        DefaultMapController mapController = new DefaultMapController(map);
        mapController.setMovementMouseButton(MouseEvent.BUTTON1);
        //MAPA NAPRAVO
        //map.setBounds(700, 50, 900, 800);
        //MAPA NALAVO
        map.setBounds(30, 300, 600, 550);
        this.add(map);
    }


}
