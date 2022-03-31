package GUI.TableModels;

import TextFiles.Agency;
import TextFiles.IObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class AgencyTableModel extends MyTableItemModel {

    public AgencyTableModel(Hashtable<String, IObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(hashtable, keys, columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object value = "";
        Agency agency = (Agency)super.hashtable.get(super.keys.get(rowIndex));
        switch (columnIndex)
        {
            case 0 -> value = agency.getAgency_id();
            case 1 -> value = agency.getAgency_name();
            case 2 -> value = agency.getAgency_url();
            case 3 -> value = agency.getAgency_timezone();
            case 4 -> value = agency.getAgency_lang();
            case 5 -> value = agency.getAgency_phone();
            case 6 -> value = agency.getAgency_fare_url();
        }

        return value;
    }

    @Override
    public Class<?> getColumnClass(int column)
    {
        return String.class;
    }

}
