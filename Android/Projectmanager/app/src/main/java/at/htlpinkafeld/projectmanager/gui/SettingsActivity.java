package at.htlpinkafeld.projectmanager.gui;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by User on 04.04.2016.
 */
public class SettingsActivity extends Activity{
    public static final String PROCESS_MODEL="pref_key_process_model_default";
    public static final String ACTIVITY_PRIORITY="pref_key_default_actPriority";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PMPreferenceFragment())
                .commit();
    }
}
