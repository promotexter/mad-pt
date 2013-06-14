package com.pt.mobile.madpt.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.pt.mobile.madpt.R;
import com.pt.mobile.madpt.adapter.LogAdapter;
import com.pt.mobile.madpt.constant.Configuration;
import com.pt.mobile.madpt.container.RowDetail;
import com.pt.mobile.madpt.receiver.MainResultReceiver;
import com.pt.mobile.madpt.service.SocketService;
import com.pt.mobile.madpt.utils.ServiceUtil;

public class MainActivity extends Activity {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	private static Button toggleSocket;
	
	private Boolean isRunning;
	
	private Activity activity;
	
	private static ListView listView;
	
	private static LogAdapter adapter;
	
	private static List<RowDetail> rowList;
	
	private MainResultReceiver resultReceiver;
	
	private MainHandler mainHandler;
	
	public static class MainHandler extends Handler
	{
		private final String TAG = MainHandler.class.getSimpleName();
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			String result = msg.getData().getString(Configuration.BUNDLE_RESULT);
			
			JSONObject obj = null;
			
			try{
				obj = new JSONObject(result);
			}
			catch(Exception e)
			{
				obj = null;
			}
			
			switch(msg.what)
			{
				case Configuration.CONNECT_ERROR:
					Log.d(TAG, "Connect Error");
					toggleSocket.setText(R.string.socket_connect);
					toggleSocket.setEnabled(true);
					break;
				case Configuration.CONNECT_SUCCESS:
					Log.d(TAG, "Connect Success");
					toggleSocket.setText(R.string.socket_close);
					toggleSocket.setEnabled(true);
					break;
				case Configuration.INFO_STATUS: 
					Log.d(TAG, "Info status");
					
					if(obj != null)
					{
						rowList.add(new RowDetail(obj));
						
						adapter.notifyDataSetChanged();
					}	
					
					break;
				case Configuration.WARNING_STATUS: 
					Log.d(TAG, "Warning status");
					
					if(obj != null)
					{
						rowList.add(new RowDetail(obj));
						
						adapter.notifyDataSetChanged();
					}	
					break;
				case Configuration.CRITICAL_STATUS: 
					Log.d(TAG, "Critical status");
					
					if(obj != null)
					{
						rowList.add(new RowDetail(obj));
						
						adapter.notifyDataSetChanged();
					}	
					break;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		activity = this;
		
		mainHandler = new MainHandler();
		
		resultReceiver = new MainResultReceiver(mainHandler);
		
		listView = (ListView) findViewById(R.id.listview);
		rowList = new ArrayList<RowDetail>();
		
//		createDummyRows();
		
		adapter = new  LogAdapter(activity, rowList);
		listView.setAdapter(adapter);
		
		isRunning = ServiceUtil.isServiceRunning(this, SocketService.class.getName());
		
		Log.d(TAG, "Is service Running? "+(isRunning));
		
		toggleSocket = (Button) findViewById(R.id.toggleSocket);
		
		if(isRunning)
		{
			toggleSocket.setText(R.string.socket_close);
		}
		else toggleSocket.setText(R.string.socket_connect);
		
		toggleSocket.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				isRunning = ServiceUtil.isServiceRunning(activity, SocketService.class.getName());
				
				Log.d(TAG, "Button clicked");
				//start activity
				Intent intent = new Intent(getApplicationContext(), SocketService.class);
				intent.setAction(SocketService.class.getName());
				intent.putExtra(Configuration.RECEIVER, resultReceiver);
				
				if(!isRunning){
					toggleSocket.setText("Connecting to Socket");
					toggleSocket.setEnabled(false);
					
					activity.startService(intent);
				}
				else{
					
					activity.stopService(intent);
					toggleSocket.setText(R.string.socket_connect);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void createDummyRows()
	{
		if(rowList == null)
			rowList = new ArrayList<RowDetail>();
		
		RowDetail detail = null;
		for(int i=0; i<5; i++)
		{
			detail = new RowDetail();
			
			detail.setDate("Date "+i);
			detail.setDetails("Details "+i);
			detail.setLogType("Log Type "+i);
			detail.setType("Type "+i);
			
			rowList.add(detail);
		}
	}
}
