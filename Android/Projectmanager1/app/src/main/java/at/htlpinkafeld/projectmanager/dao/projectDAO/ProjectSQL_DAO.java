package at.htlpinkafeld.projectmanager.dao.projectDAO;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Date;

import at.htlpinkafeld.projectmanager.dao.BaseSQL_DAO;
import at.htlpinkafeld.projectmanager.dao.PMDBHelper;
import at.htlpinkafeld.projectmanager.pojo.Project;


/**
 * Created by User on 29.02.2016.
 */
public class ProjectSQL_DAO extends BaseSQL_DAO<Project> implements ProjectDAO{

    public ProjectSQL_DAO(PMDBHelper pmDBHelper) {
        super(pmDBHelper, ProjectTable.TABLE_NAME, ProjectTable.ALL_COLUMNS, ProjectTable.COLUMN_PID);
    }

    @Override
    protected ContentValues entityToContentValues(Project p) {
        ContentValues cv = new ContentValues();
        if(p.getId()!=null)
            cv.put(ProjectTable.COLUMN_PID, p.getId());
        cv.put(ProjectTable.COLUMN_PNAME, p.getName());
        cv.put(ProjectTable.COLUMN_PCONTR, p.getContr());
        cv.put(ProjectTable.COLUMN_PRPROCMOD, p.getProcMod());
        cv.put(ProjectTable.COLUMN_PRSTARTDATE, p.getStartD().toString());
        cv.put(ProjectTable.COLUMN_PRENDDATE, p.getEndD().toString());
        cv.put(ProjectTable.COLUMN_PRDESC, p.getDesc());
        return cv;
    }

    @Override
    protected void cursorToEntity(Cursor cursor, Project p) {
        p.setId(cursor.getInt(0));
        p.setName(cursor.getString(1));
        p.setContr(cursor.getString(2));
        p.setContr(cursor.getString(3));
        p.setStartD(new Date(cursor.getInt(4)));
        p.setEndD(new Date(cursor.getInt(5)));
        p.setDesc(cursor.getString(6));
    }

    @Override
    protected Project createEntity() {
        return new Project();
    }
}
