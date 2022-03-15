package GUI;
import GUI.TablePanels.*;
import TextFiles.ObjectType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminPanel extends JPanel implements ActionListener
{
    private final JPanel contentPanel;

    public AdminPanel(JPanel panel, MainFrame mainFrame)
    {
        this.contentPanel = panel;
        TablePanel agencyTablePanel = new AgencyTablePanel(this.contentPanel, mainFrame, mainFrame.getDataLoader().getAllAgency(), ObjectType.AGENCY);
        TablePanel calendarDateTablePanel = new CalendarDateTablePanel(this.contentPanel, mainFrame, mainFrame.getDataLoader().getAllCalendarDates(), ObjectType.CALENDAR_DATE);
        TablePanel calendarTablePanel = new CalendarTablePanel(this.contentPanel, mainFrame, mainFrame.getDataLoader().getAllCalendars(), ObjectType.CALENDAR);
        TablePanel routeTablePanel = new RouteTablePanel(this.contentPanel, mainFrame, mainFrame.getDataLoader().getAllRoutes(), ObjectType.ROUTE);
        TablePanel stopTablePanel = new StopTablePanel(this.contentPanel, mainFrame, mainFrame.getDataLoader().getAllStops(), ObjectType.STOP);
        TablePanel stopTimeTablePanel = new StopTimeTablePanel(this.contentPanel, mainFrame, mainFrame.getDataLoader().getAllStopTimes(), ObjectType.STOP_TIME);
        TablePanel tripTablePanel = new TripTablePanel(this.contentPanel, mainFrame, mainFrame.getDataLoader().getAllTrips(), ObjectType.TRIP);

        this.add(agencyTablePanel);
        this.add(calendarDateTablePanel);
        this.add(calendarTablePanel);
        this.add(routeTablePanel);
        this.add(stopTablePanel);
        this.add(stopTimeTablePanel);
        this.add(tripTablePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String changeToPanel = "adminPanel";
        CardLayout cardLayout = (CardLayout) this.contentPanel.getLayout();
        cardLayout.show(this.contentPanel, changeToPanel);

    }

}
