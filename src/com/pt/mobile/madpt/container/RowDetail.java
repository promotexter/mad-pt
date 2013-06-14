package com.pt.mobile.madpt.container;

import org.json.JSONObject;

import android.util.Log;

import com.pt.mobile.madpt.constant.Configuration;

public class RowDetail {
	
	private static final String TAG = RowDetail.class.getSimpleName();

	private String logType;
	private String type;
	private String date;
	private String details;
	
	public RowDetail(){}
	
	public RowDetail(JSONObject obj)
	{
		if(obj != null)
		{
			try{
				this.logType = obj.getString(Configuration.LOG_TYPE);
				this.type = obj.getString(Configuration.TYPE);
				this.date = obj.getString(Configuration.LOG_DATE);
				this.details = obj.getString(Configuration.LOG_DETAILS);
			}
			catch(Exception e)
			{
				Log.d(TAG, "JSON Object == null");
			}
		}
	}
	
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
