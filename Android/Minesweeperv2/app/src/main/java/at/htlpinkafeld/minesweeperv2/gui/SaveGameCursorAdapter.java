package at.htlpinkafeld.minesweeperv2.gui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import at.htlpinkafeld.minesweeperv2.R;
import at.htlpinkafeld.minesweeperv2.cp.SaveGameTable;
import at.htlpinkafeld.minesweeperv2.util.MyChronometer;

/**
 * Created by User on 26.05.2016.
 */
public class SaveGameCursorAdapter extends CursorAdapter {

    public SaveGameCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.savegamelist_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView idV = (TextView) view.findViewById(R.id.savegame_item_id);
        TextView timeV = (TextView) view.findViewById(R.id.savegame_item_time);
        TextView minesV = (TextView) view.findViewById(R.id.savegame_item_mines);
        TextView uncoveredV = (TextView) view.findViewById(R.id.savegame_item_uncovered);

        idV.setText(cursor.getString(cursor.getColumnIndexOrThrow(SaveGameTable.COLUMN_SGID)));
        timeV.setText(MyChronometer.millToChronometerLayout(cursor.getLong(cursor.getColumnIndexOrThrow(SaveGameTable.COLUMN_TIME))));
        minesV.setText(cursor.getString(cursor.getColumnIndexOrThrow(SaveGameTable.COLUMN_NUMMINES)));
        uncoveredV.setText(cursor.getString(cursor.getColumnIndexOrThrow(SaveGameTable.COLUMN_FIELDSUNCOVERED)));
    }
}
