package GUI.TableModels;

import GTFSFiles.IGTFSObject;
import GUI.MainFrame;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class MyTableItemModel extends AbstractTableModel {

    protected final Hashtable<String, IGTFSObject> hashtable;
    protected final ArrayList<String> keys;
    protected final String[] columnNames;
    protected final MainFrame mainFrame;

    public MyTableItemModel(MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames)
    {
        this.mainFrame = mainFrame;
        this.hashtable = hashtable;
        this.keys = keys;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return this.hashtable.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return this.columnNames[col];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

    protected boolean isNumeric(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        try {
            Double.parseDouble(String.valueOf(obj));
            return true;
        }catch (NumberFormatException ex)
        {
            return false;
        }
    }

}
