package at.htlpinkafeld.projectmanager.cp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import at.htlpinkafeld.projectmanager.cp.PMContract.Activities;
import at.htlpinkafeld.projectmanager.cp.PMContract.Projects;
import at.htlpinkafeld.projectmanager.cp.PMContract.TeamMembers;
import at.htlpinkafeld.projectmanager.dao.PMDBHelper;
import at.htlpinkafeld.projectmanager.pojo.TeamMember;


/**
 * Created by User on 11.04.2016.
 */
public class PMProvider extends ContentProvider {

    private static final int ACTIVITY_LIST =1;
    private static final int ACTIVITY_ID =2;
    private static final int PROJECT_LIST =5;
    private static final int PROJECT_ID=6;
    private static final int TEAMMEMBER_LIST =10;
    private static final int TEAMMEMBER_ID =11;
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PMContract.AUTHORITY,"activities",ACTIVITY_LIST);
        URI_MATCHER.addURI(PMContract.AUTHORITY,"activities/#",ACTIVITY_ID);
        URI_MATCHER.addURI(PMContract.AUTHORITY,"projects",PROJECT_LIST);
        URI_MATCHER.addURI(PMContract.AUTHORITY,"projects/#",PROJECT_ID);
        URI_MATCHER.addURI(PMContract.AUTHORITY,"teammembers",TEAMMEMBER_LIST);
        URI_MATCHER.addURI(PMContract.AUTHORITY,"teammembers/#",TEAMMEMBER_ID);
    }

    private PMDBHelper pmDatabaseHelper=null;

    public PMProvider() {

    }

    @Override
    public boolean onCreate() {
        pmDatabaseHelper = new PMDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = pmDatabaseHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        boolean useAuthorityUri = false;
        switch (URI_MATCHER.match(uri)) {
            case ACTIVITY_LIST:
                builder.setTables(PMContract.Activities.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = PMContract.Activities.SORT_ORDER_DEFAULT;
                }
                break;
            case ACTIVITY_ID:
                builder.setTables(Activities.TABLE_NAME);
                // limit query to one row at most:
                builder.appendWhere(Activities._ID + " = " + uri.getLastPathSegment());
                break;
            case PROJECT_LIST:
                builder.setTables(Projects.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = Projects.SORT_ORDER_DEFAULT;
                }
                break;
            case PROJECT_ID:
                builder.setTables(Projects.TABLE_NAME);
                // limit query to one row at most:
                builder.appendWhere(Projects._ID + " = " + uri.getLastPathSegment());
                break;
            case TEAMMEMBER_LIST:
                builder.setTables(TeamMembers.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = TeamMembers.SORT_ORDER_DEFAULT;
                }
                useAuthorityUri = true;
                break;
            case TEAMMEMBER_ID:
                builder.setTables(TeamMembers.TABLE_NAME);
                // limit query to one row at most:
                builder.appendWhere(TeamMembers._ID + " = " + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        Cursor cursor = builder.query(
                        db,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
        // if we want to be notified of any changes:
        if (useAuthorityUri) {
            cursor.setNotificationUri(getContext().getContentResolver(), PMContract.CONTENT_URI);
        }
        else {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;

    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case ACTIVITY_LIST:
                return Activities.CONTENT_TYPE;
            case ACTIVITY_ID:
                return Activities.CONTENT_ACTIVITY_TYPE;
            case PROJECT_LIST:
                return Projects.CONTENT_TYPE;
            case PROJECT_ID:
                return Projects.CONTENT_PROJECTS_TYPE;
            case TEAMMEMBER_LIST:
                return TeamMembers.CONTENT_TYPE;
            case TEAMMEMBER_ID:
                return TeamMembers.CONTENT_MEMBERS_TYPE;
            default:
                return null;
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if(URI_MATCHER.match(uri)!=ACTIVITY_LIST && URI_MATCHER.match(uri)!=PROJECT_LIST && URI_MATCHER.match(uri)!=ACTIVITY_LIST)
            throw new IllegalArgumentException("Unsupported URI for insertion: " + uri);
        SQLiteDatabase db = pmDatabaseHelper.getWritableDatabase();

        if (URI_MATCHER.match(uri)==ACTIVITY_LIST){
            long id=db.insert(Activities.TABLE_NAME,null,values);
            return getUriForId(id,uri);
        } else if(URI_MATCHER.match(uri)==PROJECT_LIST){
            long id=db.insert(Projects.TABLE_NAME,null,values);
            return getUriForId(id,uri);
        }else{
            long id=db.insert(TeamMembers.TABLE_NAME,null,values);
            return getUriForId(id,uri);
        }
    }

    private Uri getUriForId(long id, Uri uri) {
        if (id > 0) {
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            return itemUri;
        }
        // s.th. went wrong:
        throw new SQLException("Problem while inserting into uri: " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = pmDatabaseHelper.getWritableDatabase();
        int delCount = 0;
        String idStr, where;

        switch (URI_MATCHER.match(uri)) {
            case ACTIVITY_LIST:
                delCount = db.delete(
                        Activities.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case ACTIVITY_ID:
                idStr = uri.getLastPathSegment();
                where = Activities._ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                delCount = db.delete(
                        Activities.TABLE_NAME,
                        where,
                        selectionArgs);
                break;
            case PROJECT_LIST:
                delCount = db.delete(
                        Projects.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case PROJECT_ID:
                idStr = uri.getLastPathSegment();
                where = Projects._ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                delCount = db.delete(
                        Projects.TABLE_NAME,
                        where,
                        selectionArgs);
                break;
            case TEAMMEMBER_LIST:
                delCount = db.delete(
                        TeamMembers.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case TEAMMEMBER_ID:
                idStr = uri.getLastPathSegment();
                where = TeamMembers._ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                delCount = db.delete(
                        TeamMembers.TABLE_NAME,
                        where,
                        selectionArgs);
                break;
        }
        // notify all listeners of changes:
        //if (delCount > 0 && !isInBatchMode()) {
        //    getContext().getContentResolver().notifyChange(uri, null);
        //}
        return delCount;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = pmDatabaseHelper.getWritableDatabase();
        int updateCount = 0;
        String idStr, where;

        switch (URI_MATCHER.match(uri)) {
            case ACTIVITY_LIST:
                updateCount = db.update(
                        Activities.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            case ACTIVITY_ID:
                idStr = uri.getLastPathSegment();
                where = Activities._ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                updateCount = db.update(
                        Activities.TABLE_NAME,
                        values,
                        where,
                        selectionArgs);
                break;
            case PROJECT_LIST:
                updateCount = db.update(
                        Projects.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            case PROJECT_ID:
                idStr = uri.getLastPathSegment();
                where = Activities._ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                updateCount = db.update(
                        Projects.TABLE_NAME,
                        values,
                        where,
                        selectionArgs);
                break;
            case TEAMMEMBER_LIST:
                updateCount = db.update(
                        TeamMembers.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            case TEAMMEMBER_ID:
                idStr = uri.getLastPathSegment();
                where = Activities._ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                updateCount = db.update(
                        TeamMembers.TABLE_NAME,
                        values,
                        where,
                        selectionArgs);
                break;
        }
        // notify all listeners of changes:
        //if (updateCount > 0 && !isInBatchMode()) {
        //    getContext().getContentResolver().notifyChange(uri, null);
        //}
        return updateCount;

    }
}
