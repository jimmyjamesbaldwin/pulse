package com.mercury.pulse.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mercury.pulse.R;
import com.mercury.pulse.adapters.NavDrawerListAdapter;
import com.mercury.pulse.fragments.GraphFragment;
import com.mercury.pulse.fragments.ServerListFragment;
import com.mercury.pulse.fragments.StatFragment;
import com.mercury.pulse.objects.NavDrawerListItem;

public class MainActivity extends Activity implements OnItemClickListener {

	private static final String					TAG = MainActivity.class.getSimpleName();

	private int									mFrameLayout = R.id.main_framelayout;
	private ArrayList<NavDrawerListItem>		mNavDrawerListItems;
	private DrawerLayout						mNavDrawer;
	private ListView							mNavDrawerList;
	private ActionBar							mActionBar;
	private ActionBarDrawerToggle				mNavDrawerToggle;
	private Fragment							mStatFrag, mServerListFrag, mGraphFrag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// If the available screen size is that of an average tablet (as defined
		// in the Android documentation) then allow the screen to rotate
		if(getResources().getBoolean(R.bool.lock_orientation)){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		mActionBar = getActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);

		// Instatiate Fragments
		mStatFrag = new StatFragment();
		mServerListFrag = new ServerListFragment();
		mGraphFrag = new GraphFragment();

		// Do navigation drawer things
		Log.d(TAG,"Creating NavigationDrawer");
		mNavDrawer = (DrawerLayout)findViewById(R.id.main_navdrawer);
		mNavDrawerList = (ListView)findViewById(R.id.main_navdrawer_list);
		// Put stuff in the list
		Log.d(TAG, "Adding items to nav drawer list");
		mNavDrawerListItems = new ArrayList<NavDrawerListItem>();
		mNavDrawerListItems.add(new NavDrawerListItem(R.drawable.ic_action_person, getResources().getString(R.string.navdrawer_servers)));
		mNavDrawerListItems.add(new NavDrawerListItem(R.drawable.ic_action_qr, getResources().getString(R.string.navdrawer_qrcode)));
		mNavDrawerListItems.add(new NavDrawerListItem(R.drawable.ic_stats, getResources().getString(R.string.navdrawer_stats)));
		mNavDrawerListItems.add(new NavDrawerListItem(R.drawable.ic_action_history, getResources().getString(R.string.navdrawer_history)));
		mNavDrawerListItems.add(new NavDrawerListItem(R.drawable.ic_action_settings, getResources().getString(R.string.navdrawer_settings)));
		mNavDrawerListItems.add(new NavDrawerListItem(R.drawable.ic_action_mercury, getResources().getString(R.string.navdrawer_about)));
		// Change the app icon to show/hide nav drawer on click
		mNavDrawerToggle = new ActionBarDrawerToggle(this, mNavDrawer, R.drawable.ic_drawer, R.string.main_navdrawer_open, R.string.main_navdrawer_close);
		mNavDrawer.setDrawerListener(mNavDrawerToggle);
		// Set the adapter for the ListView
		mNavDrawerList.setAdapter(new NavDrawerListAdapter(this, R.layout.activity_main_navitem, mNavDrawerListItems));
		mNavDrawerList.setOnItemClickListener(this);

		// Show the default Fragment
		FragmentTransaction mFragMan = getFragmentManager().beginTransaction();
		mFragMan.replace(mFrameLayout, mServerListFrag);
		setActivityTitle(getResources().getString(R.string.app_name), null);
		mFragMan.commit();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mNavDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mNavDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mNavDrawerToggle.onOptionsItemSelected(item)){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onResume() {
		super.onResume();
		/*if (mFragMan == null) {
			Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
					   Toast.LENGTH_LONG).show();
		}*/
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		if (position == 4) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		} else if (position == 1) {
			Intent intent = new Intent(this, QRActivity.class);
			startActivity(intent);
		} else if (position == 5) {
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
		} else {
			setFragment(position);
		}
		mNavDrawer.closeDrawers();
	}

	/**
	 * @param position Position of the item in the NavigationDrawer
	 */
	private void setFragment(int position){
		FragmentTransaction mFragMan = getFragmentManager().beginTransaction();
		switch (position) {
		case 0:
			mFragMan.replace(mFrameLayout, mServerListFrag);
			setActivityTitle(getResources().getString(R.string.app_name), null);
			break;
		case 2:
			mFragMan.replace(mFrameLayout, mStatFrag);
			setActivityTitle(getResources().getString(R.string.app_name), null);
			break;
		case 3:
			mFragMan.replace(mFrameLayout, mGraphFrag);
			setActivityTitle(getResources().getString(R.string.app_name), null);
			break;
		default:
			break;
		}
		mFragMan.commit();
	}

	private void setActivityTitle(String title, String subtitle){
		mActionBar.setTitle(title);
		mActionBar.setSubtitle(subtitle);
	}
}