package GUI.TableModels;

import TextFiles.IObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class MyTableItemModel extends AbstractTableModel {

    protected final Hashtable<String, IObject> hashtable;
    protected final ArrayList<String> keys;
    protected final String[] columnNames;

    public MyTableItemModel(Hashtable<String, IObject> hashtable, ArrayList<String> keys, String[] columnNames)
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


}
