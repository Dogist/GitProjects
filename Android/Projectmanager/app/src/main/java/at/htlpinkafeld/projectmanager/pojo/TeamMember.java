package at.htlpinkafeld.projectmanager.pojo;

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

    public TeamMember(Integer id, String title, String job, String fname, String lname, String dept) {
        this.id = id;
        this.title = title;
        this.job = job;
        this.fname = fname;
        this.lname = lname;
        this.dept = dept;
    }

    public TeamMember(int memId) {
        id=memId;
    }

    public Integer getId() {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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
                ", fname=" + fname  +
                ", lname=" + lname  +
                ", dept=" + dept  +
                '}';
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
