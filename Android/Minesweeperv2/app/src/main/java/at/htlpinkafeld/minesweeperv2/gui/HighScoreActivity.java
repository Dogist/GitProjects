package at.htlpinkafeld.minesweeperv2.gui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import at.htlpinkafeld.minesweeperv2.R;
import at.htlpinkafeld.minesweeperv2.service.DBService;

public class HighScoreActivity extends AppCompatActivity {

    public static final String NEW_SCORE = "new_score";
    public static final String TIME = "time";

    private DBService dbService;
    private ListView saveHSList;
    private HighScoreAdapter ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);


        dbService = new DBService(getContentResolver());
        saveHSList = (ListView) findViewById(R.id.highscore_list);


        ba = new HighScoreAdapter(dbService.getScores(), this);

        View header = getLayoutInflater().inflate(R.layout.highscorelist_header, null);
        saveHSList.addHeaderView(header);

        saveHSList.setAdapter(ba);

        if (getIntent().getBooleanExtra(NEW_SCORE, false) && getIntent().getLongExtra(TIME, -1) != -1) {
            final long time = getIntent().getLongExtra(TIME, 0);

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Playername:");
            alertDialog.setMessage("Enter Name");

            final EditText input = new EditText(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input);

            alertDialog.setPositiveButton("Save Score",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String name = input.getText().toString();
                            if (!name.isEmpty()) {
                                dbService.saveScore(time, name);
                                ba.setScores(dbService.getScores());
                                ba.notifyDataSetChanged();
                            }
                        }
                    });

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });


            alertDialog.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void returnAction(View v) {
        finish();
    }
}
