package GUI.TableModels;

import TextFiles.IGTFSObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class MyTableItemModel extends AbstractTableModel {

    protected final Hashtable<String, IGTFSObject> hashtable;
    protected final ArrayList<String> keys;
    protected final String[] columnNames;

    public MyTableItemModel(Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames)
    {
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

}
