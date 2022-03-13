import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuView extends JFrame implements ActionListener {
    JPanel mainPanel;
    //JSplitPane splitPane;
    JButton btnSelectFile;
    JButton btnExit;
    JLabel lblSelectFile;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem importItem;
    JMenuItem exitItem;

    public MenuView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));
        this.setLayout(new BorderLayout());
        this.setTitle("GTFS-Adminer");
/*
        this.menuBar = new JMenuBar();

        this.fileMenu = new JMenu("File");

        this.importItem = new JMenuItem("Import GTFS");
        this.exitItem = new JMenuItem("Exit");

        this.fileMenu.add(this.importItem);
        this.fileMenu.add(this.exitItem);

        this.menuBar.add(this.fileMenu);

        this.setJMenuBar(this.menuBar);

 */
        this.lblSelectFile = new JLabel("Vyber cestu k GTFS súborom");
        this.lblSelectFile.setFont(new Font("Sans Sheriff", Font.PLAIN,20));

        this.btnSelectFile = new JButton("Vyber cestu");
        this.btnSelectFile.setFocusable(false);
        this.btnSelectFile.addActionListener(this);

        this.btnExit = new JButton("Exit");
        this.btnExit.setFocusable(false);
        this.btnExit.addActionListener(this);

        this.mainPanel = new JPanel();
        this.mainPanel.setBorder(new EmptyBorder(10,10,10,10));
        this.mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        this.mainPanel.add(this.lblSelectFile, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,0,10,0);

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.add(this.btnSelectFile, gbc);
        buttons.add(this.btnExit, gbc);

        gbc.weighty = 1;
        this.mainPanel.add(buttons, gbc);

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
        this.add(this.mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        /*if (e.getSource() == this.btnSelectFile) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Ahoj svet");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            if (fileChooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                DataLoader dataLoader = new DataLoader(filePath);
                System.out.println("Vyborne podarilo sa nacitat vsetky data");
            } else {
                JLabel wrongPath = new JLabel();
                wrongPath.setText("Vybral si zlý priečinok!");
            }

        }*/

    }
}
