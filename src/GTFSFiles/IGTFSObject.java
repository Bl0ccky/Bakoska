package GTFSFiles;

import java.util.ArrayList;

public interface IGTFSObject
{
    void loadData(String[] attributes, String[] columnNames);
    ArrayList<Object> getColumnTypes();
    ArrayList<Object> getAttributesForExportGTFS();
    String getKey();
}
