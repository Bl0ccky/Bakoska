package GUI.TablePanels;

import GUI.TableModels.MyTableItemModel;

import javax.swing.*;

public class SearchTableFilter extends RowFilter <MyTableItemModel, Object>
{
    private final String searchText;
    private final JCheckBox[] searchCheckBoxes;

    public SearchTableFilter(String searchText, JCheckBox[] searchCheckBoxes)
    {
        this.searchText = searchText;
        this.searchCheckBoxes = searchCheckBoxes;
    }
    @Override
    public boolean include(Entry entry)
    {
        int numOfSelected = 0;
        for (int i = 0; i < this.searchCheckBoxes.length; i++)
        {
            if(this.searchCheckBoxes[i].isSelected())
            {
                numOfSelected++;
                if(entry.getStringValue(i).contains(this.searchText))
                {
                    return true;
                }
            }

        }
        return numOfSelected == 0;
    }
}
