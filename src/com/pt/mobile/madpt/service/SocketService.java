/**
 * 
 */
package com.pt.mobile.madpt.service;

import java.util.concurrent.ExecutionException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

import com.pt.mobile.madpt.async.SocketConnectAsync;
import com.pt.mobile.madpt.callback.AlertCallback;
import com.pt.mobile.madpt.constant.Configuration;
import com.pt.mobile.madpt.container.SocketDetail;
import com.pt.mobile.madpt.socket.io.SocketIO;

/**
 * @author f3rmin
 *
 */
public class SocketService extends Service {
	
	private static final String TAG = SocketService.class.getSimpleName();
	
	private Intent intent;
	
	private SocketConnectAsync connectAsync;
	
	private SocketIO socket;
	
	private AlertCallback alertCallback;
	
	private ResultReceiver resultReceiver;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		Log.d(TAG, "On start command");
		Log.d(TAG, "Intent == null?"+(intent == null));
		
		if(intent != null && SocketService.class.getName().equals(intent.getAction())){
			SocketDetail detail = new SocketDetail(Configuration.URL, Configuration.PORT);
			
			this.intent = intent;
			
			resultReceiver = intent.getParcelableExtra(Configuration.RECEIVER);
			
			alertCallback = new AlertCallback(resultReceiver);
			
			connectAsync = new SocketConnectAsync(alertCallback);
			
			Log.d(TAG, "Alert callback == null? "+(alertCallback == null));
	
			try {
				socket = connectAsync.execute(detail).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		else{
			Log.d(TAG, "this.intent == null? "+(this.intent == null));
		}
		
		return START_STICKY;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.d(TAG, "On Create SocketService");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		if(socket != null)
			socket.disconnect();
		
		intent = null;
		alertCallback = null;
		connectAsync = null;
		resultReceiver = null;
		
		Log.d(TAG, "On Destroy SocketService");
		
		super.onDestroy();
	}
	
	
}
