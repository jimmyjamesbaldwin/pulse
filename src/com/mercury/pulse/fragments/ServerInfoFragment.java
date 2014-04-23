package com.mercury.pulse.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mercury.pulse.R;

public class ServerInfoFragment extends Fragment {
	TextView txtview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_serverinfo, container, false);
		
		Bundle bundle = getArguments(); 
        int SERVERID = bundle.getInt("SERVERID");
        
        txtview = (TextView)v.findViewById(R.id.serverinfofragment_serverinfo);
        txtview.setText("Server ID: " + SERVERID);
		
		return v;
	}
}
