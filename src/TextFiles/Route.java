package TextFiles;

import Enums.Route.RouteType;

public class Route implements IObject
{
    private String route_id;
    private String agency_id;
    private String route_short_name;
    private String route_long_name;
    private String route_desc;
    private RouteType route_type;
    private String route_url;
    private String route_color;
    private String route_text_color;

    public Route() {}

    @Override
    public void loadData(String[] attributes)
    {
        this.route_id = attributes[0];
        this.agency_id = attributes[1];
        this.route_short_name = attributes[2];
        this.route_long_name = attributes[3];
        this.route_desc = attributes[4];

        if(attributes[5] != null && !attributes[5].equals(""))
        {
            this.route_type = RouteType.getRouteType(Integer.parseInt(attributes[5]));
        }

        this.route_url = attributes[6];
        this.route_color = attributes[7];
        this.route_text_color = attributes[8];

    }

    @Override
    public void getAllData()
    {
        System.out.printf(
                "%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\n",
                this.route_id,
                this.agency_id,
                this.route_short_name,
                this.route_long_name,
                this.route_desc,
                this.route_type,
                this.route_url,
                this.route_color,
                this.route_text_color);

    }

    @Override
    public String getKey() {
        return this.route_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }

    public String getRoute_short_name() {
        return route_short_name;
    }

    public void setRoute_short_name(String route_short_name) {
        this.route_short_name = route_short_name;
    }

    public String getRoute_long_name() {
        return route_long_name;
    }

    public void setRoute_long_name(String route_long_name) {
        this.route_long_name = route_long_name;
    }

    public String getRoute_desc() {
        return route_desc;
    }

    public void setRoute_desc(String route_desc) {
        this.route_desc = route_desc;
    }

    public RouteType getRoute_type() {
        return route_type;
    }

    public void setRoute_type(RouteType route_type) {
        this.route_type = route_type;
    }

    public String getRoute_url() {
        return route_url;
    }

    public void setRoute_url(String route_url) {
        this.route_url = route_url;
    }

    public String getRoute_color() {
        return route_color;
    }

    public void setRoute_color(String route_color) {
        this.route_color = route_color;
    }

    public String getRoute_text_color() {
        return route_text_color;
    }

    public void setRoute_text_color(String route_text_color) {
        this.route_text_color = route_text_color;
    }


}
