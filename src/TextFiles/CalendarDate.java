package TextFiles;

import Enums.CalendarDate.ExceptionType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarDate implements IObject
{
    private String service_id;
    private Date date;
    private ExceptionType exception_type;

    public CalendarDate(){}

    @Override
    public void loadData(String[] attributes)
    {
        this.service_id = attributes[0];
        if(attributes[1] != null && !attributes[1].equals(""))
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ObjectFactory.DatePattern);
            try {
                this.date = simpleDateFormat.parse(attributes[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(attributes[2] != null && !attributes[2].equals(""))
        {
            this.exception_type = ExceptionType.getExceptionType(Integer.parseInt(attributes[2]));
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
    public String getKey() {
        return this.service_id + "-" + this.date + "-" + this.exception_type;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ExceptionType getException_type() {
        return exception_type;
    }

    public void setException_type(ExceptionType exception_type) {
        this.exception_type = exception_type;
    }


}
