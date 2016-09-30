package at.htlpinkafeld.projectmanager.pojo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 12.10.2015.
 */
public class TeamMember {
    private Integer id;
    private String title;
    private String job;
    private String fname;
    private String lname;
    private String dept;

    public TeamMember() {
    }

    public TeamMember(JSONObject jsonObject) {
        id = -1;
        title = jsonObject.optString("title");
        job = jsonObject.optString("jobtitle");
        fname = jsonObject.optString("firstname");
        lname = jsonObject.optString("lastname");
        dept = jsonObject.optString("department");

    }

    public TeamMember(Integer id, String title, String job, String fname, String lname, String dept) {
        this.id = id;
        this.title = title;
        this.job = job;
        this.fname = fname;
        this.lname = lname;
        this.dept = dept;
    }

    public TeamMember(int memId) {
        id = memId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "id=" + id +
                ", title=" + title +
                ", job=" + job +
                ", fname=" + fname +
                ", lname=" + lname +
                ", dept=" + dept +
                '}';
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jObject = new JSONObject();
        jObject.put("title", title);
        jObject.put("job", job);
        jObject.put("fname", fname);
        jObject.put("lname", lname);
        jObject.put("dept", dept);
        return jObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMember that = (TeamMember) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
