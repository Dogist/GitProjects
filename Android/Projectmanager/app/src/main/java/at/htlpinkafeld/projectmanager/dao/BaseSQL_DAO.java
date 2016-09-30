package at.htlpinkafeld.projectmanager.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 07.03.2016.
 */
public abstract class BaseSQL_DAO<T> implements BaseDAO<T> {

    private final String COLUMN_ID;

    private final PMDBHelper pmDatabaseHelper;
    private final String tableName;
    private final String[] allColumns;

    public BaseSQL_DAO(PMDBHelper pmDatabaseHelper, String tableName, String[] allColumns, String COLUMN_ID) {
        this.COLUMN_ID = COLUMN_ID;
        this.pmDatabaseHelper = pmDatabaseHelper;
        this.tableName = tableName;
        this.allColumns = allColumns;
    }

    @Override
    public void insert(T entity) {
        SQLiteDatabase database = pmDatabaseHelper.getWritableDatabase();
        ContentValues values = entityToContentValues(entity);

        long insertId = database.insert(tableName, null, values);

        Cursor cursor = database.query(tableName,
                allColumns, COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        //store the id, create in the database, in the entity
        cursorToEntity(cursor, entity);
        cursor.close();
    }

    @Override
    public void save(T entity) {
        SQLiteDatabase database = pmDatabaseHelper.getWritableDatabase();
        ContentValues values = entityToContentValues(entity);
        database.replace(tableName, null, values);
    }

    @Override
    public void delete(Long id) {
        SQLiteDatabase database = pmDatabaseHelper.getWritableDatabase();
        database.delete(tableName, COLUMN_ID + " = " + id, null);
    }

    @Override
    public List<T> getEntityList() {
        SQLiteDatabase database = pmDatabaseHelper.getReadableDatabase();
        List<T> entityList = new ArrayList<>();

        Cursor cursor = database.query(tableName, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            T entity = createEntity();
            cursorToEntity(cursor, entity);
            entityList.add(entity);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return entityList;
    }

    protected abstract ContentValues entityToContentValues(T entity);

    protected abstract void cursorToEntity(Cursor cursor, T entity);

    protected abstract T createEntity();


}
