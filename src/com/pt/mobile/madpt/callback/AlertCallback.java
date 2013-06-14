/**
 * 
 */
package com.pt.mobile.madpt.callback;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.pt.mobile.madpt.constant.Configuration;
import com.pt.mobile.madpt.enums.SocketEvent;
import com.pt.mobile.madpt.socket.io.IOAcknowledge;
import com.pt.mobile.madpt.socket.io.IOCallback;
import com.pt.mobile.madpt.socket.io.SocketIOException;

/**
 * @author f3rmin
 *
 */
public class AlertCallback implements IOCallback, Serializable {

	private static final long serialVersionUID = 3750278132276171809L;

	private static final String TAG = AlertCallback.class.getSimpleName();
	
	private JSONArray array;
	private JSONObject jsonObj;
	
	private ResultReceiver resultReceiver;

	public AlertCallback(ResultReceiver resultReceiver) {
		super();
		this.resultReceiver = resultReceiver;
	}

	@Override
	public void onDisconnect() {
		Log.d(TAG, "Connection terminated");
		
	}

	@Override
	public void onConnect() {
		Log.d(TAG, "Connection Established");
		resultReceiver.send(Configuration.CONNECT_SUCCESS, null);
	}

	@Override
	public void onMessage(String data, IOAcknowledge ack) {

		Log.d(TAG, "onMessage: "+data);
	}

	@Override
	public void onMessage(JSONObject json, IOAcknowledge ack) {
		Log.d(TAG, "onMessage: "+json.toString());
	}

	@Override
	public void on(String event, IOAcknowledge ack, Object... args) {

		array = null;
		jsonObj = null;
		Log.d(TAG, "Event: "+ event);
//		Log.d(TAG, "Ack: "+ack.toString());
		Log.d(TAG, "Arg Length: "+args.length);
		Log.d(TAG, "Arg String: "+args[0].toString());
		
		if(SocketEvent.NEW_LINE.getValue().equals(event))
		{
			if(args.length > 0){
				try {
					array = new JSONArray(args[0].toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(array != null)
			{
				try {
					jsonObj = new JSONObject(array.getString(0));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Log.d(TAG, "JSON Obj == null?"+(jsonObj == null));
			
			if(jsonObj != null)
			{
				Log.d(TAG, jsonObj.toString());
				Bundle result = new Bundle();
//				Iterator<String> keys = jsonObj.keys();
				
				result.putString(Configuration.BUNDLE_RESULT, jsonObj.toString());
				
				resultReceiver.send(Configuration.INFO_STATUS, result);
			}
		}
	}

	@Override
	public void onError(SocketIOException socketIOException) {
		resultReceiver.send(Configuration.CONNECT_ERROR, null);
		Log.d(TAG, "Error occurred: "+socketIOException.getMessage());
		socketIOException.printStackTrace();
	}

}
