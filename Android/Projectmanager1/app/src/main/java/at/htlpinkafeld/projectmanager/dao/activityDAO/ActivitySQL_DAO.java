package at.htlpinkafeld.projectmanager.dao.activityDAO;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Date;

import at.htlpinkafeld.projectmanager.dao.BaseSQL_DAO;
import at.htlpinkafeld.projectmanager.dao.PMDBHelper;
import at.htlpinkafeld.projectmanager.pojo.Activity;


/**
 * Created by User on 07.03.2016.
 */
public class ActivitySQL_DAO extends BaseSQL_DAO<Activity> implements ActivityDAO{

    public ActivitySQL_DAO(PMDBHelper pmDBHelper) {
        super(pmDBHelper, ActivityTable.TABLE_NAME, ActivityTable.ALL_COLUMNS, ActivityTable.COLUMN_AID);
    }

    @Override
    protected ContentValues entityToContentValues(Activity a) {
        ContentValues cv = new ContentValues();
        if(a.getId()!=null)
            cv.put(ActivityTable.COLUMN_AID, a.getId());
        cv.put(ActivityTable.COLUMN_PID, a.getProj());
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
        a.setProj(cursor.getInt(1));
        a.setName(cursor.getString(2));
        a.setPrior(cursor.getString(3));
        a.setStartDat(new Date(cursor.getInt(4)));
        a.setEndDat(new Date(cursor.getInt(5)));
        a.setEffort(cursor.getString(6));
    }

    @Override
    protected Activity createEntity() {
        return new Activity();
    }


}
