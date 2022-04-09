package GUI;

import App.DataLoader;
import GUI.DetailPanels.DetailPanel;
import GUI.DetailPanels.RouteDetailPanel;
import GUI.DetailPanels.StopDetailPanel;
import GUI.DetailPanels.TripDetailPanel;
import GTFSFiles.GTFSObjectType;
import GTFSFiles.IGTFSObject;

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

public class MainFrame extends JFrame implements ActionListener {
    private DataLoader dataLoader;
    private final JPanel contentPanel;
    private AdminPanel adminPanel;
    private final JMenuItem createNewFileItem;
    private final JMenuItem importItem;
    private final JMenu exportMenu;
    private final JMenuItem exitItem;
    private final JMenuItem exportGTFSItem;
    private final JMenuItem exportCSVItem;

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1550, 950));
        this.setLayout(new BorderLayout());
        this.setTitle("GTFS-Adminer");


        CardLayout cardLayout = new CardLayout();

        this.contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);

        MenuPanel menuPanel = new MenuPanel(contentPanel, this);


        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        this.createNewFileItem = new JMenuItem("New GTFS file");
        this.createNewFileItem.addActionListener(this);
        this.importItem = new JMenuItem("Import GTFS");
        this.importItem.addActionListener(this);
        this.exportGTFSItem = new JMenuItem("GTFS export");
        this.exportGTFSItem.addActionListener(this);
        this.exportCSVItem = new JMenuItem("CSV export");
        this.exportCSVItem.addActionListener(this);
        this.exportMenu = new JMenu("Export");
        //this.exportItem.addActionListener(this);
        this.exportMenu.setVisible(false);
        this.exportMenu.add(exportGTFSItem);
        this.exportMenu.add(exportCSVItem);
        this.exitItem = new JMenuItem("Exit");
        this.exitItem.addActionListener(this);

        fileMenu.add(this.createNewFileItem);
        fileMenu.add(this.importItem);
        fileMenu.add(this.exportMenu);
        fileMenu.add(this.exitItem);

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

    public void createAdminPanel() {
        this.adminPanel = new AdminPanel(this.contentPanel, this);
        this.contentPanel.add(this.adminPanel, "adminPanel");
    }

    public void createDetailPanel(Hashtable<String, IGTFSObject> hashtable, GTFSObjectType gtfsObjectType, IGTFSObject igtfsObject) {
        DetailPanel detailPanel;
        switch (gtfsObjectType) {
            case TRIP -> detailPanel = new TripDetailPanel(this.adminPanel.getTablePanel(gtfsObjectType), this, hashtable, gtfsObjectType, igtfsObject);
            case ROUTE -> detailPanel = new RouteDetailPanel(this.adminPanel.getTablePanel(gtfsObjectType), this, hashtable, gtfsObjectType, igtfsObject);
            default -> detailPanel = new StopDetailPanel(this.adminPanel.getTablePanel(gtfsObjectType), this, hashtable, gtfsObjectType, igtfsObject);
        }

        this.contentPanel.add(detailPanel, "detailPanel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.createNewFileItem) {
            //TODO odstranit duplicitu s menu panelom!!
            String filePath = this.getGTFSFilePath(1);
            if (filePath != null) {
                int fileCounter = 1;
                boolean isDirectoryEmpty = false;
                try {
                    isDirectoryEmpty = this.isDirectoryEmpty(Path.of(filePath));
                    if (!isDirectoryEmpty) {
                        Files.createDirectory(Path.of(filePath + "\\newGTFSDirectory_" + fileCounter));
                    }

                } catch (IOException ex) {
                    if (ex instanceof FileAlreadyExistsException) {
                        fileCounter = MainFrame.getFileCounter(filePath, "newGTFSDirectory_", fileCounter);
                        try {
                            Files.createDirectory(Path.of(filePath + "\\newGTFSDirectory_" + fileCounter));
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    } else if (ex instanceof NoSuchFileException) {
                        JOptionPane.showMessageDialog(null, "This folder path doesnt exist!", "Wrong file path", JOptionPane.ERROR_MESSAGE);
                        CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
                        cardLayout.show(this.contentPanel, "menuPanel");
                        return;
                    }
                }
                if (!isDirectoryEmpty) {
                    filePath += "\\newGTFSDirectory_" + fileCounter;
                }

                this.changeToAdminPanel(createNewGTFSFiles(filePath));

            }

        } else if (e.getSource() == this.importItem) {
            String filePath = this.getGTFSFilePath(2);
            if (filePath != null) {
                DataLoader dataLoader = new DataLoader(filePath);
                dataLoader.loadAllData();
                this.changeToAdminPanel(dataLoader);
                this.exportMenu.setVisible(true);
            }
        } else if (e.getSource() == this.exportGTFSItem) {
            String filePath = this.getGTFSFilePath(1);
            if (filePath != null) {
                int fileCounter = 1;
                boolean isDirectoryEmpty = false;
                try {
                    isDirectoryEmpty = this.isDirectoryEmpty(Path.of(filePath));
                    if (!isDirectoryEmpty) {
                        Files.createDirectory(Path.of(filePath + "\\exportedGTFSDirectory_" + fileCounter));
                    }
                } catch (IOException ex) {
                    if (ex instanceof FileAlreadyExistsException) {
                        fileCounter = getFileCounter(filePath, "exportedGTFSDirectory_", fileCounter);
                        try {
                            Files.createDirectory(Path.of(filePath + "\\exportedGTFSDirectory_" + fileCounter));
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    } else if (ex instanceof NoSuchFileException) {
                        JOptionPane.showMessageDialog(null, "This folder path doesnt exist!", "Wrong file path", JOptionPane.ERROR_MESSAGE);
                        CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
                        cardLayout.show(this.contentPanel, "menuPanel");
                        return;
                    }
                }
                if (!isDirectoryEmpty) {
                    filePath += "\\exportedGTFSDirectory_" + fileCounter;
                }

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
        } else if (e.getSource() == this.exportCSVItem) {
            String filePath = this.getGTFSFilePath(1);
            if (filePath != null) {
                int fileCounter = 1;
                boolean isDirectoryEmpty = false;
                try {
                    isDirectoryEmpty = this.isDirectoryEmpty(Path.of(filePath));
                    if (!isDirectoryEmpty) {
                        Files.createDirectory(Path.of(filePath + "\\exportedCSVDirectory_" + fileCounter));
                    }
                } catch (IOException ex) {
                    if (ex instanceof FileAlreadyExistsException) {

                        fileCounter = getFileCounter(filePath, "exportedCSVDirectory_", fileCounter);
                        try {
                            Files.createDirectory(Path.of(filePath + "\\exportedCSVDirectory_" + fileCounter));
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    } else if (ex instanceof NoSuchFileException) {
                        JOptionPane.showMessageDialog(null, "This folder path doesnt exist!", "Wrong file path", JOptionPane.ERROR_MESSAGE);
                        CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
                        cardLayout.show(this.contentPanel, "menuPanel");
                        return;
                    }
                }

                if (!isDirectoryEmpty) {
                    filePath += "\\exportedCSVDirectory_" + fileCounter;
                }

                this.dataLoader.setFilePath(filePath);
                this.dataLoader.writeStopsCSV();
                this.dataLoader.writeTripCSV();

                this.changeToAdminPanel(dataLoader);

            }

        } else if (e.getSource() == this.exitItem) {
            System.exit(0);
        }

    }

    static DataLoader createNewGTFSFiles(String filePath) {
        String[] fileNames = {"agency.txt", "calendar.txt", "calendar_dates.txt", "routes.txt", "stops.txt", "stop_times.txt", "trips.txt"};
        DataLoader dataLoader = new DataLoader(filePath);
        int counter = 0;
        for (GTFSObjectType gtfsObjectType : GTFSObjectType.values()) {
            String[] columnNames = dataLoader.createColumnNamesForNewFile(gtfsObjectType);
            dataLoader.createColumnTypesForNewFile(gtfsObjectType);
            Path path = Path.of(filePath + "\\" + fileNames[counter]);
            try {
                Files.createFile(path);
                FileWriter myWriter = new FileWriter(filePath + "\\" + fileNames[counter]);
                for (int i = 0; i < columnNames.length; i++) {
                    myWriter.write(columnNames[i]);
                    if (i != columnNames.length - 1) {
                        myWriter.write(",");
                    }

                }
                myWriter.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            counter++;
        }
        return dataLoader;
    }

    static int getFileCounter(String filePath, String subString, int fileCounter) {
        File fileDirectory = new File(filePath);

        String[] directories = fileDirectory.list((dir, name) -> new File(dir, name).isDirectory());
        for (String directory : Objects.requireNonNull(directories)) {
            if (directory.contains(subString)) {
                String[] foundedFileName = directory.split("_");
                if (Integer.parseInt(foundedFileName[1]) > fileCounter) {
                    fileCounter = Integer.parseInt(foundedFileName[1]);
                }
            }

        }
        fileCounter++;
        return fileCounter;
    }

    public String getGTFSFilePath(int option) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int response;
        //EXPORT
        if (option == 1) {
            fileChooser.setDialogTitle("Create new GTFS");
            response = fileChooser.showSaveDialog(this);
        }
        //IMPORT
        else {
            fileChooser.setDialogTitle("Import GTFS");
            response = fileChooser.showOpenDialog(this);
        }

        if (response == JFileChooser.APPROVE_OPTION) {
            //if(fileChooser.getSelectedFile())
            return fileChooser.getSelectedFile().getPath();
        } else {
            return null;
        }
    }

    public void changeToAdminPanel(DataLoader dataLoader) {
        this.setDataLoader(dataLoader);
        this.createAdminPanel();
        CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
        cardLayout.show(this.contentPanel, "adminPanel");
        this.exportMenu.setVisible(true);
    }

    public boolean isDirectoryEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)) {
                return !directory.iterator().hasNext();
            }
        }

        return false;
    }

    public AdminPanel getAdminPanel() {
        return this.adminPanel;
    }
}
