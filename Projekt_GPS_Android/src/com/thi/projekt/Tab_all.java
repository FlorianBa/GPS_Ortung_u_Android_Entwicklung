package com.thi.projekt;

import java.util.Random;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.LegendAlign;
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

public class Tab_all extends Fragment {
	
	private GraphViewSeries series_acc_x,
	series_acc_y,
	series_acc_z,
	series_angle_x,
	series_angle_y,
	series_angle_z,
	series_rpm_1,
	series_rpm_2,
	series_rpm_3,
	series_rpm_4;

	private Runnable mTimerAcc, mTimerAngle, mTimerRPM;
	private int lastXValueAcc = 0, lastXValueAngle = 0, lastXValueRPM = 0;
	private final int refreshRate = 200, graphDataBuffer = 1000000, delayThread = 1;
	private final boolean scrollToEnd = true;
	private final Handler mHandler = new Handler();
	private Random random = new Random();
	private View fragmentView;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		fragmentView = inflater.inflate(R.layout.all_frag, container, false);

		generateGraph("Acceleration", R.id.graph_all_acc);
		generateGraph("Angle", R.id.graph_all_angle);
		generateGraph("RPM", R.id.graph_all_rpm);

		return fragmentView;
	}

	public void onStart(){
		super.onStart();

		// Wenn später die Daten durch einen Service geliefert werden, muss hier ein Reset der Series stattfinden
		
		appendGraphData(R.id.graph_all_acc);
		appendGraphData(R.id.graph_all_angle);
		appendGraphData(R.id.graph_all_rpm);
	}
	
	/*
	 *  Methode zum Anhängen von Daten an Graphen
	 */
	private void appendGraphData(int id) {

		switch(id){
		case R.id.graph_all_acc: 
			if(mTimerAcc == null){
				mTimerAcc= new Runnable() {
					@Override
					public void run() {
						// Diese Daten werden später durch andere Klasse geliefert
						GraphViewData data1 = new GraphViewData(lastXValueAcc, random.nextDouble()*3.5+0.1);
						GraphViewData data2 = new GraphViewData(lastXValueAcc, random.nextDouble()*3.5+0.1);
						GraphViewData data3 = new GraphViewData(lastXValueAcc, random.nextDouble()*3.5+0.1);
						
						series_acc_x.appendData(data1, scrollToEnd, graphDataBuffer);
						series_acc_y.appendData(data2, scrollToEnd, graphDataBuffer);
						series_acc_z.appendData(data3, scrollToEnd, graphDataBuffer);
						
						lastXValueAcc += 1d;
						mHandler.postDelayed(this, refreshRate);
					}
				};
				mHandler.postDelayed(mTimerAcc, delayThread);
			}
			break;
			
		case R.id.graph_all_angle: 
			if(mTimerAngle == null){
				mTimerAngle= new Runnable() {
					@Override
					public void run() {
						// Diese Daten werden später durch andere Klasse geliefert
						GraphViewData data1 = new GraphViewData(lastXValueAngle, random.nextDouble()*3.5+0.1);
						GraphViewData data2 = new GraphViewData(lastXValueAngle, random.nextDouble()*3.5+0.1);
						GraphViewData data3 = new GraphViewData(lastXValueAngle, random.nextDouble()*3.5+0.1);
						
						series_angle_x.appendData(data1, scrollToEnd, graphDataBuffer);
						series_angle_y.appendData(data2, scrollToEnd, graphDataBuffer);
						series_angle_z.appendData(data3, scrollToEnd, graphDataBuffer);
						
						lastXValueAngle += 1d;
						mHandler.postDelayed(this, refreshRate);
					}
				};
				mHandler.postDelayed(mTimerAngle, delayThread);
			}
			break;
			
		case R.id.graph_all_rpm: 
			if(mTimerRPM == null){
				mTimerRPM= new Runnable() {
					@Override
					public void run() {
						// Diese Daten werden später durch andere Klasse geliefert
						GraphViewData data1 = new GraphViewData(lastXValueRPM, random.nextDouble()*3.5+0.1);
						GraphViewData data2 = new GraphViewData(lastXValueRPM, random.nextDouble()*3.5+0.1);
						GraphViewData data3 = new GraphViewData(lastXValueRPM, random.nextDouble()*3.5+0.1);
						GraphViewData data4 = new GraphViewData(lastXValueRPM, random.nextDouble()*3.5+0.1);
						
						series_rpm_1.appendData(data1, scrollToEnd, graphDataBuffer);
						series_rpm_2.appendData(data2, scrollToEnd, graphDataBuffer);
						series_rpm_3.appendData(data3, scrollToEnd, graphDataBuffer);
						series_rpm_4.appendData(data4, scrollToEnd, graphDataBuffer);
						
						lastXValueRPM += 1d;
						mHandler.postDelayed(this, refreshRate);
					}
				};
				mHandler.postDelayed(mTimerRPM, delayThread);
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
		GraphViewSeries.GraphViewSeriesStyle styleMagenta = new GraphViewSeries.GraphViewSeriesStyle(Color.MAGENTA,2); 

		switch(id){
		case R.id.graph_all_acc: 
			series_acc_x = new GraphViewSeries("x", styleRed,initData);
			series_acc_y = new GraphViewSeries("y", styleBlue,initData);
			series_acc_z = new GraphViewSeries("z", styleGreen,initData);
			graphView.addSeries(series_acc_x);
			graphView.addSeries(series_acc_y);
			graphView.addSeries(series_acc_z);
			break;

		case R.id.graph_all_angle:
			series_angle_x = new GraphViewSeries("x", styleRed,initData);
			series_angle_y = new GraphViewSeries("y", styleBlue,initData);
			series_angle_z = new GraphViewSeries("z", styleGreen,initData);
			graphView.addSeries(series_angle_x);
			graphView.addSeries(series_angle_y);
			graphView.addSeries(series_angle_z);
			break;

		case R.id.graph_all_rpm:
			series_rpm_1 = new GraphViewSeries("1", styleRed,initData);
			series_rpm_2 = new GraphViewSeries("2", styleBlue,initData);
			series_rpm_3 = new GraphViewSeries("3", styleGreen,initData);
			series_rpm_4 = new GraphViewSeries("4", styleMagenta,initData);
			graphView.addSeries(series_rpm_1);
			graphView.addSeries(series_rpm_2);
			graphView.addSeries(series_rpm_3);
			graphView.addSeries(series_rpm_4);
			break;
		}

		graphView.setViewPort(0, 100);
		graphView.setScalable(true);
		graphView.setScrollable(true);
		graphView.setManualYAxisBounds(4, 0);
		graphView.getGraphViewStyle().setNumHorizontalLabels(3);
		graphView.getGraphViewStyle().setNumVerticalLabels(3);
		graphView.setShowLegend(true);
		graphView.setLegendAlign(LegendAlign.BOTTOM);

		LinearLayout layout = (LinearLayout) fragmentView.findViewById(id);
		layout.addView(graphView);
	}
}