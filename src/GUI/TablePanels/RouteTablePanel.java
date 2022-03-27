package GUI.TablePanels;

import Enums.Route.RouteType;
import GUI.MainFrame;
import TextFiles.IObject;
import TextFiles.ObjectType;
import TextFiles.Route;
import javax.swing.*;
import java.util.Hashtable;

public class RouteTablePanel extends TablePanel{
    public RouteTablePanel(JPanel panel, MainFrame mainFrame, Hashtable<String, IObject> hashtable, ObjectType objectType) {
        super(panel, mainFrame, hashtable, objectType);
    }

    @Override
    boolean checkAddInputs()
    {
        return !((JTextField)this.addFormObjects.get(0)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(1)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(2)).getText().equals("")
                && !((JTextField)this.addFormObjects.get(3)).getText().equals("")
                && ((JComboBox<?>) this.addFormObjects.get(5)).getSelectedItem() != null
                && !this.hashtable.containsKey(((JTextField)this.addFormObjects.get(0)).getText());
    }

    @Override
    void addNewObject()
    {
        if(this.checkAddInputs())
        {
            Route newRoute = new Route();
            newRoute.setRoute_id(((JTextField) this.addFormObjects.get(0)).getText());
            newRoute.setAgency_id(((JTextField) this.addFormObjects.get(1)).getText());
            newRoute.setRoute_short_name(((JTextField) this.addFormObjects.get(2)).getText());
            newRoute.setRoute_long_name(((JTextField) this.addFormObjects.get(3)).getText());
            newRoute.setRoute_desc(((JTextField) this.addFormObjects.get(4)).getText());
            newRoute.setRoute_type((RouteType) ((JComboBox<?>) this.addFormObjects.get(5)).getSelectedItem());
            newRoute.setRoute_url(((JTextField) this.addFormObjects.get(6)).getText());
            newRoute.setRoute_color(((JTextField) this.addFormObjects.get(7)).getText());
            newRoute.setRoute_text_color(((JTextField) this.addFormObjects.get(8)).getText());
            this.hashtable.put(newRoute.getKey(), newRoute);
            this.keys.add(newRoute.getKey());
            this.myTableItemModel.fireTableDataChanged();
        }

    }

}
