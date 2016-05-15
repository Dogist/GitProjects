package at.htlpinkafeld.projectmanager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.htlpinkafeld.projectmanager.pojo.Activity;
import at.htlpinkafeld.projectmanager.pojo.Project;
import at.htlpinkafeld.projectmanager.pojo.TeamMember;
import at.htlpinkafeld.projectmanager.util.DefaultHashMap;

public class ProjectManagerService {


    private static volatile ProjectManagerService instance = null;

    private ProjectManagerService() {
    }

    public static synchronized ProjectManagerService getInstance() {
        if (instance == null) {
            instance = new ProjectManagerService();

            TeamMember m = new TeamMember(1, "Mr.", "CTO", "Bob", "Smith", "Engineering");
            instance.addTeamMember(m);
            m = new TeamMember(2, "Mr.", "CEO", "Kyle", "Zeno", "Management");
            instance.addTeamMember(m);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Project p = null;
            try {
                p = new Project(1, "Android App", "Banana Inc.", "Scrum",
                        sdf.parse("2015-01-01"), sdf.parse("2015-05-30"), "Project Manager App");
                instance.addProject(p);

                instance.setSelectedProject(p);

                Activity a = new Activity(1, "Create preferences", "A", sdf.parse("2015-03-02"), sdf.parse("2015-02-03"), 5, p);

                instance.addActivity(a);

                p = new Project(2, "Company Website", "Banana Inc.", "Waterfall",
                        sdf.parse("2016-02-01"), sdf.parse("2016-07-31"), "New Website with CMS");
                instance.addProject(p);

                instance.setSelectedProject(p);

                a = new Activity(2, "Transfer design to HTML", "A", sdf.parse("2016-03-03"), sdf.parse("2016-03-10"), 40, p);

                instance.addActivity(a);




            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        return instance;
    }

    // -----------------

    private Map<Integer, TeamMember> teamMembers = new HashMap<>();

    public List<TeamMember> getTeamMembers() {
        List<TeamMember> members = new ArrayList<>(teamMembers.values());
        Collections.sort(members, new Comparator<TeamMember>() {
            @Override
            public int compare(TeamMember t1, TeamMember t2) {
                return Integer.compare(t1.getId(), t2.getId());
            }
        });
        return members;
    }

    public TeamMember getMember(int id) {
        return teamMembers.get(id);
    }

    public void addTeamMember(TeamMember m) {
        teamMembers.put(m.getId(), m);
    }

    public void printTeamMembers() {
        System.out.println("Team Members:");
        for (TeamMember m : teamMembers.values()) {
            System.out.println(m);
        }
    }

    public void updateTeamMember(TeamMember m) {
        teamMembers.put(m.getId(), m);
    }

    public void deleteTeamMember(TeamMember m) {
        teamMembers.remove(m.getId());
    }


    // -----------------

    private Map<Integer, Project> projects = new HashMap<>();

    private Project selectedProject;

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>(this.projects.values());
        Collections.sort(projects, new Comparator<Project>() {
            @Override
            public int compare(Project p1, Project p2) {
                return Integer.compare(p1.getId(), p2.getId());
            }
        });
        return projects;
    }

    public void addProject(Project p) {
        projects.put(p.getId(), p);
    }

    public Project getProject(int id) {
        return projects.get(id);
    }

    public void updateProject(Project project) {
        projects.put(project.getId(), project);
    }

    public void deleteProject(Project project) {
        projects.remove(project.getId());
    }

    public void setSelectedProject(Project p) {
        this.selectedProject = p;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    // -----------------

    private Map<Project, HashMap<Integer, Activity>> activities = new DefaultHashMap<Project, HashMap<Integer, Activity>>() {
        @Override
        public HashMap<Integer, Activity> getDefaultValue() {
            return new HashMap<>();
        }
    };

    public void addActivity(Activity a) {
        this.activities.get(getSelectedProject()).put(a.getId(), a);
    }

    public Activity getActivity(int id) {
        return this.activities.get(getSelectedProject()).get(id);
    }

    public List<Activity> getActivities() {
        List<Activity> activities = new ArrayList<>(this.activities.get(getSelectedProject()).values());
        Collections.sort(activities, new Comparator<Activity>() {
            @Override
            public int compare(Activity p1, Activity p2) {
                return Integer.compare(p1.getId(), p2.getId());
            }
        });
        return activities;
    }

    public void deleteActivity(Activity a) {
        this.activities.get(getSelectedProject()).remove(a.getId());
    }

    public void updateActivity(Activity a) {
        this.activities.get(getSelectedProject()).put(a.getId(), a);
    }


    public List<Activity> getAllActivities() {
        List<Activity> activities = new ArrayList<>();
        for (Project p : this.activities.keySet()) {
            activities.addAll(this.activities.get(p).values());
        }
        return activities;
    }

}

