package at.htlpinkafeld.projectmanager.pojo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by User on 19.10.2015.
 */
public class Activity {
    private Project proj;
    private Integer id;
    private String name;
    private String prior;
    private Date startDat;
    private Date endDat;
    private double effort;

    public Activity(Project proj, int id, String name, String prior, Date startDat, Date endDat, double effort) {
        this.proj = proj;
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
        id = actId;
    }

    public Project getProj() {
        return proj;
    }

    public void setProj(Project proj) {
        this.proj = proj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }

    public Date getStartDat() {
        return startDat;
    }

    public void setStartDat(Date startDat) {
        this.startDat = startDat;
    }

    public Date getEndDat() {
        return endDat;
    }

    public void setEndDat(Date endDat) {
        this.endDat = endDat;
    }

    public double getEffort() {
        return effort;
    }

    public void setEffort(double effort) {
        this.effort = effort;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", project=" + proj + "\'" +
                ", name='" + name + '\'' +
                ", prior='" + prior + '\'' +
                ", startDat=" + startDat +
                ", endDat=" + endDat +
                ", effort='" + effort + '\'' +
                '}';
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jObject = new JSONObject();
        jObject.put("name", name);
        jObject.put("prior", prior);
        jObject.put("startDat", startDat);
        jObject.put("endDat", endDat);
        jObject.put("effort", effort);
        return jObject;
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
