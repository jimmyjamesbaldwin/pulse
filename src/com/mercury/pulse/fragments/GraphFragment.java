package com.mercury.pulse.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.mercury.pulse.R;

public class GraphFragment extends Fragment {

	@SuppressWarnings("unused")
	private int height, width;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_graph, container, false);
		makeSomeSexyGraphs(container, v);
		return v;
	}

	private void getScreenDimensions() {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;
	}

	private void makeSomeSexyGraphs(ViewGroup container, View view) {
		// init example series data
		GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
				new GraphViewData(1, 99.0d)
				, new GraphViewData(2, 77.0d)
				, new GraphViewData(3, 89.0d)
				, new GraphViewData(4, 93.0d)
		});

		GraphView graphView = new LineGraphView(container.getContext(), "CPU Usage Graph Demo");
		graphView.addSeries(exampleSeries);
		graphView.setManualYAxisBounds(100, 0);
		graphView.setHorizontalLabels(new String[] {"15:00", "15:30", "16:00", "16:30"});
		graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.GRAY);
		graphView.setVerticalLabels(new String[] {"100%", "50%", "0"});
		graphView.getGraphViewStyle().setVerticalLabelsColor(Color.GRAY);
		//graphView.getGraphViewStyle().setGridColor(Color.TRANSPARENT);
		
		//set size of graph to screen dimensions with some padding
		getScreenDimensions();
		graphView.setLayoutParams(new LayoutParams(width-30, 400));

		LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
		layout.addView(graphView);
	}

}