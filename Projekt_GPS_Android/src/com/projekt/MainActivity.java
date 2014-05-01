package com.projekt;

import com.projekt.R;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


public class MainActivity extends Activity {
	public static Context appContext;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//ActionBar gets initiated
		ActionBar actionbar = getActionBar();
		//Tell the ActionBar we want to use Tabs.
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//initiating  tabs and set text to it.
		ActionBar.Tab all_Tab = actionbar.newTab().setText("ALL");
		ActionBar.Tab acc_Tab = actionbar.newTab().setText("ACC");
		ActionBar.Tab winkel_Tab = actionbar.newTab().setText("WINKEL");
		ActionBar.Tab rpm_Tab = actionbar.newTab().setText("RPM");
		ActionBar.Tab gps_Tab = actionbar.newTab().setText("GPS");
		//create the fragments we want to use for display content
		Tab_all all_fragment = new Tab_all();
		Tab_acc acc_fragment = new Tab_acc();
		Tab_winkel winkel_fragment = new Tab_winkel();
		Tab_rpm rpm_fragment = new Tab_rpm();
		Tab_gps gps_fragment = new Tab_gps();

		//set the Tab listener. Now we can listen for clicks.
		all_Tab.setTabListener((TabListener) new MyTabsListener(all_fragment));
		acc_Tab.setTabListener((TabListener) new MyTabsListener(acc_fragment));
		winkel_Tab.setTabListener((TabListener) new MyTabsListener(winkel_fragment));
		rpm_Tab.setTabListener((TabListener) new MyTabsListener(rpm_fragment));
		gps_Tab.setTabListener((TabListener) new MyTabsListener(gps_fragment));

		//add the tabs to the actionbar
		actionbar.addTab(all_Tab);
		actionbar.addTab(acc_Tab);
		actionbar.addTab(winkel_Tab);
		actionbar.addTab(rpm_Tab);
		actionbar.addTab(gps_Tab);

	}
}