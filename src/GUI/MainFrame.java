package GUI;

import App.DataLoader;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private DataLoader dataLoader;
    private final JPanel panelContent;

    public MainFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1550,950));
        this.setLayout(new BorderLayout());
        this.setTitle("GTFS-Adminer");


        CardLayout cardLayout = new CardLayout();

        this.panelContent = new JPanel();
        panelContent.setLayout(cardLayout);

        MenuPanel menuPanel = new MenuPanel(panelContent, this);


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

        this.panelContent.add(menuPanel, "menuPanel");

        this.add(panelContent);
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
        AdminPanel adminPanel = new AdminPanel(this.panelContent, this);
        this.panelContent.add(adminPanel, "adminPanel");
        //this.add(panelContent);
        //this.pack();
        //this.setLocationRelativeTo(null);
        //this.setVisible(true);
    }

    public void createDetailPanel()
    {
        DetailPanel detailPanel = new DetailPanel();
        this.panelContent.add(detailPanel, "detailPanel");
        //this.add(panelContent);

    }
}
