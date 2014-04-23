package com.mercury.pulse.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mercury.pulse.R;
import com.mercury.pulse.views.PieChartView;

public class StatFragment extends Fragment {

	private static final String					TAG = StatFragment.class.getSimpleName();
	private ProgressBar							mProgress;
	private TextView							mAverageReturn, mAverageReturnLbl, mPieChartLbl;
	private int									mAverageReturnAmount = 0;
	private PieChartView				mPieChart;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true); // Tell the activity that we have ActionBar items
	}

	@Override
	public void onCreateOptionsMenu(Menu menu,MenuInflater inf){
		super.onCreateOptionsMenu(menu, inf);
		inf.inflate(R.menu.stats, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.action_refresh:
			updateData();
			return true;
		default:
			break;
		}
		return false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_stats, container, false);
		mProgress = (ProgressBar)v.findViewById(R.id.stats_progress);
		mAverageReturn = (TextView)v.findViewById(R.id.stats_averagereturn);
		mAverageReturnLbl = (TextView)v.findViewById(R.id.stats_averagereturn_label);
		mPieChart = (PieChartView)v.findViewById(R.id.stats_piechart);
		mPieChartLbl = (TextView)v.findViewById(R.id.stats_piechart_label);
		updateData();
		return v;
	}

	public void updateData(){
		Log.d(TAG,"Updating UI");
		new UpdateData().execute();
	}

	public class UpdateData extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute(){
			mProgress.setVisibility(ProgressBar.VISIBLE);
			mAverageReturn.setVisibility(TextView.GONE);
			mAverageReturnLbl.setVisibility(TextView.GONE);
			mPieChartLbl.setVisibility(TextView.GONE);
			mPieChart.setVisibility(View.GONE);
			mAverageReturnAmount = 0;
		}

		@Override
		protected Void doInBackground(Void... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void no){
			mProgress.setVisibility(ProgressBar.INVISIBLE);
			mAverageReturnLbl.setVisibility(TextView.VISIBLE);
			mAverageReturnLbl.setText("No data could be generated");
			mAverageReturn.setText("Days Without Issue");
			mAverageReturnLbl.setText("Days Without Issue");
			mAverageReturnLbl.setVisibility(TextView.VISIBLE);
			mAverageReturn.setText(String.format(getActivity().getResources().getString(R.string.stats_averageloan_value),
					mAverageReturnAmount));
			mPieChartLbl.setVisibility(TextView.VISIBLE);
			mPieChart.setVisibility(View.VISIBLE);
		}
	}
}