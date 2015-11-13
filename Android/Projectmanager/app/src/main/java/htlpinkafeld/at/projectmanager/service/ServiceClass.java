package htlpinkafeld.at.projectmanager.service;

import java.util.ArrayList;
import java.util.List;

import htlpinkafeld.at.projectmanager.pojo.Activity;
import htlpinkafeld.at.projectmanager.pojo.Project;
import htlpinkafeld.at.projectmanager.pojo.TeamMember;

/**
 * Created by User on 19.10.2015.
 */
public class ServiceClass {
    private static  ServiceClass sc=null;
    private final List<TeamMember> memL;
    private final List<Project> projL;
    private final List<Activity> actL;

    private ServiceClass() {
        this.memL = new ArrayList<>();
        this.projL = new ArrayList<>();
        this.actL = new ArrayList<>();
    }

    public static ServiceClass getServiceClass(){
        if(sc ==null) {
            sc = new ServiceClass();
        }
        return sc;

    }

    public void addMember(TeamMember mem){
        this.memL.add(mem);
    }

    public TeamMember getMember(int i){
        return this.memL.get(i);
    }

    public void updateMember(int i, TeamMember nMem){
        this.memL.set(i, nMem);
    }

    public void removeMember(int i){
        this.memL.remove(i);
    }

    public void printAllM(){
        for(TeamMember mem:this.memL)
            System.out.println(mem);
    }

    public int sizeM(){
        return this.memL.size();
    }


    public void addProject(Project proj){
        this.projL.add(proj);
    }

    public Project getProject(int i){
        return this.projL.get(i);
    }

    public void updateProject(int i, Project nProj){
        this.projL.set(i, nProj);
    }

    public void removeProject(int i){
        this.projL.remove(i);
    }

    public void printAllP(){
        for(Project proj:this.projL)
            System.out.println(proj);
    }

    public int sizeP(){
        return this.projL.size();
    }

    public void addActivity(Activity act){
        this.actL.add(act);
    }

    public Activity getActivity(int i){
        return this.actL.get(i);
    }

    public void updateActivity(int i, Activity nAct){
        this.actL.set(i, nAct);
    }

    public void removeActivity(int i){
        this.actL.remove(i);
    }

    public void printAllA(){
        for(Activity act:this.actL)
            System.out.println(act);
    }

    public int sizeA(){
        return this.actL.size();
    }
}
