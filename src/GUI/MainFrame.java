package GUI;

import App.DataLoader;
import GUI.DetailPanels.DetailPanel;
import GUI.DetailPanels.RouteDetailPanel;
import GUI.DetailPanels.StopDetailPanel;
import GUI.DetailPanels.TripDetailPanel;
import TextFiles.GTFSObjectType;
import TextFiles.IGTFSObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Hashtable;
import java.util.Objects;

public class MainFrame extends JFrame implements ActionListener
{
    private DataLoader dataLoader;
    private final JPanel contentPanel;
    private AdminPanel adminPanel;
    private final JMenuItem importItem;
    private final JMenuItem exportItem;
    private final JMenuItem exitItem;

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

        this.importItem = new JMenuItem("Import GTFS");
        this.importItem.addActionListener(this);
        this.exportItem = new JMenuItem("Export");
        this.exportItem.addActionListener(this);
        this.exportItem.setVisible(false);
        this.exitItem = new JMenuItem("Exit");
        this.exitItem.addActionListener(this);

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
    }

    public void createDetailPanel(Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject)
    {
        DetailPanel detailPanel;
        switch (gtfsObjectType)
        {
            case TRIP -> detailPanel = new TripDetailPanel(this.adminPanel.getTablePanel(gtfsObjectType), this, hashtable, gtfsObjectType, igtfsObject);
            case ROUTE -> detailPanel = new RouteDetailPanel(this.adminPanel.getTablePanel(gtfsObjectType), this, hashtable, gtfsObjectType, igtfsObject);
            default -> detailPanel = new StopDetailPanel(this.adminPanel.getTablePanel(gtfsObjectType), this, hashtable, gtfsObjectType, igtfsObject);
        }

        this.contentPanel.add(detailPanel, "detailPanel");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.importItem)
        {
            String filePath = this.getGTFSFilePath(2);
            if(filePath != null)
            {
                DataLoader dataLoader = new DataLoader(filePath);
                dataLoader.loadAllData();
                this.changeToAdminPanel(dataLoader);
                this.exportItem.setVisible(true);
            }
        }
        else if(e.getSource() == this.exportItem)
        {
            String filePath = this.getGTFSFilePath(1);
            if(filePath != null)
            {
                int fileCounter = 1;
                try {
                    if(!this.isDirectoryEmpty(Path.of(filePath)))
                    {
                        Files.createDirectory(Path.of(filePath + "\\exportedGTFSDirectory_"+fileCounter));
                    }
                } catch (IOException ex) {
                    if(ex instanceof FileAlreadyExistsException)
                    {

                        File fileDirectory = new File(filePath);
                        String[] directories = fileDirectory.list((dir, name) -> new File(dir, name).isDirectory());
                        for (String directory : Objects.requireNonNull(directories))
                        {
                            String[] foundedFileName = directory.split("_");
                            if (Integer.parseInt(foundedFileName[1]) > fileCounter) {
                                fileCounter = Integer.parseInt(foundedFileName[1]);
                            }
                        }
                        fileCounter++;
                        try {
                            Files.createDirectory(Path.of(filePath + "\\exportedGTFSDirectory_"+fileCounter));
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    }
                    else if(ex instanceof NoSuchFileException)
                    {
                        JOptionPane.showMessageDialog(null, "This folder path doesnt exist!", "Wrong file path", JOptionPane.ERROR_MESSAGE);
                        CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
                        cardLayout.show(this.contentPanel, "menuPanel");
                        return;
                    }
                }
                filePath += "\\exportedGTFSDirectory_"+fileCounter;

                this.dataLoader.setFilePath(filePath);

                this.dataLoader.writeAgency();
                this.dataLoader.writeCalendars();
                this.dataLoader.writeCalendarDates();
                this.dataLoader.writeRoutes();
                this.dataLoader.writeStops();
                this.dataLoader.writeStopTimes();
                this.dataLoader.writeTrips();

                this.changeToAdminPanel(dataLoader);

            }
        }
        else if(e.getSource() == this.exitItem)
        {
            System.exit(0);
        }

    }

    public String getGTFSFilePath(int option)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int response;
        //EXPORT
        if(option == 1)
        {
            fileChooser.setDialogTitle("Create new GTFS");
            response = fileChooser.showSaveDialog(this);
        }
        //IMPORT
        else
        {
            fileChooser.setDialogTitle("Import GTFS");
            response = fileChooser.showOpenDialog(this);
        }

        if(response == JFileChooser.APPROVE_OPTION)
        {
            //if(fileChooser.getSelectedFile())
            return fileChooser.getSelectedFile().getPath();
        }
        else
        {
            return null;
        }
    }

    public void changeToAdminPanel(DataLoader dataLoader)
    {
        this.setDataLoader(dataLoader);
        this.createAdminPanel();
        CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
        cardLayout.show(this.contentPanel, "adminPanel");
        this.exportItem.setVisible(true);
    }

    public boolean isDirectoryEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)) {
                return !directory.iterator().hasNext();
            }
        }

        return false;
    }
}
