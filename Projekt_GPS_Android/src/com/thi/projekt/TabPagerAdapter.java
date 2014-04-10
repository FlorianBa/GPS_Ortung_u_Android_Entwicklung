package com.thi.projekt;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
    super(fm);
    // TODO Auto-generated constructor stub
  }
  @Override
  public Fragment getItem(int i) {
    switch (i) {
        case 0:
            //Fragement for All Tab
            return new Tab_all();
        case 1:
           //Fragment for acc Tab
            return new Tab_acc();
        case 2:
            //Fragment for Winkel Tab
            return new Tab_winkel();
        case 3:
        	//Fragment for rpm Tab
        	return new Tab_rpm();
        case 4:
        	//Fragment for gps Tab
        	return new Tab_gps();
        }
    return null;
  }
  @Override
  public int getCount() {
    // TODO Auto-generated method stub
    return 5; //No of Tabs
  }
    }