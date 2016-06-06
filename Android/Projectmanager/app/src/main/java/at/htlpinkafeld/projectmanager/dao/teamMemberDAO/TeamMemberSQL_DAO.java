package at.htlpinkafeld.projectmanager.dao.teamMemberDAO;

import android.content.ContentValues;
import android.database.Cursor;

import at.htlpinkafeld.projectmanager.dao.BaseSQL_DAO;
import at.htlpinkafeld.projectmanager.dao.PMDBHelper;
import at.htlpinkafeld.projectmanager.pojo.TeamMember;


/**
 * Created by User on 14.03.2016.
 */
public class TeamMemberSQL_DAO extends BaseSQL_DAO<TeamMember> implements TeamMemberDAO {


    public TeamMemberSQL_DAO(PMDBHelper pmDatabaseHelper) {
        super(pmDatabaseHelper, TeamMemberTable.TABLE_NAME, TeamMemberTable.ALL_COLUMNS, TeamMemberTable.COLUMN_TMID);
    }

    @Override
    protected ContentValues entityToContentValues(TeamMember t) {
        ContentValues cv = new ContentValues();
        if(t.getId()!=null&&t.getId()!=-1)
            cv.put(TeamMemberTable.COLUMN_TMID, t.getId());
        cv.put(TeamMemberTable.COLUMN_TMTitle, t.getTitle());
        cv.put(TeamMemberTable.COLUMN_TMJob, t.getJob());
        cv.put(TeamMemberTable.COLUMN_TMFirst_Name, t.getFname());
        cv.put(TeamMemberTable.COLUMN_TMLast_Name, t.getLname());
        cv.put(TeamMemberTable.COLUMN_TMDepartment, t.getDept());

        return cv;
    }

    @Override
    protected void cursorToEntity(Cursor cursor, TeamMember t) {
        t.setId(cursor.getInt(0));
        t.setTitle(cursor.getString(1));
        t.setJob(cursor.getString(2));
        t.setFname(cursor.getString(3));
        t.setLname(cursor.getString(4));
        t.setDept(cursor.getString(5));
    }

    @Override
    protected TeamMember createEntity() {
        return new TeamMember();
    }
}
