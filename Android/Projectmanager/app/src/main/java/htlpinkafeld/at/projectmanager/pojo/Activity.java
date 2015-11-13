package htlpinkafeld.at.projectmanager.pojo;

import java.util.Date;

/**
 * Created by User on 19.10.2015.
 */
public class Activity {
    private int id;
    private String name;
    private String prior;
    private Date startDat;
    private Date endDat;
    private String effort;

    public Activity(int id, String name, String prior, Date startDat, Date endDat, String effort) {
        this.id = id;
        this.name = name;
        this.prior = prior;
        this.startDat = startDat;
        this.endDat = endDat;
        this.effort = effort;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrior() {
        return prior;
    }

    public Date getStartDat() {
        return startDat;
    }

    public Date getEndDat() {
        return endDat;
    }

    public String getEffort() {
        return effort;
    }
}
