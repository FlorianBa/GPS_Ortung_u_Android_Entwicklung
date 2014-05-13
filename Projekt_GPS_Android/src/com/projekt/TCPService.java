package com.projekt;

/**
 * Created by matthiasNuttendorfer on 5/5/14.
 */



import java.io.IOException;
import java.io.InputStream;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import static com.jjoe64.graphview.GraphView.GraphViewData;

public class TCPService extends Service {

	private final static int SOF = 0x43;


	public Socket socket;

	public static final int SERVERPORT = 4000;
	public static final String SERVERIP = "192.168.178.26";
	private MainActivity linkToParent;



	private List<GraphViewData> listAccX = new ArrayList<GraphViewData>();
	private List<GraphViewData> listAccY = new ArrayList<GraphViewData>();
	private List<GraphViewData> listAccZ = new ArrayList<GraphViewData>();
	private List<GraphViewData> listrpm1 = new ArrayList<GraphViewData>();
	private List<GraphViewData> listrpm2 = new ArrayList<GraphViewData>();
	private List<GraphViewData> listrpm3 = new ArrayList<GraphViewData>();
	private List<GraphViewData> listrpm4 = new ArrayList<GraphViewData>();
	private List<GraphViewData> listAngleX = new ArrayList<GraphViewData>();
	private List<GraphViewData> listAngleY= new ArrayList<GraphViewData>();
	private List<GraphViewData> listAngleZ = new ArrayList<GraphViewData>();
	private List<LatLng>      listLocations = new ArrayList<LatLng>();
	private static boolean enableSaving = false;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		//intent.getClass()
		return myBinder;
	}

	public void setLink (Object parent)
	{
		linkToParent =  (MainActivity) parent;
	}

	private final IBinder myBinder = new LocalBinder();

	public class LocalBinder extends Binder {
		public TCPService getService() {
			return TCPService.this;
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		socket = new Socket();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket = null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Runnable connect = new connectSocket();
		new Thread(connect).start();

		return super.onStartCommand(intent, flags, startId);
	}

	class connectSocket implements Runnable {
		@Override
		public void run() {

			try {
				//here you must put your computer's IP address.
				InetAddress serverAddr = InetAddress.getByName(SERVERIP);
				Socket s = new Socket(serverAddr, SERVERPORT);
				while (!Thread.currentThread().isInterrupted()) {

					try {
						//  Log.i("TcpClient", "received:");

						processReceivedData(s.getInputStream());

					} catch (IOException e) {
						e.printStackTrace();
					}
				}


			} catch (UnknownHostException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}
		}
	}


	private void processReceivedData(InputStream rcvdData)
	{

		double timestamp;
		byte[] buffer = new byte[28];
		try {
			rcvdData.read(buffer,0,buffer.length);
			Log.i("TCP", "current buffer" + buffer.toString());

			if(buffer[0] == SOF){

				switch ((buffer[8] + buffer[9])) {

				case 16:
					timestamp = listAccX.size();

					listAccX.add(new GraphViewData(timestamp, getValueFromBytes(buffer, 13, 14, false) * 0.0005));
					listAccY.add(new GraphViewData(timestamp, getValueFromBytes(buffer, 15, 16, false) * 0.005));
					listAccZ.add(new GraphViewData(timestamp, getValueFromBytes(buffer, 15, 16, false) * 0.005));
					Log.i("count", " "+listAccX.size());
					break;

				case 21:


					timestamp= listrpm1.size();
					listrpm1.add(new GraphViewData(timestamp, getValueFromBytes(buffer,13,14,false)));
					listrpm2.add(new GraphViewData(timestamp, getValueFromBytes(buffer,13,14,false)));
					listrpm3.add(new GraphViewData(timestamp, getValueFromBytes(buffer,13,14,false)));
					listrpm4.add(new GraphViewData(timestamp, getValueFromBytes(buffer,13,14,false)));

					break;

				case 22:

					timestamp = listAngleX.size();
					listAngleX.add(new GraphViewData(timestamp, getValueFromBytes(buffer, 13, 14, false) * 1.0));
					listAngleY.add(new GraphViewData(timestamp, getValueFromBytes(buffer, 15, 16, false) * 1.0));
					listAngleZ.add(new GraphViewData(timestamp, getValueFromBytes(buffer, 17, 18, false) * 1.0));


					break;

				case 18:


					listLocations.add(new LatLng(getValueFromBytes(buffer,13,14,false), getValueFromBytes(buffer,12,24, false)));

					break;

				}
			}


		}catch (Exception e){

		}
	}


	public static Double getValueFromBytes(byte[] buffer,
			int startByte, int endByte, Boolean byteIsUnsigned) {

		Double result = new Double(0.0);


		for (int i = startByte; i <= endByte; i++) {
			if (!byteIsUnsigned && i == endByte) {
				result += new Double(buffer[i])
				* Math.pow(2, 8 * (i - startByte));

			} else {
				result += new Double(unsignedByteToInt(buffer[i]))
				* Math.pow(2, 8 * (i - startByte));

			}
		}

		return result;
	}

	private static int unsignedByteToInt(byte b) {
		return (int) b & 0xFF;
	}





	public GraphViewData getCurrentGraphDataAccX(){
		return listAccX.get(listAccX.size() - 1);
	}

	public GraphViewData getCurrentGraphDataAccY(){
		return listAccY.get(listAccY.size() - 1);
	}

	public GraphViewData getCurrentGraphDataAccZ(){
		return listAccZ.get(listAccZ.size() - 1);
	}

	public GraphViewData getCurrentGraphDatarpm1(){
		return listrpm1.get(listrpm1.size() - 1);
	}

	public GraphViewData getCurrentGraphDatarpm2(){
		return listrpm2.get(listrpm2.size() - 1);
	}

	public GraphViewData getCurrentGraphDatarpm3(){
		return listrpm3.get(listrpm3.size() - 1);
	}

	public GraphViewData getCurrentGraphDatarpm4(){
		return listrpm4.get(listrpm4.size() - 1);
	}

	public GraphViewData getCurrentGraphDataAngleX(){
		return listAngleX.get(listAngleX.size() - 1);
	}

	public GraphViewData getCurrentGraphDataAngleY(){
		return listAngleY.get(listAngleY.size() - 1);
	}

	public GraphViewData getCurrentGraphDataAngleZ(){
		return listAngleZ.get(listAngleZ.size() - 1);
	}

	public LatLng getCurrentLocation(){
		return listLocations.get(listLocations.size()-1);
	}
	public List<LatLng> getAllLocations(){
		return listLocations;
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

	public GraphViewData[] getAllGraphDataAngleX(){
		return (GraphViewData[]) listAngleX.toArray( new GraphViewData[listAngleX.size()] );
	}

	public GraphViewData[] getAllGraphDataAngleY(){
		return (GraphViewData[]) listAngleY.toArray( new GraphViewData[listAngleY.size()] );
	}

	public GraphViewData[] getAllGraphDataAngleZ(){
		return (GraphViewData[]) listAngleZ.toArray( new GraphViewData[listAngleZ.size()] );
	}

	public static void SetEnableSaving(boolean state){
		enableSaving = state;
	}

}



