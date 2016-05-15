package at.htlpinkafeld.projectmanager.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.htlpinkafeld.projectmanager.service.ProjectManagerService;

/**
 * Created by tq on 15-11-20.
 */
public class Activity {

    private int id;
    private String name, priority;
    private Date startDate, endDate;
    private double effort;

    private Project project;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public Activity(int id, String name, String priority, Date startDate, Date endDate, double effort) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.effort = effort;
    }

    public Activity() {
    }

    public Activity(int id, String name, String priority, Date startDate, Date endDate, double effort, Project project) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.effort = effort;
        this.project = project;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public double getEffort() {
        return effort;
    }

    public void setEffort(double effort) {
        this.effort = effort;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }

    public String toCommaSeparatedLine() {
        return id + ";" + name + ";" + priority + ";" + SDF.format(startDate) + ";" + SDF.format(endDate) +
                ";" + effort + ";" + ((project != null) ? project.getId() : "-1");
    }

    public static Activity fromCommaSeparatedLine(String line) {
        String[] p = line.split(";");
        Activity a = null;
        try {
            a = new Activity(Integer.parseInt(p[0]),
                    p[1],
                    p[2],
                    SDF.parse(p[3]),
                    SDF.parse(p[4]),
                    Double.parseDouble(p[5]));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!p[6].isEmpty() && a != null) {
            a.setProject(ProjectManagerService.getInstance().getProject(Integer.parseInt(p[6])));
        }
        return a;
    }
}
