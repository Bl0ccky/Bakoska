package GUI;

import App.DataLoader;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private DataLoader dataLoader;
    private final JPanel contentPanel;

    public MainFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1550,950));
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
        AdminPanel adminPanel = new AdminPanel(this.contentPanel, this);
        this.contentPanel.add(adminPanel, "adminPanel");
        //this.add(panelContent);
        //this.pack();
        //this.setLocationRelativeTo(null);
        //this.setVisible(true);
    }

    public void createDetailPanel()
    {
        DetailPanel detailPanel = new DetailPanel(this.contentPanel, this);
        this.contentPanel.add(detailPanel, "detailPanel");
        //this.add(panelContent);

    }
}
