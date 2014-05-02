package com.projekt;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {
	private ListPreference listpref;
	private static final String KEY_LISTPREF = "saving";
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings);
        
		PreferenceManager.setDefaultValues(getActivity(), R.xml.settings, false);
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		sharedPref.registerOnSharedPreferenceChangeListener(this);


		listpref = (ListPreference)findPreference(KEY_LISTPREF);
		listpref.setSummary(listpref.getEntry());

    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sp, String key) {
		if(key.equals(KEY_LISTPREF)){
			listpref.setSummary(listpref.getEntry());
		}
		
	}
	
	@Override     
	public void onResume() {
		super.onResume();          
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);     
	}



	@Override
	public void onPause() {         
		super.onPause();          
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

	}
}
