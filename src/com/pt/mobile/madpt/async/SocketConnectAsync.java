/**
 * 
 */
package com.pt.mobile.madpt.async;

import java.net.MalformedURLException;

import android.os.AsyncTask;
import android.util.Log;

import com.pt.mobile.madpt.callback.AlertCallback;
import com.pt.mobile.madpt.container.SocketDetail;
import com.pt.mobile.madpt.socket.io.SocketIO;

/**
 * @author f3rmin
 *
 */
public class SocketConnectAsync extends AsyncTask<SocketDetail, Integer, SocketIO> {
	
	private static final String TAG = SocketConnectAsync.class.getSimpleName();

	private SocketIO socket;
	
	private AlertCallback alert;
	
	public SocketConnectAsync(AlertCallback alert) {
		super();
		this.alert = alert;
	}



	@Override
	protected SocketIO doInBackground(SocketDetail... params) {
		
		Log.d(TAG, "Execute in process");
		
		socket = null;
		
		SocketDetail detail = params[0];
		
		try {
			socket = new SocketIO(detail.getCompleteURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		socket.connect(alert);
		return socket;
	}
}
