package GUI.TablePanels;

import GUI.TableModels.MyTableItemModel;

import javax.swing.*;

public class TableRowFilter extends RowFilter <MyTableItemModel, Object>
{
    private final String searchText;
    private final JCheckBox[] searchCheckBoxes;

    public TableRowFilter(String searchText, JCheckBox[] searchCheckBoxes)
    {
        this.searchText = searchText;
        this.searchCheckBoxes = searchCheckBoxes;
    }
    @Override
    public boolean include(Entry entry)
    {
        for (int i = 0; i < this.searchCheckBoxes.length; i++)
        {
            if(this.searchCheckBoxes[i].isSelected())
            {
                if(entry.getStringValue(i).contains(this.searchText))
                {
                    return true;
                }
            }

        }
        return false;
    }
}
