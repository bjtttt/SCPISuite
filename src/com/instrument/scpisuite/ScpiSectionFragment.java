package com.instrument.scpisuite;

import java.util.HashMap;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ScpiSectionFragment extends Fragment {
	
	private HashMap<String, Object> mHMSettings = null;
	
	public ScpiSectionFragment(HashMap<String, Object> hmSettings) {
		mHMSettings = hmSettings;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.activity_scpi, container, false);
		
		ImageButton imgbtn = (ImageButton)view.findViewById(R.id.imgbtn_scpisend);
		imgbtn.setOnClickListener(imgbtnOnClickListener);
		imgbtn = (ImageButton)view.findViewById(R.id.imgbtn_scpisendquery);
		imgbtn.setOnClickListener(imgbtnOnClickListener);
		imgbtn = (ImageButton)view.findViewById(R.id.imgbtn_scpiquery);
		imgbtn.setOnClickListener(imgbtnOnClickListener);
		imgbtn = (ImageButton)view.findViewById(R.id.imgbtn_scpiread);
		imgbtn.setOnClickListener(imgbtnOnClickListener);
		imgbtn = (ImageButton)view.findViewById(R.id.imgbtn_scpistop);
		imgbtn.setOnClickListener(imgbtnOnClickListener);
		imgbtn = (ImageButton)view.findViewById(R.id.imgbtn_scpioptions);
		imgbtn.setOnClickListener(imgbtnOnClickListener);
		
		return view;
	}
	
	private OnClickListener imgbtnOnClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			boolean instrumentConnected = (Boolean)mHMSettings.get(MainActivity.HMSETTINGS_INSTRUMENT_CONNECTED);
			String instrumentName = (String)mHMSettings.get(MainActivity.HMSETTINGS_INSTRUMENT_NAME);
			if(instrumentConnected == false) {
				if(instrumentName== null || instrumentName.isEmpty() == true)
					Toast.makeText(getActivity(), "No instrument is connected.", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getActivity(), instrumentName + " is not connected.", Toast.LENGTH_SHORT).show();
			}
			
			int id = v.getId();
			switch(id) {
				default:
					break;
				case R.id.imgbtn_scpisend:
					break;
			}
		} 
    };
}
