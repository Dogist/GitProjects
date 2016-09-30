package at.htlpinkafeld.projectmanager.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import at.htlpinkafeld.projectmanager.R;

public class DurationCalculator extends AppCompatActivity {
    public static final String EXTRA_START = "at.htlpinkafeld.projectmanager.startd";
    public static final String EXTRA_END = "at.htlpinkafeld.projectmanager.endd";
    EditText endET;
    EditText durationET;
    private Date startD = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration_calculator);
        EditText startET = (EditText) findViewById(R.id.durcalc_start);
        endET = (EditText) findViewById(R.id.durcalc_end);
        durationET = (EditText) findViewById(R.id.durcalc_dur);
        Intent intent = getIntent();
        Date endD = null;
        if (intent.hasExtra(EXTRA_START)) {
            startD = (Date) intent.getSerializableExtra(EXTRA_START);
            startET.setText(AbstrDetailFragment.dateFormat.format(startD));
        }
        if (intent.hasExtra(EXTRA_END)) {
            endD = (Date) intent.getSerializableExtra(EXTRA_END);
            endET.setText(AbstrDetailFragment.dateFormat.format(endD));
        }
        if (endD != null && startD != null)
            durationET.setText((int) ((endD.getTime() - startD.getTime()) / 1000 / 60 / 60 / 24));

        endET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Date endD = AbstrDetailFragment.dateFormat.parse(s.toString());
                    durationET.setText((int) ((endD.getTime() - startD.getTime()) / 1000 / 60 / 60 / 24));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        durationET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int duration = Integer.getInteger(s.toString());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startD);
                calendar.add(Calendar.DATE, duration);
                endET.setText(AbstrDetailFragment.dateFormat.format(calendar.getTime()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void save(View v) {
        Intent retInt = new Intent();
        try {
            retInt.putExtra(EXTRA_END, AbstrDetailFragment.dateFormat.parse(endET.toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setResult(RESULT_OK, retInt);
        finish();
    }

    public void cancel(View v) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_duration_calculator, menu);
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
}
