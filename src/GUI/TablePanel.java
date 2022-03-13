package GUI;

import TextFiles.IObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Map;

public class TablePanel extends JPanel implements ActionListener
{
    private final JPanel contentPanel;

    public TablePanel(JPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable)
    {
        DefaultTableModel dtm = new DefaultTableModel();
        JTable table = new JTable(dtm);
        for(Map.Entry<String, IObject> entry: hashtable.entrySet())
        {
            dtm.addRow(new Object[] {entry.getKey(), entry.getValue()});
        }

        this.contentPanel = panel;
        this.add(table);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String changeToPanel = "tablePanel";
        CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
        cardLayout.show(this.contentPanel, changeToPanel);

    }





}
