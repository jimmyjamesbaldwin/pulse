package com.mercury.pulse.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mercury.pulse.R;
//import com.mercury.pulse.RetrieveContactPhoto;
import com.mercury.pulse.objects.Server;
import com.mercury.pulse.views.RoundedImageView;

public class ServerListAdapter extends ArrayAdapter<Server> {

	private ArrayList<Server>				mServers;
	
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
			TextView badgeCount = (TextView)v.findViewById(R.id.loanslist_peopleitem_badgenumber);
			RoundedImageView badge = (RoundedImageView)v.findViewById(R.id.loanslist_peopleitem_badge);
			v.findViewById(R.id.loanslist_peopleitem_pic);
			name.setText(server.getServerName());
			//new RetrieveContactPhoto(server.getLookupURI(), pic, mContext, R.drawable.friend_image_light).execute();
			badge.setVisibility(RoundedImageView.INVISIBLE);
			badgeCount.setVisibility(RoundedImageView.INVISIBLE);
			v.setTag(server);
		}
		return v;
	}
}