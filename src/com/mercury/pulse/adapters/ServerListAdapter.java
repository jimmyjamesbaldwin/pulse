package com.mercury.pulse.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercury.pulse.R;
//import com.mercury.pulse.RetrieveContactPhoto;
import com.mercury.pulse.objects.Server;

public class ServerListAdapter extends ArrayAdapter<Server> {

	private ArrayList<Server> mServers;
	
	public ServerListAdapter(Context context, ArrayList<Server> objects) {
		super(context, R.layout.fragment_loanslist_peopleitem, objects);
		mServers = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		if(v==null){
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.fragment_loanslist_peopleitem, null);
		}
		Server server = mServers.get(position);
		if(server!=null){
			TextView name = (TextView)v.findViewById(R.id.loanslist_peopleitem_name);
			name.setText(server.getServerName());
			//if windows server, if linux server, etc, etc...
			ImageView icon = (ImageView)v.findViewById(R.id.loanslist_peopleitem_pic_border);
			icon.setImageResource(R.drawable.winlogo);
			
			v.setTag(server);
		}
		return v;
	}
}