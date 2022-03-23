package GUI.TablePanels;

import GUI.MainFrame;
import GUI.TableModels.*;
import TextFiles.*;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class TablePanel extends JPanel implements ActionListener{
    protected final JPanel contentPanel;
    protected final MainFrame mainFrame;
    protected final Hashtable<String, IObject> hashtable;
    protected final String[] columnNames;
    protected final ArrayList<String> keys;
    protected final MyTableItemModel myTableItemModel;
    private final JTextField searchField;
    private final JButton searchButton;
    private final TableRowSorter<MyTableItemModel> tableRowSorter;

    public TablePanel(JPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType) {
        this.keys = new ArrayList<>(hashtable.keySet());
        this.contentPanel = panel;
        this.mainFrame = mainFrame;
        this.hashtable = hashtable;
        this.columnNames = mainFrame.getDataLoader().getHashTableColumns(objectType);
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



        this.tableRowSorter = new TableRowSorter<>(this.myTableItemModel);
        JTable table = new JTable(myTableItemModel);
        table.setRowSorter(tableRowSorter);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 700));


        JLabel searchLabel = new JLabel("Search");
        this.searchField = new JTextField();
        this.searchField.setPreferredSize(new Dimension(100, 30));
        this.searchButton = new JButton("Search");
        this.searchButton.addActionListener(this);

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton removeButton = new JButton("Remove");

        this.add(scrollPane);
        this.add(searchLabel);
        this.add(searchField);
        this.add(searchButton);
        this.add(addButton);
        this.add(editButton);
        this.add(removeButton);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.searchButton)
        {
            this.tableRowSorter.setRowFilter(new TableRowFilter(this.searchField.getText()));
        }


    }



}
