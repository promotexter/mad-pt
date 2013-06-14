/**
 * 
 */
package com.pt.mobile.madpt.receiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;

/**
 * @author f3rmin
 *
 */
public class MainResultReceiver extends ResultReceiver {

	private final String TAG = MainResultReceiver.class.getSimpleName();
	
	private Handler handler;

	public MainResultReceiver(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.handler = handler;
	} 

	@Override
	protected void onReceiveResult(int resultCode, Bundle resultData) {
		Message message = new Message();
		message.what = resultCode;
		message.setData(resultData);
				
		handler.sendMessage(message);
	}
}
