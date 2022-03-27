package GUI.TablePanels;

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
import TextFiles.*;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class TablePanel extends JPanel implements ActionListener, ListSelectionListener
{
    protected final JPanel contentPanel;
    protected final MainFrame mainFrame;
    protected final Hashtable<String, IObject> hashtable;
    protected final String[] columnNames;
    protected final Object[] columnTypes;
    protected final ArrayList<String> keys;
    protected final MyTableItemModel myTableItemModel;
    private JTextField searchField;
    private JButton searchButton;
    private TableRowSorter<MyTableItemModel> tableRowSorter;
    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private JButton detailButton;
    private JCheckBox[] searchCheckBoxes;
    protected ArrayList<JComponent> addFormObjects;

    public TablePanel(JPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType) {
        this.keys = new ArrayList<>(hashtable.keySet());
        this.contentPanel = panel;
        this.mainFrame = mainFrame;
        this.hashtable = hashtable;
        this.columnNames = mainFrame.getDataLoader().getHashTableColumnNames(objectType);
        this.columnTypes = mainFrame.getDataLoader().getHashTableColumnTypes(objectType);
        switch (objectType)
        {
            case CALENDAR -> this.myTableItemModel = new CalendarTableModel(this.hashtable, this.keys, this.columnNames);
            case CALENDAR_DATE -> this.myTableItemModel = new CalendarDateTableModel(this.hashtable, this.keys, this.columnNames);
            case ROUTE -> this.myTableItemModel = new RouteTableModel(this.hashtable, this.keys, this.columnNames);
            case STOP -> this.myTableItemModel = new StopTableModel(this.hashtable, this.keys, this.columnNames);
            case STOP_TIME -> this.myTableItemModel = new StopTimeTableModel(this.hashtable, this.keys, this.columnNames);
            case TRIP -> this.myTableItemModel = new TripTableModel(this.hashtable, this.keys, this.columnNames);
            default -> this.myTableItemModel = new AgencyTableModel(this.hashtable, this.keys, this.columnNames);
        }

        this.setLayout(null);

        this.createTableSection();
        this.createSearchSection();
        this.createButtonsSection();
        this.createAddSection();

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == this.searchButton)
        {
            this.tableRowSorter.setRowFilter(new TableRowFilter(this.searchField.getText(), this.searchCheckBoxes));
        }
        else if(e.getSource() == this.addButton)
        {
            this.addNewObject();

        }
        else if(e.getSource() == this.editButton)
        {

        }
        else if(e.getSource() == this.removeButton)
        {

        }
        else if(e.getSource() == this.detailButton)
        {
            this.mainFrame.createDetailPanel();
            CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
            cardLayout.show(this.contentPanel, "detailPanel");
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        this.detailButton.setVisible(true);
        this.editButton.setVisible(true);
        this.removeButton.setVisible(true);
    }

    private void createTableSection()
    {
        this.tableRowSorter = new TableRowSorter<>(this.myTableItemModel);
        JTable table = new JTable(myTableItemModel);
        table.setRowSorter(tableRowSorter);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getSelectionModel().addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10, 900,800);

        this.add(scrollPane);

    }

    private void createSearchSection()
    {
        JLabel searchLabel = new JLabel("Search");
        searchLabel.setBounds(950,50, 50,30);

        this.searchField = new JTextField();
        this.searchField.setBounds(1000,50, 350,30);

        this.searchButton = new JButton("Search");
        this.searchButton.setFocusable(false);
        this.searchButton.addActionListener(this);
        this.searchButton.setBounds(1000,90,100,30);

        this.add(searchLabel);
        this.add(searchField);
        this.add(searchButton);

        int x = 1110;
        int y1 = 90;
        int y2 = 90;
        this.searchCheckBoxes = new JCheckBox[this.columnNames.length];
        for (int i = 0; i < this.columnNames.length; i++) {
            this.searchCheckBoxes[i] = new JCheckBox(this.columnNames[i]);
            this.searchCheckBoxes[i].setFocusable(false);
            if(i >= this.columnNames.length/2)
            {
                x = 1270;
                this.searchCheckBoxes[i].setBounds(x, y2, 150, 20);
                y2 += 30;
            }
            else
            {
                this.searchCheckBoxes[i].setBounds(x, y1, 150, 20);
                y1 += 30;
            }

            this.add(this.searchCheckBoxes[i]);
        }
    }

    private void createButtonsSection()
    {
        this.detailButton = new JButton("Detail");
        this.detailButton.setFocusable(false);
        this.detailButton.setVisible(false);
        this.detailButton.addActionListener(this);
        this.detailButton.setBounds(1000,150,100,30);

        this.editButton = new JButton("Edit");
        this.editButton.setFocusable(false);
        this.editButton.setVisible(false);
        this.editButton.addActionListener(this);
        this.editButton.setBounds(1000,190,100,30);

        this.removeButton = new JButton("Remove");
        this.removeButton.setFocusable(false);
        this.removeButton.setVisible(false);
        this.removeButton.addActionListener(this);
        this.removeButton.setBounds(1000,230,100,30);

        this.add(detailButton);
        this.add(editButton);
        this.add(removeButton);

    }

    private void createAddSection()
    {


        this.addFormObjects = new ArrayList<>();

        int x = 1110;
        int y1 = 350;
        int y2 = 350;
        JLabel[] addLabels = new JLabel[this.columnNames.length];

        for (int i = 0; i < this.columnNames.length; i++)
        {
            if(this.columnTypes[i] instanceof String || this.columnTypes[i] instanceof Integer || this.columnTypes[i] instanceof Double || this.columnTypes[i] instanceof Float)
            {
                this.addFormObjects.add(new JTextField());
            }
            else if(this.columnTypes[i] instanceof LocalDate)
            {
                this.addFormObjects.add(new DatePicker());
            }
            else if(this.columnTypes[i] instanceof LocalTime)
            {
                this.addFormObjects.add(new TimePicker());
            }
            else
            {
                if(this.columnTypes[i] instanceof DayServiceAvailability)
                {
                    this.addFormObjects.add(new JComboBox<>(DayServiceAvailability.values()));
                }
                else if(this.columnTypes[i] instanceof ExceptionType)
                {
                    this.addFormObjects.add(new JComboBox<>(ExceptionType.values()));
                }
                else if(this.columnTypes[i] instanceof RouteType)
                {
                    this.addFormObjects.add(new JComboBox<>(RouteType.values()));
                }
                else if(this.columnTypes[i] instanceof StopLocationType)
                {
                    this.addFormObjects.add(new JComboBox<>(StopLocationType.values()));
                }
                else if(this.columnTypes[i] instanceof StopWheelchairBoarding)
                {
                    this.addFormObjects.add(new JComboBox<>(StopWheelchairBoarding.values()));
                }
                else if(this.columnTypes[i] instanceof DropOffType)
                {
                    this.addFormObjects.add(new JComboBox<>(DropOffType.values()));
                }
                else if(this.columnTypes[i] instanceof PickupType)
                {
                    this.addFormObjects.add(new JComboBox<>(PickupType.values()));
                }
                else if(this.columnTypes[i] instanceof TimePoint)
                {
                    this.addFormObjects.add(new JComboBox<>(TimePoint.values()));
                }
                else if(this.columnTypes[i] instanceof TripDirectionID)
                {
                    this.addFormObjects.add(new JComboBox<>(TripDirectionID.values()));
                }
                else if(this.columnTypes[i] instanceof TripWheelchairAccessible)
                {
                    this.addFormObjects.add(new JComboBox<>(TripWheelchairAccessible.values()));
                }
            }

            this.addFormObjects.get(i).setFocusable(false);

            addLabels[i] = new JLabel(this.columnNames[i]);

            if(i >= this.columnNames.length/2)
            {
                x = 1280;
                addLabels[i].setBounds(x, y2, 160, 30);
                y2 += 30;
                this.addFormObjects.get(i).setBounds(x, y2, 160, 30);
                y2 += 30;
            }
            else
            {
                addLabels[i].setBounds(x, y1, 160, 30);
                y1 += 30;
                this.addFormObjects.get(i).setBounds(x, y1, 160, 30);

                y1 += 30;
            }

            this.add(addLabels[i]);
            this.add(this.addFormObjects.get(i));
        }

        this.addButton = new JButton("Add");
        this.addButton.setFocusable(false);
        this.addButton.addActionListener(this);
        this.addButton.setBounds(1000,380,100,30);
        this.add(addButton);

    }

    abstract boolean checkAddInputs();
    abstract void addNewObject();

}
