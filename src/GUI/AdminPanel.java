package GUI;
import GUI.TablePanels.*;
import GTFSFiles.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminPanel extends JPanel
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
        this.agencyTablePanel = new AgencyTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllAgency(), GTFSObjectType.AGENCY);
        this.calendarDateTablePanel = new CalendarDateTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllCalendarDates(), GTFSObjectType.CALENDAR_DATE);
        this.calendarTablePanel = new CalendarTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllCalendars(), GTFSObjectType.CALENDAR);
        this.routeTablePanel = new RouteTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllRoutes(), GTFSObjectType.ROUTE);
        this.stopTablePanel = new StopTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllStops(), GTFSObjectType.STOP);
        this.stopTimeTablePanel = new StopTimeTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllStopTimes(), GTFSObjectType.STOP_TIME);
        this.tripTablePanel = new TripTablePanel(this, mainFrame, mainFrame.getDataLoader().getAllTrips(), GTFSObjectType.TRIP);

        tablePanels.add("Agency", this.agencyTablePanel);
        tablePanels.add("Calendar Date", this.calendarDateTablePanel);
        tablePanels.add("Calendar", this.calendarTablePanel);
        tablePanels.add("Route", this.routeTablePanel);
        tablePanels.add("Stop", this.stopTablePanel);
        tablePanels.add("Stop Time", this.stopTimeTablePanel);
        tablePanels.add("Trip", this.tripTablePanel);

        tablePanels.setBounds(50,25,1450,700);
        this.add(tablePanels);
    }

    public TablePanel getTablePanel(GTFSObjectType gtfsObjectType)
    {
        return switch (gtfsObjectType) {
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
