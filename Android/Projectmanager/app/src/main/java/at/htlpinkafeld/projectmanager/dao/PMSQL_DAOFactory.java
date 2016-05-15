package at.htlpinkafeld.projectmanager.dao;

import android.content.Context;

import at.htlpinkafeld.projectmanager.dao.activityDAO.ActivityDAO;
import at.htlpinkafeld.projectmanager.dao.activityDAO.ActivitySQL_DAO;
import at.htlpinkafeld.projectmanager.dao.projectDAO.ProjectSQL_DAO;
import at.htlpinkafeld.projectmanager.dao.teamMemberDAO.TeamMemberDAO;
import at.htlpinkafeld.projectmanager.dao.teamMemberDAO.TeamMemberSQL_DAO;


/**
 * Created by User on 07.03.2016.
 */
public class PMSQL_DAOFactory extends PM_DAOFactory {

    private final PMDBHelper pmDatabaseHelper;

    public PMSQL_DAOFactory(Context context) {
        this.pmDatabaseHelper = new PMDBHelper(context);
    }

    @Override
    public ProjectSQL_DAO getProjectDAO() {
        return new ProjectSQL_DAO(pmDatabaseHelper);
    }

    @Override
    public ActivityDAO getActivityDAO() {
        return new ActivitySQL_DAO(pmDatabaseHelper);
    }

    @Override
    public TeamMemberDAO getTeamMemberDAO() {
        return new TeamMemberSQL_DAO(pmDatabaseHelper);
    }

    public CalendarDAO getCalendarDAO(){return new CalendarContentProviderDAO();}

}
