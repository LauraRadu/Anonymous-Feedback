package opinion;

import java.sql.Time;
import java.util.Date;

public class Opinion {
    private String name;
    private String opinion;
    private Date date;
    private Time hour;


    public Opinion(String name, String opinion, Date date, Time hour) {
        this.name = name;
        this.opinion = opinion;
        this.date = date;
        this.hour = hour;
    }
    public Opinion(String name, String opinion) {
        this.name = name;
        this.opinion = opinion;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public Opinion() {}



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
