package at.htlpinkafeld.projectmanager.service;

import java.util.LinkedList;
import java.util.List;

import at.htlpinkafeld.projectmanager.MainActivity;
import at.htlpinkafeld.projectmanager.dao.PM_DAOFactory;
import at.htlpinkafeld.projectmanager.pojo.Activity;
import at.htlpinkafeld.projectmanager.pojo.Project;
import at.htlpinkafeld.projectmanager.pojo.TeamMember;


/**
 * Created by User on 19.10.2015.
 */
public class ServiceClass {
    private static ServiceClass sc=null;
    private List<TeamMember> memL;
    private List<Project> projL;
    private List<Activity> actL;

    private ServiceClass() {
        this.memL = new LinkedList<>();
        this.projL = new LinkedList<>();
        this.actL = new LinkedList<>();
    }

    public static ServiceClass getServiceClass(){
        if(sc ==null) {
            sc = new ServiceClass();
            //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            //try {

                //sc.memL.put(2, new TeamMember(2, "MR", "PROGRAMMER", "Patrick", "Hofer", "POOOS"));
                //sc.actL.put(2,new Activity(5,2,"Nop.Avi", "3",dateFormat.parse("10-10-2011"), dateFormat.parse("10-10-2014"), "none"));
            //} catch (ParseException e) {
           //     e.printStackTrace();
           // }
            sc.memL= PM_DAOFactory.getPM_DAOFactory(MainActivity.context).getTeamMemberDAO().getEntityList();
            sc.projL=PM_DAOFactory.getPM_DAOFactory(MainActivity.context).getProjectDAO().getEntityList();
            sc.actL=PM_DAOFactory.getPM_DAOFactory(MainActivity.context).getActivityDAO().getEntityList();
            PM_DAOFactory.getPM_DAOFactory(MainActivity.context).getCalendarDAO().getFirstCalendar("martin.six98@gmail.com","com.google", "martin.six98@gmail.com");
        }
        return sc;

    }

    public void addMember(TeamMember mem){
        int i=getPositionFromMember(mem);
        if(i==-1)
            this.memL.add(mem);
    }

    public TeamMember getMember(int i){
        return this.memL.get(i);
    }

    public void updateMember(TeamMember nMem){
        int i=getPositionFromMember(nMem);
        if(i!=-1)
            this.memL.set(i, nMem);

    }

    public void removeMemberByID(int memId){
        this.memL.remove(new TeamMember(memId));
    }

    public void printAllM(){
        for(TeamMember mem:this.memL)
            System.out.println(mem);
    }

    public int sizeM(){
        return this.memL.size();
    }

    private int getPositionFromMember(TeamMember m){
        int pos=-1;
        int x=0;
        for(TeamMember mem:memL){
            if(m.equals(mem))
                pos=x;
            x++;
        }
        return pos;
    }


    public void addProject(Project proj){
        int i=getPositionFroProject(proj);
        if(i==-1)
            this.projL.add(proj);
    }

    public Project getProject(int i){
        return this.projL.get(i);
    }

    public void updateProject(Project nProj){
        int i=getPositionFroProject(nProj);
        if(i!=-1)
            this.projL.set(i, nProj);
    }

    public void removeProjectByID(int projId){
        this.projL.remove(new Project(projId));
    }

    public void printAllP(){
        for(Project proj:this.projL)
            System.out.println(proj);
    }

    public int sizeP(){
        return this.projL.size();
    }

    private int getPositionFroProject(Project p){
        int pos=-1;
        int x=0;
        for(Project proj:projL){
            if(p.equals(proj))
                pos=x;
            x++;
        }
        return pos;
    }

    public void addActivity(Activity act){
        int i=getPositionFromActivity(act);
        if(i==-1)
            this.actL.add(act);
    }

    public Activity getActivity(int i){
        return this.actL.get(i);
    }

    public void updateActivity(Activity nAct){
        int i=getPositionFromActivity(nAct);
        if(i!=-1)
            this.actL.set(i, nAct);
    }

    public void removeActivityByID(int actId){
        this.actL.remove(new Activity(actId));
    }

    public void printAllA(){
        for(Activity act:this.actL)
            System.out.println(act);
    }

    public int sizeA(){
        return this.actL.size();
    }

    private int getPositionFromActivity(Activity a){
        int pos=-1;
        int x=0;
        for(Activity act:actL){
            if(a.equals(act))
                pos=x;
            x++;
        }
        return pos;
    }
}
