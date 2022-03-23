package GUI.TablePanels;

import GUI.TableModels.MyTableItemModel;

import javax.swing.*;

public class TableRowFilter extends RowFilter <MyTableItemModel, Object>
{
    private final String searchText;

    public TableRowFilter(String searchText)
    {
        this.searchText = searchText;
    }
    @Override
    public boolean include(Entry entry)
    {
        for (int i = 0; i < entry.getValueCount(); i++)
        {
            if(entry.getStringValue(i).contains(this.searchText))
            {
                return true;
            }
        }
        return false;
    }
}
