package at.htlpinkafeld.projectmanager.pojo;

import java.util.Date;

/**
 * Created by User on 19.10.2015.
 */
public class Activity {
    private Integer proj;
    private Integer id;
    private String name;
    private String prior;
    private Date startDat;
    private Date endDat;
    private String effort;

    public Activity(Integer proj, int id, String name, String prior, Date startDat, Date endDat, String effort) {
        this.proj =proj;
        this.id = id;
        this.name = name;
        this.prior = prior;
        this.startDat = startDat;
        this.endDat = endDat;
        this.effort = effort;
    }

    public Activity() {

    }

    public Activity(int actId) {
        id=actId;
    }

    public Integer getProj() {
        return proj;
    }

    public void setProj(Integer proj) {
        this.proj =proj;
    }

    public Integer getId() {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }

    public void setStartDat(Date startDat) {
        this.startDat = startDat;
    }

    public void setEndDat(Date endDat) {
        this.endDat = endDat;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", project="+ proj +"\'"+
                ", name='" + name + '\'' +
                ", prior='" + prior + '\'' +
                ", startDat=" + startDat +
                ", endDat=" + endDat +
                ", effort='" + effort + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        return id == activity.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
