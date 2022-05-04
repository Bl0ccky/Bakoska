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
import GUI.AdminPanel;
import GUI.MainFrame;
import GTFSFiles.*;
import GUI.TableModels.AgencyTableModel;
import GUI.TableModels.CalendarDateTableModel;
import GUI.TableModels.CalendarTableModel;
import GUI.TableModels.MyTableItemModel;
import GUI.TableModels.RouteTableModel;
import GUI.TableModels.StopTableModel;
import GUI.TableModels.StopTimeTableModel;
import GUI.TableModels.TripTableModel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.tableeditors.DateTableEditor;
import com.github.lgooddatepicker.tableeditors.TimeTableEditor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class TablePanel extends JPanel implements ActionListener, ListSelectionListener {
    protected final AdminPanel contentPanel;
    protected final MainFrame mainFrame;
    protected final Hashtable<String, IGTFSObject> hashtable;
    protected final String[] columnNames;
    protected final ArrayList<Object> columnTypes;
    protected final ArrayList<String> keys;
    protected final MyTableItemModel myTableItemModel;
    private final GTFSObjectType gtfsObjectType;
    private JTable table;
    private JTextField searchField;
    private JButton searchButton;
    private TableRowSorter<MyTableItemModel> tableRowSorter;
    private JButton addButton;
    private JButton removeButton;
    private JButton detailButton;
    private JCheckBox[] searchCheckBoxes;
    protected ArrayList<JComponent> addFormObjects;
    protected JLabel[] addLabels;

    public TablePanel(AdminPanel panel, MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType) {
        this.keys = new ArrayList<>(hashtable.keySet());
        this.contentPanel = panel;
        this.mainFrame = mainFrame;
        this.hashtable = hashtable;
        this.gtfsObjectType = gtfsObjectType;
        this.columnNames = mainFrame.getDataLoader().getHashTableColumnNames(this.gtfsObjectType);
        this.columnTypes = mainFrame.getDataLoader().getHashTableColumnTypes(this.gtfsObjectType);
        switch (this.gtfsObjectType) {
            case CALENDAR -> this.myTableItemModel = new CalendarTableModel(this.mainFrame, this.hashtable, this.keys, this.columnNames);
            case CALENDAR_DATE -> this.myTableItemModel = new CalendarDateTableModel(this.mainFrame, this.hashtable, this.keys, this.columnNames);
            case ROUTE -> this.myTableItemModel = new RouteTableModel(this.mainFrame, this.hashtable, this.keys, this.columnNames);
            case STOP -> this.myTableItemModel = new StopTableModel(this.mainFrame, this.hashtable, this.keys, this.columnNames);
            case STOP_TIME -> this.myTableItemModel = new StopTimeTableModel(this.mainFrame, this.hashtable, this.keys, this.columnNames);
            case TRIP -> this.myTableItemModel = new TripTableModel(this.mainFrame, this.hashtable, this.keys, this.columnNames);
            default -> this.myTableItemModel = new AgencyTableModel(this.mainFrame, this.hashtable, this.keys, this.columnNames);
        }

        this.setLayout(null);

        this.createTableSection();
        this.createSearchSection();
        this.createButtonsSection();
        this.createAddSectionAndEditCells();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.searchButton) {
            this.tableRowSorter.setRowFilter(new SearchTableFilter(this.searchField.getText(), this.searchCheckBoxes));
        } else if (e.getSource() == this.addButton) {
            this.addNewObject();
            this.myTableItemModel.fireTableDataChanged();
        }
        else if (e.getSource() == this.removeButton) {
            int confirmRemove = JOptionPane.showConfirmDialog(null, "Are you sure to delete this item?", "Remove Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmRemove == 0) {
                int[] selectedRows = this.table.getSelectedRows();
                ArrayList<String> selectedKeys = new ArrayList<>();
                for (int selectedRow : selectedRows) {
                    selectedKeys.add(this.keys.get(this.table.convertRowIndexToModel(selectedRow)));
                }
                boolean allRowsAreAbleToRemove = true;
                for (int selectedRow : selectedRows) {
                    if (!this.checkRemoveAction(this.table.convertRowIndexToModel(selectedRow))) {
                        allRowsAreAbleToRemove = false;
                    }
                }
                if (allRowsAreAbleToRemove) {
                    for (int selectedRow : selectedRows) {
                        this.removeObject(this.table.convertRowIndexToModel(selectedRow));
                    }


                    int numOfDeletedRows = 0;
                    for (int i = 0; i < this.keys.size(); i++) {
                        if (numOfDeletedRows == selectedKeys.size()) {
                            break;
                        }
                        for (String selectedKey : selectedKeys) {
                            if (this.keys.get(i).equals(selectedKey)) {
                                this.keys.remove(i);
                                numOfDeletedRows++;
                            }
                        }
                    }
                    this.updateTable();
                    this.myTableItemModel.fireTableDataChanged();
                    JOptionPane.showMessageDialog(null, "Successful removal", "Successful removal", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Selected records can not be removed because of constrains. Check them first!", "Error with removal", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource() == this.detailButton) {
            Hashtable<String, IGTFSObject> detailPanelHashTable = new Hashtable<>();
            IGTFSObject igtfsObject = this.hashtable.get(this.keys.get(this.table.convertRowIndexToModel(this.table.getSelectedRow())));

            switch (this.gtfsObjectType) {
                case TRIP -> {
                    Hashtable<String, IGTFSObject> stopTimesHashTable = this.mainFrame.getDataLoader().getAllStopTimes();
                    Hashtable<String, IGTFSObject> stopHashTable = this.mainFrame.getDataLoader().getAllStops();
                    ((Trip)igtfsObject).createSpecialStopHashTable(stopTimesHashTable, stopHashTable);
                    detailPanelHashTable = ((Trip)igtfsObject).getSpecialStopHashTable();
                }
                case ROUTE -> {
                    Hashtable<String, IGTFSObject> tripHashTable = this.mainFrame.getDataLoader().getAllTrips();
                    ArrayList<String> tripKeys = new ArrayList<>(tripHashTable.keySet());

                    String route_routeId = ((Route)igtfsObject).getRoute_id();

                    for (String tripKey : tripKeys) {
                        Trip trip = (Trip) tripHashTable.get(tripKey);
                        String trip_routeId = trip.getRoute_id();

                        if (trip_routeId.equals(route_routeId)) {

                            detailPanelHashTable.put(trip.getKey(), trip);
                        }

                    }
                }
                default -> {
                    Hashtable<String, IGTFSObject> stopTimeHashTable = this.mainFrame.getDataLoader().getAllStopTimes();
                    ArrayList<String> stopTimeKeys = new ArrayList<>(stopTimeHashTable.keySet());

                    Hashtable<String, IGTFSObject> tripHashTable = this.mainFrame.getDataLoader().getAllTrips();
                    ArrayList<String> tripKeys = new ArrayList<>(tripHashTable.keySet());

                    String stop_stopId = ((Stop)igtfsObject).getStop_id();

                    for (String stopTimeKey : stopTimeKeys) {
                        StopTime stopTime = (StopTime) stopTimeHashTable.get(stopTimeKey);
                        String stopTime_stopId = stopTime.getStop_id();

                        if (stopTime_stopId.equals(stop_stopId)) {
                            for (String tripKey : tripKeys) {
                                Trip trip = (Trip) tripHashTable.get(tripKey);
                                String trip_tripId = trip.getTrip_id();
                                if (trip_tripId.equals(stopTime.getTrip_id())) {
                                    detailPanelHashTable.put(trip.getKey(), trip);
                                }

                            }

                        }

                    }
                }
            }

            this.mainFrame.createDetailPanel(detailPanelHashTable, this.gtfsObjectType, igtfsObject);
            CardLayout cardLayout = (CardLayout) this.contentPanel.getContentPanel().getLayout();
            cardLayout.show(this.contentPanel.getContentPanel(), "detailPanel");
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (this instanceof TripTablePanel || this instanceof StopTablePanel || this instanceof RouteTablePanel) {
            this.detailButton.setVisible(true);
        }
        this.removeButton.setVisible(true);
    }

    private void createTableSection() {
        this.tableRowSorter = new TableRowSorter<>(this.myTableItemModel);
        this.table = new JTable(myTableItemModel);
        this.table.setRowSorter(tableRowSorter);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.table.setRowHeight(20);
        this.table.getSelectionModel().addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 900, 650);

        this.add(scrollPane);

    }

    private void createSearchSection() {
        JLabel searchLabel = new JLabel("Search");
        searchLabel.setBounds(950, 50, 50, 30);

        this.searchField = new JTextField();
        this.searchField.setBounds(1000, 50, 350, 30);

        this.searchButton = new JButton("Search");
        this.searchButton.setFocusable(false);
        this.searchButton.addActionListener(this);
        this.searchButton.setBounds(1000, 90, 100, 30);

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
            if (i >= this.columnNames.length / 2) {
                x = 1270;
                this.searchCheckBoxes[i].setBounds(x, y2, 150, 20);
                y2 += 30;
            } else {
                this.searchCheckBoxes[i].setBounds(x, y1, 150, 20);
                y1 += 30;
            }

            this.add(this.searchCheckBoxes[i]);
        }
    }

    private void createButtonsSection() {
        this.removeButton = new JButton("Remove");
        this.removeButton.setFocusable(false);
        this.removeButton.setVisible(false);
        this.removeButton.addActionListener(this);
        this.removeButton.setBounds(1000, 150, 100, 30);

        this.add(removeButton);

        if (this instanceof TripTablePanel || this instanceof StopTablePanel || this instanceof RouteTablePanel) {
            this.detailButton = new JButton("Detail");
            this.detailButton.setFocusable(false);
            this.detailButton.setVisible(false);
            this.detailButton.addActionListener(this);
            this.detailButton.setBounds(1000, 190, 100, 30);
            this.add(detailButton);
        }

    }

    private void createAddSectionAndEditCells() {


        this.addFormObjects = new ArrayList<>();

        int x = 1110;
        int y1 = 270;
        int y2 = 270;
        this.addLabels = new JLabel[this.columnNames.length];

        for (int i = 0; i < this.columnTypes.size(); i++) {
            TableColumn column = this.table.getColumnModel().getColumn(i);
            if (this.columnTypes.get(i) instanceof String || this.columnTypes.get(i) instanceof Integer || this.columnTypes.get(i) instanceof Double || this.columnTypes.get(i) instanceof Float) {
                this.addFormObjects.add(new JTextField());
                column.setCellEditor(new DefaultCellEditor(new JTextField()));
            } else if (this.columnTypes.get(i) instanceof LocalDate) {
                this.addFormObjects.add(new DatePicker());
                DateTableEditor dateTableEditor = new DateTableEditor(false, true, true);
                dateTableEditor.clickCountToEdit = 2;
                dateTableEditor.getDatePicker().getComponentToggleCalendarButton().setPreferredSize(new Dimension(20, 15));
                dateTableEditor.getDatePicker().getComponentDateTextField().setPreferredSize(new Dimension(2, 15));
                column.setCellEditor(dateTableEditor);
            } else if (this.columnTypes.get(i) instanceof LocalTime) {
                this.addFormObjects.add(new TimePicker());
                TimeTableEditor timeTableEditor = new TimeTableEditor(false, true, true);
                timeTableEditor.clickCountToEdit = 2;
                timeTableEditor.getTimePicker().getComponentToggleTimeMenuButton().setPreferredSize(new Dimension(20, 15));
                timeTableEditor.getTimePicker().getComponentTimeTextField().setPreferredSize(new Dimension(2, 15));
                column.setCellEditor(timeTableEditor);
            } else {
                if (this.columnTypes.get(i) instanceof DayServiceAvailability) {
                    this.addFormObjects.add(new JComboBox<>(DayServiceAvailability.values()));
                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JComboBox<>(DayServiceAvailability.values()));
                    defaultCellEditor.setClickCountToStart(2);
                    column.setCellEditor(defaultCellEditor);
                } else if (this.columnTypes.get(i) instanceof ExceptionType) {
                    this.addFormObjects.add(new JComboBox<>(ExceptionType.values()));
                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JComboBox<>(ExceptionType.values()));
                    defaultCellEditor.setClickCountToStart(2);
                    column.setCellEditor(defaultCellEditor);
                } else if (this.columnTypes.get(i) instanceof RouteType) {
                    this.addFormObjects.add(new JComboBox<>(RouteType.values()));
                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JComboBox<>(RouteType.values()));
                    defaultCellEditor.setClickCountToStart(2);
                    column.setCellEditor(defaultCellEditor);
                } else if (this.columnTypes.get(i) instanceof StopLocationType) {
                    this.addFormObjects.add(new JComboBox<>(StopLocationType.values()));
                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JComboBox<>(StopLocationType.values()));
                    defaultCellEditor.setClickCountToStart(2);
                    column.setCellEditor(defaultCellEditor);
                } else if (this.columnTypes.get(i) instanceof StopWheelchairBoarding) {
                    this.addFormObjects.add(new JComboBox<>(StopWheelchairBoarding.values()));
                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JComboBox<>(StopWheelchairBoarding.values()));
                    defaultCellEditor.setClickCountToStart(2);
                    column.setCellEditor(defaultCellEditor);
                } else if (this.columnTypes.get(i) instanceof DropOffType) {
                    this.addFormObjects.add(new JComboBox<>(DropOffType.values()));
                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JComboBox<>(DropOffType.values()));
                    defaultCellEditor.setClickCountToStart(2);
                    column.setCellEditor(defaultCellEditor);
                } else if (this.columnTypes.get(i) instanceof PickupType) {
                    this.addFormObjects.add(new JComboBox<>(PickupType.values()));
                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JComboBox<>(PickupType.values()));
                    defaultCellEditor.setClickCountToStart(2);
                    column.setCellEditor(defaultCellEditor);
                } else if (this.columnTypes.get(i) instanceof TimePoint) {
                    this.addFormObjects.add(new JComboBox<>(TimePoint.values()));
                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JComboBox<>(TimePoint.values()));
                    defaultCellEditor.setClickCountToStart(2);
                    column.setCellEditor(defaultCellEditor);
                } else if (this.columnTypes.get(i) instanceof TripDirectionID) {
                    this.addFormObjects.add(new JComboBox<>(TripDirectionID.values()));
                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JComboBox<>(TripDirectionID.values()));
                    defaultCellEditor.setClickCountToStart(2);
                    column.setCellEditor(defaultCellEditor);
                } else if (this.columnTypes.get(i) instanceof TripWheelchairAccessible) {
                    this.addFormObjects.add(new JComboBox<>(TripWheelchairAccessible.values()));
                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JComboBox<>(TripWheelchairAccessible.values()));
                    defaultCellEditor.setClickCountToStart(2);
                    column.setCellEditor(defaultCellEditor);
                }
            }

            this.addLabels[i] = new JLabel(this.columnNames[i]);

            if (i >= this.columnNames.length / 2) {
                x = 1280;
                this.addLabels[i].setBounds(x, y2, 160, 30);
                y2 += 30;
                this.addFormObjects.get(i).setBounds(x, y2, 160, 30);
                y2 += 30;
            } else {
                this.addLabels[i].setBounds(x, y1, 160, 30);
                y1 += 30;
                this.addFormObjects.get(i).setBounds(x, y1, 160, 30);

                y1 += 30;
            }

            this.add(this.addLabels[i]);
            this.add(this.addFormObjects.get(i));
        }

        this.addButton = new JButton("Add");
        this.addButton.setFocusable(false);
        this.addButton.addActionListener(this);
        this.addButton.setBounds(1000, 300, 100, 30);
        this.add(addButton);

    }

    public AdminPanel getContentPanel() {
        return this.contentPanel;
    }

    public boolean tableContainsValueAt(String findingIdValue, int columnIndex) {
        for (int i = 0; i < this.table.getModel().getRowCount(); i++) {
            if (this.table.getModel().getValueAt(i, columnIndex).equals(findingIdValue)) {
                return true;
            }
        }
        return false;
    }

    protected void removeObject(int keyIndex) {
        if (this.checkRemoveAction(keyIndex)) {
            String key = this.keys.get(keyIndex);
            this.hashtable.remove(key);
        }
    }

    abstract boolean checkRemoveAction(int keyIndex);

    abstract boolean checkAddInputs();

    abstract void addNewObject();

    abstract void updateTable();

    protected void setRedBorder(JComponent jComponent)
    {
        jComponent.setBorder(BorderFactory.createEtchedBorder(Color.red,Color.RED));
    }

    protected void setDefaultBorder(JComponent jComponent)
    {
        jComponent.setBorder(UIManager.getBorder("TextField.border"));
    }

    protected boolean isNumeric(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        try {
            Double.parseDouble(String.valueOf(obj));
            return true;
        }catch (NumberFormatException ex)
        {
            return false;
        }
    }
}
