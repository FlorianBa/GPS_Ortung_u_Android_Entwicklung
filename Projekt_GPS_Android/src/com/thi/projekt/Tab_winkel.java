package com.thi.projekt;

import java.util.Random;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.learn2crack.tab.R;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/*
 * Bei diesem Fragment fehlt noch die Anbindung an einem Service der die entsprechenden Daten liefert
 */
public class Tab_winkel extends Fragment {
	
	private GraphViewSeries series_x, series_y, series_z;
	private Runnable mTimerX, mTimerY, mTimerZ;
	private int lastXValueAngleX = 0, lastXValueAngleY = 0, lastXValueAngleZ = 0;
	private final int refreshRate = 200, graphDataBuffer = 1000000, delayThread = 1;
	private final boolean scrollToEnd = true;
	private final Handler mHandler = new Handler();
	private Random random = new Random();
	private View fragmentView;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		fragmentView = inflater.inflate(R.layout.winkel_frag, container, false);

		generateGraph("Angle X", R.id.graph_angle_x);
		generateGraph("Angle y", R.id.graph_angle_y);
		generateGraph("Angle z", R.id.graph_angle_z);

		return fragmentView;
	}
	
	public void onStart(){
		super.onStart();
		
		// Wenn sp�ter die Daten durch einen Service geliefert werden, muss hier ein Reset der Series stattfinden
		
		appendGraphData(R.id.graph_angle_x);
		appendGraphData(R.id.graph_angle_y);
		appendGraphData(R.id.graph_angle_z);
	}
	
	/*
	 *  Methode zum Anh�ngen von Daten an Graphen
	 */
	private void appendGraphData(int id) {

		switch(id){
		case R.id.graph_angle_x: 
			if(mTimerX == null){
				mTimerX = new Runnable() {
					@Override
					public void run() {
						// Diese Daten werden sp�ter durch andere Klasse geliefert
						GraphViewData data = new GraphViewData(lastXValueAngleX, random.nextDouble()*3.5+0.1);
						
						series_x.appendData(data, scrollToEnd, graphDataBuffer);
						
						lastXValueAngleX += 1d;
						mHandler.postDelayed(this, refreshRate);
					}
				};
				mHandler.postDelayed(mTimerX, delayThread);
			}
			break;
			
		case R.id.graph_angle_y: 
			if(mTimerY == null){
				mTimerY = new Runnable() {
					@Override
					public void run() {
						// Diese Daten werden sp�ter durch andere Klasse geliefert
						GraphViewData data = new GraphViewData(lastXValueAngleY, random.nextDouble()*3.5+0.1);
						
						series_y.appendData(data, scrollToEnd, graphDataBuffer);

						
						lastXValueAngleY += 1d;
						mHandler.postDelayed(this, refreshRate);
					}
				};
				mHandler.postDelayed(mTimerY, delayThread);
			}
			break;
			
		case R.id.graph_angle_z: 
			if(mTimerZ == null){
				mTimerZ = new Runnable() {
					@Override
					public void run() {
						// Diese Daten werden sp�ter durch andere Klasse geliefert
						GraphViewData data = new GraphViewData(lastXValueAngleZ, random.nextDouble()*3.5+0.1);
						
						series_z.appendData(data, scrollToEnd, graphDataBuffer);
						
						lastXValueAngleZ += 1d;
						mHandler.postDelayed(this, refreshRate);
					}
				};
				mHandler.postDelayed(mTimerZ, delayThread);
			}
			break;



		}
	}
	
	
	/*
	 *  Methode zum Initialisieren eines Graphen
	 */
	private void generateGraph(String name, int id){
		GraphView graphView = new LineGraphView(this.getActivity(), name);
		GraphViewData[] initData = new GraphViewData[]{new GraphViewData(0, 0)};
		GraphViewSeries.GraphViewSeriesStyle styleRed = new GraphViewSeries.GraphViewSeriesStyle(Color.RED,2);  
		GraphViewSeries.GraphViewSeriesStyle styleBlue = new GraphViewSeries.GraphViewSeriesStyle(Color.BLUE,2); 
		GraphViewSeries.GraphViewSeriesStyle styleGreen = new GraphViewSeries.GraphViewSeriesStyle(Color.GREEN,2); 

		switch(id){
		case R.id.graph_angle_x: 
			series_x = new GraphViewSeries("x", styleRed,initData);
			graphView.addSeries(series_x);;
			break;

		case R.id.graph_angle_y:
			series_y = new GraphViewSeries("y", styleBlue,initData);
			graphView.addSeries(series_y);
			break;

		case R.id.graph_angle_z:
			series_z = new GraphViewSeries("z", styleGreen,initData);
			graphView.addSeries(series_z);
			break;
		}

		graphView.setViewPort(0, 100);
		graphView.setScalable(true);
		graphView.setScrollable(true);
		graphView.setManualYAxisBounds(4, 0);
		graphView.getGraphViewStyle().setNumHorizontalLabels(3);
		graphView.getGraphViewStyle().setNumVerticalLabels(3);


		LinearLayout layout = (LinearLayout) fragmentView.findViewById(id);
		layout.addView(graphView);
	}
}