package com.projekt;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

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
		boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

		if(key.equals(KEY_LISTPREF)){
			if(isSDPresent){
				Toast.makeText(getActivity(), "SD vorhanden", Toast.LENGTH_LONG).show();
				listpref.setSummary(listpref.getEntry());
			}
			else{
				if(sp.getString(key, "kein Wert").equals("extern")){
					listpref.setSummary("intern");
					listpref.setValueIndex(0);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString(key, "intern");
					editor.commit();
					
					Toast.makeText(getActivity(), "No SD-Card available", Toast.LENGTH_LONG).show();
				}
				else if(sp.getString(key, "kein Wert").equals("intern")){
					listpref.setSummary("intern");				
				}
				else
					Toast.makeText(getActivity(), "SharedPref kein Wert", Toast.LENGTH_LONG).show();
			}
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
