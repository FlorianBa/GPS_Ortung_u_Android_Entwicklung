package com.projekt;

import java.util.ArrayList;
import java.util.List;

import com.jjoe64.graphview.GraphView.GraphViewData;

public class TCP_Service {
	private List<GraphViewData> listAccX = new ArrayList<GraphViewData>();
	private List<GraphViewData> listAccY = new ArrayList<GraphViewData>();
	private List<GraphViewData> listAccZ = new ArrayList<GraphViewData>();
	private List<GraphViewData> listrpm1 = new ArrayList<GraphViewData>();
	private List<GraphViewData> listrpm2 = new ArrayList<GraphViewData>();
	private List<GraphViewData> listrpm3 = new ArrayList<GraphViewData>();
	private List<GraphViewData> listrpm4 = new ArrayList<GraphViewData>();
	private List<GraphViewData> listAngelX = new ArrayList<GraphViewData>();
	private List<GraphViewData> listAngelY= new ArrayList<GraphViewData>();
	private List<GraphViewData> listAngelZ = new ArrayList<GraphViewData>();
	
	private static boolean enableSaving = false;
	
	public GraphViewData getCurrentGraphDataAccX(){
		return listAccX.get(listAccX.size() - 1);
	}
	
	public GraphViewData getCurrentGraphDataAccY(){
		return listAccY.get(listAccY.size() - 1);
	}
	
	public GraphViewData getCurrentGraphDataAccZ(){
		return listAccZ.get(listAccZ.size() - 1);
	}
	
	public GraphViewData getCurrentGraphDatarmp1(){
		return listrpm1.get(listrpm1.size() - 1);
	}
	
	public GraphViewData getCurrentGraphDatarmp2(){
		return listrpm2.get(listrpm2.size() - 1);
	}
	
	public GraphViewData getCurrentGraphDatarmp3(){
		return listrpm3.get(listrpm3.size() - 1);
	}
	
	public GraphViewData getCurrentGraphDatarmp4(){
		return listrpm4.get(listrpm4.size() - 1);
	}
	
	public GraphViewData getCurrentGraphDataAngelX(){
		return listAngelX.get(listAngelX.size() - 1);
	}
	
	public GraphViewData getCurrentGraphDataAngelY(){
		return listAngelY.get(listAngelY.size() - 1);
	}
	
	public GraphViewData getCurrentGraphDataAngelZ(){
		return listAngelZ.get(listAngelZ.size() - 1);
	}
	
	public GraphViewData[] getAllGraphDataAccX(){
		return (GraphViewData[]) listAccX.toArray( new GraphViewData[listAccX.size()] );
	}
	
	public GraphViewData[] getAllGraphDataAccY(){
		return (GraphViewData[]) listAccY.toArray( new GraphViewData[listAccZ.size()] );
	}
	
	public GraphViewData[] getAllGraphDataAccZ(){
		return (GraphViewData[]) listAccZ.toArray( new GraphViewData[listAccZ.size()] );
	}
	
	public GraphViewData[] getAllGraphDatarpm1(){
		return (GraphViewData[]) listrpm1.toArray( new GraphViewData[listrpm1.size()] );
	}
	
	public GraphViewData[] getAllGraphDatarpm2(){
		return (GraphViewData[]) listrpm2.toArray( new GraphViewData[listrpm2.size()] );
	}
	
	public GraphViewData[] getAllGraphDatarpm3(){
		return (GraphViewData[]) listrpm3.toArray( new GraphViewData[listrpm3.size()] );
	}
	
	public GraphViewData[] getAllGraphDatarpm4(){
		return (GraphViewData[]) listrpm4.toArray( new GraphViewData[listrpm4.size()] );
	}
	
	public GraphViewData[] getAllGraphDataAngelX(){
		return (GraphViewData[]) listAngelX.toArray( new GraphViewData[listAngelX.size()] );
	}
	
	public GraphViewData[] getAllGraphDataAngelY(){
		return (GraphViewData[]) listAngelY.toArray( new GraphViewData[listAngelY.size()] );
	}
	
	public GraphViewData[] getAllGraphDataAngelZ(){
		return (GraphViewData[]) listAngelZ.toArray( new GraphViewData[listAngelZ.size()] );
	}
	
	public static void SetEnableSaving(boolean state){
		enableSaving = state;
	}
	
}
