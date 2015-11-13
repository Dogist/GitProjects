package htlpinkafeld.at.projectmanager.pojo;

import java.util.Date;

/**
 * Created by User on 19.10.2015.
 */
public class Project {
    private int id;
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

    public int getId() {
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
}
