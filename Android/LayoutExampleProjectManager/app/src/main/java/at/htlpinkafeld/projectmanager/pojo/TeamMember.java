package at.htlpinkafeld.projectmanager.pojo;

/**
 * Created by tq on 15-10-11.
 */
public class TeamMember {

    private int id;
    private String title;
    private String jobtitle;
    private String firstname;
    private String lastname;
    private String department;

    public TeamMember(int id, String title, String jobtitle, String firstname, String lastname, String department) {
        this.id = id;
        this.title = title;
        this.jobtitle = jobtitle;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return id + ": " + firstname + "  " + lastname;
    }
}
