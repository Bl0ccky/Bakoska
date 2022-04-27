package GTFSFiles;

import java.util.ArrayList;

public class Agency implements IGTFSObject {
    private String agency_id;
    private String agency_name;
    private String agency_url;
    private String agency_timezone;
    private String agency_lang;
    private String agency_phone;
    private String agency_fare_url;

    public Agency() {
        this.agency_id = "";
        this.agency_name = "";
        this.agency_url = "";
        this.agency_timezone = "";
        this.agency_lang = "";
        this.agency_phone = "";
        this.agency_fare_url = "";
    }

    @Override
    public void loadData(String[] attributes, String[] columnNames) {
        for (int i = 0; i < columnNames.length; i++) {
            switch (columnNames[i]) {
                case "agency_id" -> this.agency_id = attributes[i];
                case "agency_name" -> this.agency_name = attributes[i];
                case "agency_url" -> this.agency_url = attributes[i];
                case "agency_timezone" -> this.agency_timezone = attributes[i];
                case "agency_lang" -> this.agency_lang = attributes[i];
                case "agency_phone" -> this.agency_phone = attributes[i];
                case "agency_fare_url" -> this.agency_fare_url = attributes[i];
            }
        }
    }

    @Override
    public ArrayList<Object> getColumnTypes() {
        ArrayList<Object> columnTypes = new ArrayList<>();
        columnTypes.add(this.agency_id);
        columnTypes.add(this.agency_name);
        columnTypes.add(this.agency_url);
        columnTypes.add(this.agency_timezone);
        columnTypes.add(this.agency_lang);
        columnTypes.add(this.agency_phone);
        columnTypes.add(this.agency_fare_url);
        return columnTypes;
    }

    @Override
    public ArrayList<Object> getAttributesForExportGTFS() {
        return this.getColumnTypes();
    }

    @Override
    public String getKey() {
        return this.agency_id;
    }

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getAgency_url() {
        return agency_url;
    }

    public void setAgency_url(String agency_url) {
        this.agency_url = agency_url;
    }

    public String getAgency_timezone() {
        return agency_timezone;
    }

    public void setAgency_timezone(String agency_timezone) {
        this.agency_timezone = agency_timezone;
    }

    public String getAgency_lang() {
        return agency_lang;
    }

    public void setAgency_lang(String agency_lang) {
        this.agency_lang = agency_lang;
    }

    public String getAgency_phone() {
        return agency_phone;
    }

    public void setAgency_phone(String agency_phone) {
        this.agency_phone = agency_phone;
    }

    public String getAgency_fare_url() {
        return agency_fare_url;
    }

    public void setAgency_fare_url(String agency_fare_url) {
        this.agency_fare_url = agency_fare_url;
    }


}
