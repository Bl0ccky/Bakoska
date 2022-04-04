package TextFiles;

import java.util.ArrayList;

public interface IGTFSObject
{
    void loadData(String[] attributes);
    ArrayList<Object> getColumnTypes();
    String getKey();
}
