package at.htlpinkafeld.minesweeperv2.gui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import at.htlpinkafeld.minesweeperv2.R;
import at.htlpinkafeld.minesweeperv2.pojo.MineField;
import at.htlpinkafeld.minesweeperv2.service.DBService;
import at.htlpinkafeld.minesweeperv2.service.FieldServiceClass;
import at.htlpinkafeld.minesweeperv2.util.MyChronometer;


public class MineActivity extends AppCompatActivity {

    public static final String START_TIME_EXTRA = "start_time";
    private static long startTime = 0;
    private MyChronometer timer;
    private DBService dbService;
    private GridView gameGrid;
    private boolean gameRunning = true;

    private AlertDialog endGameDialog;

    public static void setStartTime(long time) {
        startTime = time;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if ((startTime = getIntent().getLongExtra(START_TIME_EXTRA, -1)) == -1) {
            FieldServiceClass.forceRecreate();
            startTime = 0;
        }

        gameGrid = (GridView) findViewById(R.id.game_grid);
        gameGrid.setNumColumns(FieldServiceClass.getWidth());
        GridBoardAdapter gba = new GridBoardAdapter(this);
        gameGrid.setAdapter(gba);

        //Listener to uncover fields, which have no flag. Also handles winning and loosing
        //it also starts the chronometer with 0 or the defined start time from a saved game
        gameGrid.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MineField f = (MineField) parent.getItemAtPosition(position);
                if (!timer.isRunning() && gameRunning) {
                    timer.setBase(SystemClock.elapsedRealtime() - startTime);
                    timer.start();
                }
                if (!f.isFlagged() && gameRunning) {
                    FieldServiceClass.getFieldServiceClass().clearAround(position % FieldServiceClass.getWidth(), position / FieldServiceClass.getWidth());
                    ((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();

                    if (f.isMine()) {
                        FieldServiceClass.getFieldServiceClass().setMinesCovered(false);
                        endGameDialog = createGameEndDialog("You Lost");
                        endGameDialog.show();
                    } else if (FieldServiceClass.getWidth() * FieldServiceClass.getHeight() - FieldServiceClass.getNumMines() == FieldServiceClass.getFieldServiceClass().getFieldsUncovered()) {
                        endGameDialog = createGameEndDialog("You Won");
                        endGameDialog.show();

                    }
                } else if (!gameRunning) {
                    endGameDialog.show();
                }
            }
        });
        //used to set or unset flags
        gameGrid.setOnItemLongClickListener(new GridView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                MineField f = (MineField) parent.getItemAtPosition(position);
                if (f.isCovered()) {
                    if (!timer.isRunning()) {
                        timer.setBase(SystemClock.elapsedRealtime() - startTime);
                        timer.start();
                    }
                    f.setFlagged(!f.isFlagged());
                    ((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });

        timer = (MyChronometer) findViewById(R.id.game_timer);

        timer.setText(MyChronometer.millToChronometerLayout(startTime));

        dbService = new DBService(getContentResolver());

    }

    //creates the standard dialog at the end of the game
    private AlertDialog createGameEndDialog(String message) {

        gameRunning = false;

        if (timer.isRunning()) {
            this.timer.stop();
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(message);

        dialogBuilder.setPositiveButton("play again?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartGame();
                gameRunning = true;
            }
        });
        dialogBuilder.setNegativeButton("different settings?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialogBuilder.setNeutralButton("save score", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getBaseContext(), HighScoreActivity.class);
                i.putExtra(HighScoreActivity.NEW_SCORE, true);
                i.putExtra(HighScoreActivity.TIME, SystemClock.elapsedRealtime() - timer.getBase());
                startActivity(i);
                finish();
            }
        });
        return dialogBuilder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_game:
                if (gameRunning) {
                    dbService.saveGame(SystemClock.elapsedRealtime() - timer.getBase());
                    return true;
                } else {
                    Toast.makeText(this, "You can't save a finished game", Toast.LENGTH_LONG).show();
                    return false;
                }
            case R.id.action_retry:
                restartGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer.isRunning()) {
            this.timer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startTime = -1;
    }

    private void restartTimer() {
        if (timer.isRunning())
            timer.stop();
        startTime = 0;
        timer.setBase(SystemClock.elapsedRealtime());
        timer.setText("00:00");
    }

    private void restartGame() {
        FieldServiceClass.getFieldServiceClass().reset();
        ((BaseAdapter) gameGrid.getAdapter()).notifyDataSetChanged();
        restartTimer();
        gameGrid.setEnabled(true);
    }
}