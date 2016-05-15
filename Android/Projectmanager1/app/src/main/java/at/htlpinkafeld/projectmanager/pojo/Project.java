package at.htlpinkafeld.projectmanager.pojo;

import java.util.Date;

/**
 * Created by User on 19.10.2015.
 */
public class Project {
    private Integer id;
    private String name;
    private String contr;
    private String procMod;
    private Date startD;
    private Date endD;
    private String desc;

    public Project(int id, String name, String contr, String procMod, Date startD, Date endD, String desc) {
        this.id = id;
        this.name = name;
        this.contr = contr;
        this.procMod = procMod;
        this.startD = startD;
        this.endD = endD;
        this.desc = desc;
    }

    public Project() {

    }

    public Project(int projId) {
        id=projId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContr() {
        return contr;
    }

    public String getProcMod() {
        return procMod;
    }

    public Date getStartD() {
        return startD;
    }

    public Date getEndD() {
        return endD;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContr(String contr) {
        this.contr = contr;
    }

    public void setProcMod(String procMod) {
        this.procMod = procMod;
    }

    public void setStartD(Date startD) {
        this.startD = startD;
    }

    public void setEndD(Date endD) {
        this.endD = endD;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contr='" + contr + '\'' +
                ", procMod='" + procMod + '\'' +
                ", startD=" + startD +
                ", endD=" + endD +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return id == project.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
