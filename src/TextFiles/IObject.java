package TextFiles;

public interface IObject
{
    void loadData(String[] attributes);
    void getAllData();
    Object[] getColumnTypes(String[] attributes);
    String getKey();


}
