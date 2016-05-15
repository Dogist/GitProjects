package htlpinkafeld.at.minesweeper.gui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import htlpinkafeld.at.minesweeper.R;
import htlpinkafeld.at.minesweeper.pojo.MineField;
import htlpinkafeld.at.minesweeper.service.FieldServiceClass;
import htlpinkafeld.at.minesweeper.util.MyChronometer;

public class MineActivity extends AppCompatActivity {

    private MyChronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FieldServiceClass.forceRecreate();

        GridView grid = (GridView) findViewById(R.id.game_grid);
        grid.setNumColumns(FieldServiceClass.getWidth());
        GridBoardAdapter gba = new GridBoardAdapter(this);
        grid.setAdapter(gba);

        //Listener to uncover fields, which have no flag. Also handles winning and loosing
        grid.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MineField f = (MineField) parent.getItemAtPosition(position);
                if (!timer.isRunning()) {
                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();
                }
                if (!f.isFlagged()) {
                    FieldServiceClass.getFieldServiceClass().clearAround(position % FieldServiceClass.getWidth(), position / FieldServiceClass.getWidth());
                    ((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();

                    if (f.isMine()) {
                        FieldServiceClass.getFieldServiceClass().setMinesCovered(false);
                        AlertDialog alertDialog = createGameEndDialog("You Lost");
                        alertDialog.show();
                    } else if (FieldServiceClass.getWidth() * FieldServiceClass.getHeight() - FieldServiceClass.getNumMines() == FieldServiceClass.getFieldServiceClass().getFieldsUncovered()) {
                        AlertDialog alertDialog = createGameEndDialog("You Won");
                        alertDialog.show();

                    }
                }
            }
        });
        //used to set or unset flags
        grid.setOnItemLongClickListener(new GridView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MineField f = (MineField) parent.getItemAtPosition(position);
                if (f.isCovered()) {
                    f.setFlagged(!f.isFlagged());
                    ((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });

        timer = (MyChronometer) findViewById(R.id.game_timer);
    }

    //creates the standard dialog at the end of the game
    private AlertDialog createGameEndDialog(String message) {

        if (timer.isRunning()) {
            this.timer.stop();
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(message);

        dialogBuilder.setPositiveButton("play again?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FieldServiceClass.getFieldServiceClass().reset();
                GridView grid = (GridView) findViewById(R.id.game_grid);
                ((BaseAdapter) grid.getAdapter()).notifyDataSetChanged();
                timer.setText("00:00");
            }
        });
        dialogBuilder.setNegativeButton("different settings?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialogBuilder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((GridView) findViewById(R.id.game_grid)).setEnabled(false);
            }
        });
        return dialogBuilder.create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer.isRunning()) {
            this.timer.stop();
        }
    }
}
