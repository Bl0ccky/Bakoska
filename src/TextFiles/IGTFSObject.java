package TextFiles;

public interface IGTFSObject
{
    void loadData(String[] attributes);
    void getAllData();
    Object[] getColumnTypes(String[] attributes);
    String getKey();


}
