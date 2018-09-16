package CountDown;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Deadline {
    private String name=null;
    private Calendar c= new GregorianCalendar();

    public Deadline(String name, GregorianCalendar c){
        this.name=name;
        this.c=c;
    }
    public String getName(){
        return name;
    }
    public Calendar getCalender(){
        return c;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setCalender(Calendar c){
        this.c=c;
    }
}
