package com.mercury.pulse.adapters;

import com.mercury.pulse.fragments.ServerInfoCPUFragment;
import com.mercury.pulse.fragments.ServerInfoFragment;
import com.mercury.pulse.fragments.ServerInfoHDDFragment;
import com.mercury.pulse.fragments.ServerInfoRAMFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ServerInfoTabAdapter extends FragmentPagerAdapter {
	Bundle bundle = new Bundle();

	public ServerInfoTabAdapter(FragmentManager fm, int SERVERID) {
		super(fm);
		bundle.putInt("SERVERID", SERVERID);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			ServerInfoFragment infofragment = new ServerInfoFragment();
			infofragment.setArguments(bundle);
			return infofragment;
		case 1:
			return new ServerInfoCPUFragment();
		case 2:
			return new ServerInfoRAMFragment();
		case 3:
			return new ServerInfoHDDFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		//this must be equal to the number of tabs you have!
		return 4;
	}

}
