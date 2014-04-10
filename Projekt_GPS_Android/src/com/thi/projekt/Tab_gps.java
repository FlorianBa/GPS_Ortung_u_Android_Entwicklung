package com.thi.projekt;
import com.learn2crack.tab.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class Tab_gps extends Fragment {
  @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
              Bundle savedInstanceState) {
          View gps = inflater.inflate(R.layout.gps_frag, container, false);
          ((TextView)gps.findViewById(R.id.textView)).setText("Hier Google Maps");
          return gps;
}}