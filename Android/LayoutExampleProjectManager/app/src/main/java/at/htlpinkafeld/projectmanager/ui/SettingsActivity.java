package at.htlpinkafeld.projectmanager.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tq on 16-02-01.
 */
public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PMPreferenceFragment())
                .commit();
    }
}
