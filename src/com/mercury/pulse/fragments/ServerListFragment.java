package com.mercury.pulse.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mercury.pulse.R;
import com.mercury.pulse.activities.ServerInfoActivity;
import com.mercury.pulse.adapters.ServerListAdapter;
import com.mercury.pulse.objects.Server;

public class ServerListFragment extends Fragment implements OnItemClickListener {

	//private static final String					TAG = LoansByPersonFragment.class.getSimpleName();

	private ProgressBar							mProgress;
	private TextView							mEmptyState;
	private GridView							mGridView;
	private ArrayList<Server>					mServers;
	private ServerListAdapter					mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true); // Tell the activity that we have ActionBar items
	}

	@Override
	public void onCreateOptionsMenu(Menu menu,MenuInflater inf){
		super.onCreateOptionsMenu(menu, inf);
		inf.inflate(R.menu.loanslist, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		return false;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_loanslist, container, false);
		mProgress = (ProgressBar)v.findViewById(R.id.loanslist_progress);
		mEmptyState = (TextView)v.findViewById(R.id.loanslist_empty);
		mGridView = (GridView)v.findViewById(R.id.loanslist_grid);
		mGridView.setOnItemClickListener(this);
		return v;
	}

	@Override
	public void onStart(){
		super.onStart();
		new GetPeople().execute();
	}

	private class GetPeople extends AsyncTask<Void, Void, Exception>{
		@Override
		protected void onPreExecute(){
			mProgress.setVisibility(ProgressBar.VISIBLE);
			mGridView.setVisibility(GridView.INVISIBLE);
			mEmptyState.setVisibility(TextView.INVISIBLE);
			mServers = new ArrayList<Server>();
		}
		@Override
		protected Exception doInBackground(Void... params) {
			try{
				Server newServer = new Server(1, "Win7");
				mServers.add(newServer);
				newServer = new Server(2, "Win8");
				mServers.add(newServer);
				newServer = new Server(3, "Win9");
				mServers.add(newServer);
				newServer = new Server(4, "Win5");
				mServers.add(newServer);
				newServer = new Server(5, "Win3");
				mServers.add(newServer);
				newServer = new Server(7, "Win3");
				mServers.add(newServer);
				newServer = new Server(6, "Win3");
				mServers.add(newServer);
				
				
			} catch (Exception e){
				return e;
			}
			return null;
		}
		@Override
		protected void onPostExecute(Exception e){
			mProgress.setVisibility(ProgressBar.INVISIBLE);
			if(e!=null){
				Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
				Log.e("error", e.toString());
			} else {
				if(mServers==null || mServers.size()<1){
					mEmptyState.setVisibility(TextView.VISIBLE);
				} else {
					try{
						mAdapter = new ServerListAdapter(getActivity(), mServers);
						mGridView.setAdapter(mAdapter);
						mGridView.setVisibility(GridView.VISIBLE);
					} catch (Exception e1){
						e1.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Bundle b = new Bundle();
		b.putInt("SERVERID", ((Server)arg1.getTag()).getServerID());
		Intent i = new Intent(getActivity(), ServerInfoActivity.class);
		i.putExtras(b);
		getActivity().startActivity(i);
	}
}