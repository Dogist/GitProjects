package at.htlpinkafeld.projectmanager.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import at.htlpinkafeld.projectmanager.R;

/**
 * Created by tq on 16-02-01.
 */
public class PMPreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_general);
    }

}