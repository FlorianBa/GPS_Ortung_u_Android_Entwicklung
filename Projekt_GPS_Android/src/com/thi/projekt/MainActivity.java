package com.thi.projekt;


import com.learn2crack.tab.R;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{
	private ViewPager Tab;
	private TabPagerAdapter TabAdapter;
	private ActionBar actionBar;
	private View customView;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
		Tab = (ViewPager)findViewById(R.id.pager);
		Tab.setOnPageChangeListener(
				new ViewPager.SimpleOnPageChangeListener(){
					@Override
					public void onPageSelected(int position){
						actionBar = getActionBar();
						actionBar.setSelectedNavigationItem(position);
					}
				});
		Tab.setAdapter(TabAdapter);
		actionBar = getActionBar();
		//Enable Tabs on Action Bar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


		ActionBar.TabListener tabListener = new ActionBar.TabListener(){
			@Override
			public void onTabReselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft){
				Tab.setCurrentItem(tab.getPosition());
			}
			@Override
			public void onTabUnselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft){
				// TODO Auto-generated method stub
			}};


			//Add New Tab
			actionBar.addTab(actionBar.newTab().setText("All").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("Acc").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("Winkel").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("RPM").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("GPS").setTabListener(tabListener));
			
			customView = getLayoutInflater().inflate(R.layout.actionbar_button, null);
			actionbar.setCustomView(customView,
	                new ActionBar.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT));
			actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
			
			ToggleButton button = (ToggleButton) customView.findViewById(R.id.button);
			button.setOnCheckedChangeListener(this);
	}
	
	@Override
	public void onCheckedChanged(CompoundButton button, boolean isChecked) {

		if(isChecked){
			Toast.makeText(this, "Start Saving", Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(this, "Stop Saving", Toast.LENGTH_LONG).show();
		}
	}
}
