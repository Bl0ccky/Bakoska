package GUI;

import GUI.TablePanels.TablePanel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailPanel extends JPanel implements ActionListener
{
    private final JPanel contentPanel;
    private final JFrame mainFrame;
    public DetailPanel(JPanel panel, MainFrame mainFrame)
    {
        this.contentPanel = panel;
        this.mainFrame = mainFrame;
        this.setLayout(null);
        //JMapViewer map = new JMapViewer();
        //map.setDisplayPosition(new Coordinate(51.5072, -0.1275), 12);
        //map.setBounds(0,0,500,500);

        //this.add();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
