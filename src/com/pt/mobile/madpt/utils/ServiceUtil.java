/**
 * 
 */
package com.pt.mobile.madpt.utils;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;

/**
 * @author Fermin B. Agomaa III
 *
 * Date Created: Oct 12, 2012
 */
public class ServiceUtil {
	
	private static final String TAG = ServiceUtil.class.getSimpleName();

	public static boolean isServiceRunning(Activity activity, String service)
	{
		if(activity != null){
			ActivityManager manager = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
			
			for(RunningServiceInfo info: manager.getRunningServices(Integer.MAX_VALUE))
			{
//				Log.d(TAG, info.service.getClassName());
				
				if(service.equals(info.service.getClassName())) return true;
			}
		}
				
		return false;
	}
	
	public static JSONArray convertToJsonArray(String input)
	{
		JSONArray array = null;
		
		if(input != null)
		{
			try {
				array = new JSONArray(input);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return array;
			}
		}
		
		return array;
	}
}

