package at.htlpinkafeld.projectmanager.dao.activityDAO;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Date;

import at.htlpinkafeld.projectmanager.dao.BaseSQL_DAO;
import at.htlpinkafeld.projectmanager.dao.PMDBHelper;
import at.htlpinkafeld.projectmanager.dao.projectDAO.ProjectDAO;
import at.htlpinkafeld.projectmanager.dao.projectDAO.ProjectSQL_DAO;
import at.htlpinkafeld.projectmanager.pojo.Activity;
import at.htlpinkafeld.projectmanager.pojo.Project;
import at.htlpinkafeld.projectmanager.service.ServiceClass;


/**
 * Created by User on 07.03.2016.
 */
public class ActivitySQL_DAO extends BaseSQL_DAO<Activity> implements ActivityDAO{

    ProjectDAO projectDAO;

    public ActivitySQL_DAO(PMDBHelper pmDBHelper) {
        super(pmDBHelper, ActivityTable.TABLE_NAME, ActivityTable.ALL_COLUMNS, ActivityTable.COLUMN_AID);
        projectDAO=new ProjectSQL_DAO(pmDBHelper);
    }

    @Override
    protected ContentValues entityToContentValues(Activity a) {
        ContentValues cv = new ContentValues();
        if(a.getId()!=null)
            cv.put(ActivityTable.COLUMN_AID, a.getId());
        cv.put(ActivityTable.COLUMN_PID, a.getProj().getId());
        cv.put(ActivityTable.COLUMN_ANAME, a.getName());
        cv.put(ActivityTable.COLUMN_APRIORITY, a.getPrior());
        cv.put(ActivityTable.COLUMN_ASTARTDATE, a.getStartDat().toString());
        cv.put(ActivityTable.COLUMN_AENDDATE, a.getEndDat().toString());
        cv.put(ActivityTable.COLUMN_AEFFORT, a.getEffort());
        return cv;
    }

    @Override
    protected void cursorToEntity(Cursor cursor, Activity a) {
        a.setId(cursor.getInt(0));
        for(Project p:projectDAO.getEntityList()) {
            if (p.getId() == cursor.getInt(1))
                a.setProj(p);
        }
        a.setName(cursor.getString(2));
        a.setPrior(cursor.getString(3));
        a.setStartDat(new Date(cursor.getInt(4)));
        a.setEndDat(new Date(cursor.getInt(5)));
        a.setEffort(cursor.getDouble(6));
    }

    @Override
    protected Activity createEntity() {
        return new Activity();
    }


}
