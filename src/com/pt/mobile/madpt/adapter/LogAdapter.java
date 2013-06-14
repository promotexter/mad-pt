/**
 * 
 */
package com.pt.mobile.madpt.adapter;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pt.mobile.madpt.R;
import com.pt.mobile.madpt.container.RowDetail;

/**
 * @author f3rmin
 *
 */
public class LogAdapter extends BaseAdapter {

	private Activity activity;
	
	private static LayoutInflater inflater = null;
	
	private List<RowDetail> list;
	
	public LogAdapter(Activity activity, List<RowDetail> list)
	{
		this.activity = activity;
		this.list = list;
		
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View rowView = inflater.inflate(R.layout.rowlayout, null);
		
		TextView logType = (TextView) rowView.findViewById(R.id.log_type);
		TextView type = (TextView) rowView.findViewById(R.id.type);
		TextView date = (TextView) rowView.findViewById(R.id.log_date);
		TextView details = (TextView) rowView.findViewById(R.id.log_details);
		
		RowDetail detail = list.get(position);
		
		if(detail != null)
		{
			logType.setText(detail.getLogType());
			type.setText(detail.getType());
			date.setText(detail.getDate());
			details.setText(detail.getDetails());
		}
		
		return rowView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list != null? list.size():0;
	}

	@Override
	public Object getItem(int position) {
		RowDetail detail = null;
		
		if(list != null && position < list.size())
			detail = list.get(position);
			
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	
}
