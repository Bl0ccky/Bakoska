package GUI;
import GUI.TablePanels.*;
import TextFiles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminPanel extends JPanel implements ActionListener
{
    private final JPanel contentPanel;
    private final TablePanel agencyTablePanel;
    private final TablePanel calendarDateTablePanel;
    private final TablePanel calendarTablePanel;
    private final TablePanel routeTablePanel;
    private final TablePanel stopTablePanel;
    private final TablePanel stopTimeTablePanel;
    private final TablePanel tripTablePanel;


    public AdminPanel(JPanel panel, MainFrame mainFrame)
    {
        this.contentPanel = panel;
        this.setLayout(null);

        JTabbedPane tablePanels = new JTabbedPane();
        this.agencyTablePanel = new AgencyTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllAgency(), ObjectType.AGENCY);
        this.calendarDateTablePanel = new CalendarDateTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllCalendarDates(), ObjectType.CALENDAR_DATE);
        this.calendarTablePanel = new CalendarTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllCalendars(), ObjectType.CALENDAR);
        this.routeTablePanel = new RouteTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllRoutes(), ObjectType.ROUTE);
        this.stopTablePanel = new StopTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllStops(), ObjectType.STOP);
        this.stopTimeTablePanel = new StopTimeTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllStopTimes(), ObjectType.STOP_TIME);
        this.tripTablePanel = new TripTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllTrips(), ObjectType.TRIP);

        tablePanels.add("Agency", this.agencyTablePanel);
        tablePanels.add("Calendar Date", this.calendarDateTablePanel);
        tablePanels.add("Calendar", this.calendarTablePanel);
        tablePanels.add("Route", this.routeTablePanel);
        tablePanels.add("Stop", this.stopTablePanel);
        tablePanels.add("Stop Time", this.stopTimeTablePanel);
        tablePanels.add("Trip", this.tripTablePanel);

        tablePanels.setBounds(50,25,1450,850);
        this.add(tablePanels);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //String changeToPanel = "adminPanel";
        //CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
        //cardLayout.show(this.contentPanel, changeToPanel);

    }

    public TablePanel getTablePanel(ObjectType objectType)
    {
        return switch (objectType) {
            case AGENCY -> this.agencyTablePanel;
            case CALENDAR -> this.calendarTablePanel;
            case CALENDAR_DATE -> this.calendarDateTablePanel;
            case ROUTE -> this.routeTablePanel;
            case STOP -> this.stopTablePanel;
            case STOP_TIME -> this.stopTimeTablePanel;
            case TRIP -> this.tripTablePanel;
        };
    }

    public JPanel getContentPanel()
    {
        return contentPanel;
    }
}
