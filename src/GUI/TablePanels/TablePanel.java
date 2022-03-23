package GUI.TablePanels;

import GUI.MainFrame;
import GUI.TableModels.*;
import TextFiles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class TablePanel extends JPanel implements ActionListener {
    protected final JPanel contentPanel;
    protected final MainFrame mainFrame;
    protected final Hashtable<String, IObject> hashtable;
    protected final String[] columnNames;
    protected final ArrayList<String> keys;
    protected final MyTableItemModel myTableItemModel;

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

        JTable table = new JTable(myTableItemModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton removeButton = new JButton("Remove");


        this.add(scrollPane);
        this.add(addButton);
        this.add(editButton);
        this.add(removeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String changeToPanel = "tablePanel";
        CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
        cardLayout.show(this.contentPanel, changeToPanel);

    }


}
