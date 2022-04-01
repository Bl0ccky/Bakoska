package TextFiles;

import Enums.CalendarDate.ExceptionType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


public class CalendarDate implements IGTFSObject
{
    private String service_id;
    private LocalDate date;
    private ExceptionType exception_type;

    public CalendarDate(){}

    @Override
    public void loadData(String[] attributes)
    {
        DateTimeFormatter dateFormat = new DateTimeFormatterBuilder().appendPattern(GTFSObjectFactory.DatePattern).toFormatter();
        this.service_id = attributes[0];
        if(attributes[1] != null && !attributes[1].equals(""))
        {
            this.date = LocalDate.parse(attributes[1], dateFormat);
        }

        if(attributes[2] != null && !attributes[2].equals(""))
        {
            this.exception_type = ExceptionType.getExceptionType(Integer.parseInt(attributes[2]));
        }
        else
        {
            this.exception_type = ExceptionType.NO_INFO;
        }

    }

    @Override
    public void getAllData()
    {
        System.out.printf(
                "%5s\t%5s\t%5s\n",
                this.service_id,
                this.date,
                this.exception_type);
    }

    @Override
    public Object[] getColumnTypes(String[] attributes)
    {
        Object[] columnTypes = new Object[attributes.length];
        columnTypes[0] = this.service_id;
        columnTypes[1] = this.date;
        columnTypes[2] = this.exception_type;
        return columnTypes;
    }

    @Override
    public String getKey() {
        return this.service_id + "-" + this.date + "-" + this.exception_type;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public ExceptionType getException_type() {
        return exception_type;
    }

    public void setException_type(ExceptionType exception_type) {
        this.exception_type = exception_type;
    }


}
