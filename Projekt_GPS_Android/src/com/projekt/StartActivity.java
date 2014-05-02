package com.projekt;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class StartActivity extends Activity implements OnClickListener {
	private static final String KEY_LISTPREF = "saving";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		Button startButton = (Button)findViewById(R.id.button_start);
		Button settingsButton = (Button)findViewById(R.id.button_settings);
		startButton.setOnClickListener(this);
		settingsButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {

		switch(view.getId()){
		case R.id.button_start: 
			startActivity(new Intent(this, MainActivity.class));
			break;
		case R.id.button_settings:
			startActivity(new Intent(this, SettingsActivity.class));
			break;
		}
	}

	@SuppressWarnings("unused")
	protected void onStart(){
		super.onStart();

		/*
		 * Für Dominic:
		 * Hier ein Beispiel wie man auf die Einstellungen für Inter/Extern zugreifen kann
		 * Muss nicht in onStart() sein, kann überall aufgerufen werden
		 */
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String storedPreference = pref.getString(KEY_LISTPREF, "kein Wert");	//"kein Wert" wird ausgegeben, wenn kein String für diesen Key gefunden wurde, ein Default-Wert sozusagen


	}
}
