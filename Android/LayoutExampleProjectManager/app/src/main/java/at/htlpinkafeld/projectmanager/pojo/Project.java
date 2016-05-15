package at.htlpinkafeld.projectmanager.pojo;

import java.util.Date;

/**
 * Created by tq on 15-11-20.
 */
public class Project {

    private int id;
    private String name;
    private String contractor;
    private String processModel;
    private Date startDate;
    private Date endDate;
    private String description;

    public Project(int id, String name, String contractor, String processModel, Date startDate, Date endDate, String description) {
        this.id = id;
        this.name = name;
        this.contractor = contractor;
        this.processModel = processModel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getProcessModel() {
        return processModel;
    }

    public void setProcessModel(String processModel) {
        this.processModel = processModel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "" + id + ": " + name;
    }
}

