package at.htlpinkafeld.projectmanager.dao;

import android.content.Context;



/**
 * Created by User on 07.03.2016.
 */
public class PMSQL_DAOFactory extends at.htlpinkafeld.projectmanager.dao.PM_DAOFactory {

    private final at.htlpinkafeld.projectmanager.dao.PMDBHelper pmDatabaseHelper;

    public PMSQL_DAOFactory(Context context) {
        this.pmDatabaseHelper = new at.htlpinkafeld.projectmanager.dao.PMDBHelper(context);
    }

    @Override
    public at.htlpinkafeld.projectmanager.dao.projectDAO.ProjectSQL_DAO getProjectDAO() {
        return new at.htlpinkafeld.projectmanager.dao.projectDAO.ProjectSQL_DAO(pmDatabaseHelper);
    }

    @Override
    public at.htlpinkafeld.projectmanager.dao.activityDAO.ActivityDAO getActivityDAO() {
        return new at.htlpinkafeld.projectmanager.dao.activityDAO.ActivitySQL_DAO(pmDatabaseHelper);
    }

    @Override
    public at.htlpinkafeld.projectmanager.dao.teamMemberDAO.TeamMemberDAO getTeamMemberDAO() {
        return new at.htlpinkafeld.projectmanager.dao.teamMemberDAO.TeamMemberSQL_DAO(pmDatabaseHelper);
    }

    public at.htlpinkafeld.projectmanager.dao.CalendarDAO getCalendarDAO(){return new at.htlpinkafeld.projectmanager.dao.CalendarContentProviderDAO();}

}
