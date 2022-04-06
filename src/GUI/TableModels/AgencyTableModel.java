package GUI.TableModels;

import GTFSFiles.Agency;
import GTFSFiles.IGTFSObject;
import GUI.MainFrame;

import java.util.ArrayList;
import java.util.Hashtable;

public class AgencyTableModel extends MyTableItemModel {

    public AgencyTableModel(MainFrame mainFrame, Hashtable<String, IGTFSObject> hashtable, ArrayList<String> keys, String[] columnNames) {
        super(mainFrame, hashtable, keys, columnNames);
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Agency agency = (Agency)this.hashtable.get(this.keys.get(rowIndex));

        String findingIDValue = agency.getAgency_id();
        if(!(this.mainFrame.getAdminPanel().getTablePanel(GTFSFiles.GTFSObjectType.ROUTE).tableContainsValueAt(findingIDValue, 1) && columnIndex == 0))
        {
            this.hashtable.remove(this.keys.get(rowIndex));
            switch (columnIndex)
            {
                case 0 -> agency.setAgency_id((String) aValue);
                case 1 -> agency.setAgency_name((String) aValue);
                case 2 -> agency.setAgency_url((String) aValue);
                case 3 -> agency.setAgency_timezone((String) aValue);
                case 4 -> agency.setAgency_lang((String) aValue);
                case 5 -> agency.setAgency_phone((String) aValue);
                case 6 -> agency.setAgency_fare_url((String) aValue);
            }

            this.hashtable.put(agency.getKey(), agency);
            this.keys.set(rowIndex, agency.getKey());
            this.mainFrame.getDataLoader().updateHashTable(this.hashtable, GTFSFiles.GTFSObjectType.AGENCY);
            this.fireTableDataChanged();
        }
    }
}
