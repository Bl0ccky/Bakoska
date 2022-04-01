package GUI;

import App.DataLoader;
import GUI.DetailPanels.DetailPanel;
import GUI.DetailPanels.RouteDetailPanel;
import GUI.DetailPanels.StopDetailPanel;
import GUI.DetailPanels.TripDetailPanel;
import GUI.TableModels.*;
import TextFiles.GTFSObjectType;
import TextFiles.IGTFSObject;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class MainFrame extends JFrame
{
    private DataLoader dataLoader;
    private final JPanel contentPanel;
    private AdminPanel adminPanel;

    public MainFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1700,950));
        this.setLayout(new BorderLayout());
        this.setTitle("GTFS-Adminer");


        CardLayout cardLayout = new CardLayout();

        this.contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);

        MenuPanel menuPanel = new MenuPanel(contentPanel, this);


        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem importItem = new JMenuItem("Import GTFS");
        JMenuItem exportItem = new JMenuItem("Export");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(importItem);
        fileMenu.add(exportItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);

        this.contentPanel.add(menuPanel, "menuPanel");

        this.add(contentPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    public DataLoader getDataLoader() {
        return dataLoader;
    }

    public void setDataLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void createAdminPanel()
    {
        this.adminPanel = new AdminPanel(this.contentPanel, this);
        this.contentPanel.add(this.adminPanel, "adminPanel");
        //this.add(panelContent);
        //this.pack();
        //this.setLocationRelativeTo(null);
        //this.setVisible(true);
    }

    public void createDetailPanel(Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType)
    {
        DetailPanel detailPanel;
        switch (gtfsObjectType)
        {
            case TRIP -> detailPanel = new TripDetailPanel(this.adminPanel.getTablePanel(gtfsObjectType), this, hashtable, gtfsObjectType);
            case ROUTE -> detailPanel = new RouteDetailPanel(this.adminPanel.getTablePanel(gtfsObjectType), this, hashtable, gtfsObjectType);
            default -> detailPanel = new StopDetailPanel(this.adminPanel.getTablePanel(gtfsObjectType), this, hashtable, gtfsObjectType);
        }

        this.contentPanel.add(detailPanel, "detailPanel");
        //this.add(panelContent);

    }
}
