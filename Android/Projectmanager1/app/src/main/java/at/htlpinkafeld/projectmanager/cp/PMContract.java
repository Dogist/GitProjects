package at.htlpinkafeld.projectmanager.cp;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by User on 11.04.2016.
 */
public class PMContract {
    /**
     * The authority of the lentitems provider.
     */
    public static final String AUTHORITY = "at.htlpinkafeld.projectmanager";

    /**
     * The content URI for the top-level
     * lentitems authority.
     */
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    /**
     * permission to set in the attribute android:readPermission
     * of the provider tag
     */
    public static final String PERMISSION_READ = AUTHORITY + ".READ";
    /**
     * permission to set in the attribute android:writePermission
     * of the provider tag
     */
    public static final String PERMISSION_WRITE =AUTHORITY + ".WRITE";

    public static final class Activities implements BaseColumns {
        public static final String TABLE_NAME = "Activity";

        public static final String PID = "Project_ID";
        public static final String ANAME = "ActName";
        public static final String APRIORITY = "ActPriority";
        public static final String ASTARTDATE = "ActStartDate";
        public static final String AENDDATE = "ActEndDate";
        public static final String AEFFORT = "ActEffort";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(PMContract.CONTENT_URI, TABLE_NAME);

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.at.htlpinkafeld.projectmanager_activities";

        public static final String CONTENT_ACTIVITY_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/vnd.at.htlpinkafeld.projectmanager_activities";

        public static final String[] PROJECTION_ALL = {_ID,PID , ANAME,APRIORITY,ASTARTDATE, AENDDATE, AEFFORT};

        public static final String SORT_ORDER_DEFAULT = _ID + " ASC";
    }

    public static final class Projects implements BaseColumns {
        public static final String TABLE_NAME = "Activity";

        public static final String PNAME = "PRNAME";
        public static final String PCONTR = "PRCONTRACTOR";
        public static final String PRPROCMOD = "PRPROCMOD";
        public static final String PRSTARTDATE = "PRSTARTDATE";
        public static final String PRENDDATE = "PRENDDATE";
        public static final String PRDESC = "PRDESC";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(PMContract.CONTENT_URI, TABLE_NAME);

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.at.htlpinkafeld.projectmanager_projects";

        public static final String CONTENT_PROJECTS_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/vnd.at.htlpinkafeld.projectmanager_projects";

        public static final String[] PROJECTION_ALL = {_ID, PNAME , PCONTR,PRPROCMOD,PRSTARTDATE, PRENDDATE, PRDESC};

        public static final String SORT_ORDER_DEFAULT = PNAME + " ASC";
    }

    public static final class TeamMembers implements BaseColumns {
        public static final String TABLE_NAME = "TeamMember";

        public static final String TMTITLE = "TMTitle";
        public static final String TMJOB = "TMJob";
        public static final String TMFIRST_NAME = "TMFirst_Name";
        public static final String TMLAST_NAME = "TMLast_Name";
        public static final String TMDEPARTMENT = "TMDepartment";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(PMContract.CONTENT_URI, TABLE_NAME);

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.at.htlpinkafeld.projectmanager_teammembers";

        public static final String CONTENT_MEMBERS_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/vnd.at.htlpinkafeld.projectmanager_teammembers";

        public static final String[] PROJECTION_ALL ={_ID,TMTITLE,TMJOB,TMFIRST_NAME,TMLAST_NAME,TMDEPARTMENT};

        public static final String SORT_ORDER_DEFAULT = TMLAST_NAME + " ASC, " + TMFIRST_NAME + "ASC";

    }

}
