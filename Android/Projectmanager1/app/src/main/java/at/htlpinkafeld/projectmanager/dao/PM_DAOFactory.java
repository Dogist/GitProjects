package at.htlpinkafeld.projectmanager.dao;

import android.content.Context;

import at.htlpinkafeld.projectmanager.dao.activityDAO.ActivityDAO;
import at.htlpinkafeld.projectmanager.dao.projectDAO.ProjectSQL_DAO;
import at.htlpinkafeld.projectmanager.dao.teamMemberDAO.TeamMemberDAO;

/**
 * Created by User on 07.03.2016.
 */
public abstract class PM_DAOFactory {

    public static PM_DAOFactory getPM_DAOFactory(Context context) {
        return new PMSQL_DAOFactory(context);
    }

    public abstract ProjectSQL_DAO getProjectDAO();
    public abstract ActivityDAO getActivityDAO();
    public abstract TeamMemberDAO getTeamMemberDAO();
    public abstract CalendarDAO getCalendarDAO();

}
