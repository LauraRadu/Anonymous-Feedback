package opinion;

import java.util.Date;

public class Opinion {
    private String name;
    private String opinion;

    public Opinion(String name, String opinion, Date date) {
        this.name = name;
        this.opinion = opinion;
        this.date = date;
    }
    public Opinion(String name, String opinion) {
        this.name = name;
        this.opinion = opinion;
    }

    public Opinion() {}

    private Date date;

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
