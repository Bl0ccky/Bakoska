package GUI;

import App.DataLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPanel extends JPanel implements ActionListener{
    private final JPanel contentPanel;
    private final JButton btnSelectFile;
    private final JButton btnExit;

    public MenuPanel(JPanel panel) {

        this.contentPanel = panel;
        JLabel lblSelectFile = new JLabel("Vyber cestu k GTFS súborom");
        lblSelectFile.setFont(new Font("Sans Sheriff", Font.PLAIN,20));

        this.btnSelectFile = new JButton("Vyber cestu");
        this.btnSelectFile.setFocusable(false);
        this.btnSelectFile.addActionListener(this);

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
        buttons.add(this.btnSelectFile, gbc);
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
        if (e.getSource() == this.btnSelectFile) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                DataLoader dataLoader = new DataLoader(filePath);
                dataLoader.writeAllAgency();
                System.out.println("Vyborne podarilo sa nacitat vsetky data");
                String changeToPanel = "tablePanel";
                CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
                cardLayout.show(this.contentPanel, changeToPanel);

            } else {
                JLabel wrongPath = new JLabel();
                wrongPath.setText("Vybral si zlý priečinok!");
            }

        }
        else if(e.getSource() == this.btnExit)
        {
            System.exit(0);
        }

    }
}
