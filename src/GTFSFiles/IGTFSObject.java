package GTFSFiles;

import java.util.ArrayList;

public interface IGTFSObject
{
    void loadData(String[] attributes);
    ArrayList<Object> getColumnTypes();
    ArrayList<Object> getAttributesForExportGTFS();
    String getKey();
}
