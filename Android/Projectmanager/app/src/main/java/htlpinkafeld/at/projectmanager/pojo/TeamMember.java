package htlpinkafeld.at.projectmanager.pojo;

/**
 * Created by User on 12.10.2015.
 */
public class TeamMember {
    private final int id;
    private final String title;
    private final String job;
    private final String fname;
    private final String lname;
    private final String dept;

    public TeamMember(int id, String title, String job, String fname, String lname, String dept) {
        this.id = id;
        this.title = title;
        this.job = job;
        this.fname = fname;
        this.lname = lname;
        this.dept = dept;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getJob() {
        return job;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", job='" + job + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
