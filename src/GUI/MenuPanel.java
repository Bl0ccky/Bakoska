package GUI;

import App.DataLoader;
import GTFSFiles.GTFSObjectType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;


public class MenuPanel extends JPanel implements ActionListener{
    private final JPanel contentPanel;
    private final JButton btnImportFile;
    private final JButton btnCreateNewFile;
    private final JButton btnExit;
    private final MainFrame mainFrame;

    public MenuPanel(JPanel panel, MainFrame mainFrame) {

        this.contentPanel = panel;
        this.mainFrame = mainFrame;
        JLabel lblSelectFile = new JLabel("Choose your action");
        lblSelectFile.setFont(new Font("Sans Sheriff", Font.PLAIN,20));

        this.btnImportFile = new JButton("Import gtfs file");
        this.btnImportFile.setFocusable(false);
        this.btnImportFile.addActionListener(this);

        this.btnCreateNewFile = new JButton("Create new gtfs file");
        this.btnCreateNewFile.setFocusable(false);
        this.btnCreateNewFile.addActionListener(this);

        this.btnExit = new JButton("Exit");
        this.btnExit.setFocusable(false);
        this.btnExit.addActionListener(this);

        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(lblSelectFile, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,0,10,0);

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.add(this.btnCreateNewFile, gbc);
        buttons.add(this.btnImportFile, gbc);
        buttons.add(this.btnExit, gbc);

        gbc.weighty = 1;
        this.add(buttons, gbc);

        //this.btnPanel = new JPanel(new BorderLayout());
        //this.splitPane = new JSplitPane();


        //this.mainPanel.add(this.lblSelectFile, Component.CENTER_ALIGNMENT);
        //this.mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
        //this.labelPanel.setBackground(Color.blue);
        //this.mainPanel.add(this.btnSelectFile, Component.CENTER_ALIGNMENT);
        //this.btnPanel.setBackground(Color.GREEN);
/*
        this.splitPane.setSize(500,500);

        this.splitPane.setDividerSize(0);
        this.splitPane.setDividerLocation(0.5);
        this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.splitPane.setTopComponent(this.labelPanel);
        this.splitPane.setBottomComponent(this.btnPanel);
*/

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnImportFile)
        {
            String filePath = this.mainFrame.getGTFSFilePath(2);
            if(filePath != null)
            {
                String[] fileNames = {"agency.txt", "calendar.txt", "calendar_dates.txt", "routes.txt", "stops.txt", "stop_times.txt", "trips.txt"};
                boolean allFilesExist = true;
                for (String filename: fileNames)
                {
                    if(!new File(filePath+"\\"+filename).exists())
                    {
                        allFilesExist = false;
                        break;
                    }
                }
                if(allFilesExist)
                {
                    DataLoader dataLoader = new DataLoader(filePath);
                    dataLoader.loadAllData();
                    this.mainFrame.changeToAdminPanel(dataLoader);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "This folder doesnt contains all required files of GTFS", "Missing files", JOptionPane.ERROR_MESSAGE);
                    CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
                    cardLayout.show(this.contentPanel, "menuPanel");

                }
            }

        }
        else if(e.getSource() == this.btnCreateNewFile)
        {
            String filePath = this.mainFrame.getGTFSFilePath(1);
            if(filePath != null)
            {
                int fileCounter = 1;
                try
                {
                    if(!this.mainFrame.isDirectoryEmpty(Path.of(filePath)))
                    {
                        Files.createDirectory(Path.of(filePath + "\\newGTFSDirectory_"+fileCounter));
                    }

                } catch (IOException ex) {
                    if(ex instanceof FileAlreadyExistsException)
                    {
                        fileCounter = MainFrame.getFileCounter(filePath, "newGTFSDirectory_", fileCounter);
                        try {
                            Files.createDirectory(Path.of(filePath + "\\newGTFSDirectory_"+fileCounter));
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                        filePath += "\\newGTFSDirectory_"+fileCounter;
                    }
                    else if(ex instanceof NoSuchFileException)
                    {
                        JOptionPane.showMessageDialog(null, "This folder path doesnt exist!", "Wrong file path", JOptionPane.ERROR_MESSAGE);
                        CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
                        cardLayout.show(this.contentPanel, "menuPanel");
                        return;
                    }
                }

                this.mainFrame.changeToAdminPanel(MainFrame.createNewGTFSFiles(filePath));

            }

        }
        else if(e.getSource() == this.btnExit)
        {
            System.exit(0);
        }

    }


}
