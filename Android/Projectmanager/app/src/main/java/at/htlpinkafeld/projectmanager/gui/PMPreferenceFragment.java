package at.htlpinkafeld.projectmanager.gui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import at.htlpinkafeld.projectmanager.R;


/**
 * Created by User on 04.04.2016.
 */
public class PMPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_general);
    }
}
