package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{

    public MainFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));
        this.setLayout(new BorderLayout());
        this.setTitle("GTFS-Adminer");

        CardLayout cardLayout = new CardLayout();

        JPanel panelContent = new JPanel();
        panelContent.setLayout(cardLayout);

        MenuPanel menuPanel = new MenuPanel(panelContent);
        //TablePanel tablePanel = new TablePanel(panelContent, this);

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


        panelContent.add(menuPanel, "menuPanel");
        //panelContent.add(tablePanel, "tablePanel");

        this.add(panelContent);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


}
