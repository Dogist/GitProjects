package at.htlpinkafeld.minesweeperv2.gui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import at.htlpinkafeld.minesweeperv2.R;
import at.htlpinkafeld.minesweeperv2.cp.SaveGameTable;
import at.htlpinkafeld.minesweeperv2.pojo.Game;
import at.htlpinkafeld.minesweeperv2.pojo.MineField;
import at.htlpinkafeld.minesweeperv2.service.DBService;
import at.htlpinkafeld.minesweeperv2.service.FieldServiceClass;

public class Select_SavegameActivity extends AppCompatActivity {

    private DBService dbService;
    private ListView saveGList;
    private Cursor cursor;
    private int selectPosition = -1;
    private CursorAdapter sca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__savegame);
        dbService = new DBService(getContentResolver());
        saveGList = (ListView) findViewById(R.id.savegame_list);

        View header = getLayoutInflater().inflate(R.layout.savegamelist_header, null);
        saveGList.addHeaderView(header);

        cursor = dbService.getSaveGames();

        saveGList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPosition = position - saveGList.getHeaderViewsCount();
            }
        });
        sca = new SaveGameCursorAdapter(this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        saveGList.setAdapter(sca);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void deleteSelectedGame(View v) {
        dbService.deleteSaveGame(saveGList.getItemIdAtPosition(selectPosition + saveGList.getHeaderViewsCount()));
        sca.swapCursor(dbService.getSaveGames());
    }

    public void loadSelectedGame(View v) {
        Gson gson = new Gson();
        if (selectPosition >= 0) {
            cursor.moveToPosition(selectPosition);
            if (!cursor.isBeforeFirst()) {
                Game g = new Game(cursor.getInt(cursor.getColumnIndexOrThrow(SaveGameTable.COLUMN_SGID)), gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(SaveGameTable.COLUMN_BOARD)), MineField[][].class),
                        cursor.getInt(cursor.getColumnIndexOrThrow(SaveGameTable.COLUMN_NUMMINES)), cursor.getInt(cursor.getColumnIndexOrThrow(SaveGameTable.COLUMN_FIELDSUNCOVERED)), cursor.getLong(cursor.getColumnIndexOrThrow(SaveGameTable.COLUMN_TIME)));
                FieldServiceClass.getFieldServiceClass().loadGame(g);
                Intent intent = new Intent(v.getContext(), MineActivity.class);
                intent.putExtra(MineActivity.START_TIME_EXTRA, g.getTime());
                startActivity(intent);
                finish();
            }
        }
    }
}
