package com.instrument.scpisuite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.app.Dialog;
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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	public final static int SECTION_MAX_COUNT = 6;
	public final static int SECTION_INDEX_TREE = 0;
	public final static int SECTION_INDEX_SCPI= 1;
	public final static int SECTION_INDEX_HISTORY = 2;
	public final static int SECTION_INDEX_BATCH = 3;
	public final static int SECTION_INDEX_DATA = 4;
	public final static int SECTION_INDEX_LOG = 5;
	
	public final static String HMSETTINGS_INSTRUMENT_NAME = "Instrument Name";
	public final static String HMSETTINGS_INSTRUMENT_TIMEOUT = "Instrument Timeout";
	public final static String HMSETTINGS_INSTRUMENT_CONNECTED = "Instrument Connected";
	public final static String HMSETTINGS_INSTRUMENT_CONNECTION = "Instrument Connection";
	public final static String HMSETTINGS_INSTRUMENT_LOCK = "Instrument Lock";
	public final static String HMSETTINGS_SERVER_NAME = "Server Name";
	public final static String HMSETTINGS_SERVER_TIMEOUT = "Server Timeout";
	public final static String HMSETTINGS_SERVER_CONNECTED = "Server Connected";
	public final static String HMSETTINGS_SERVER_CONNECTION = "Server Connection";
	public final static String HMSETTINGS_SERVER_LOCK = "Server Lock";
	
	public final static int DEFAULT_INSTRUMENT_TIMEOUT = 5000;
	public final static int DEFAULT_SERVER_TIMEOUT = 5000;
	
	private Lock mLockInstrument = new ReentrantLock();
	private Lock mLockServer = new ReentrantLock();
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private List<Fragment> mFragList = new ArrayList<Fragment>();
	private HashMap<String, Object> mHMSettings = new HashMap<String, Object>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// Initiate settings
		mHMSettings.put(HMSETTINGS_INSTRUMENT_NAME, "");
		mHMSettings.put(HMSETTINGS_INSTRUMENT_TIMEOUT, DEFAULT_INSTRUMENT_TIMEOUT);
		mHMSettings.put(HMSETTINGS_INSTRUMENT_CONNECTED, false);
		mHMSettings.put(HMSETTINGS_INSTRUMENT_CONNECTION, null);
		mHMSettings.put(HMSETTINGS_INSTRUMENT_LOCK, mLockInstrument);
		mHMSettings.put(HMSETTINGS_SERVER_NAME, "");
		mHMSettings.put(HMSETTINGS_SERVER_TIMEOUT, DEFAULT_SERVER_TIMEOUT);
		mHMSettings.put(HMSETTINGS_SERVER_CONNECTED, false);
		mHMSettings.put(HMSETTINGS_SERVER_CONNECTION, null);
		mHMSettings.put(HMSETTINGS_SERVER_LOCK, mLockServer);
		
		// Initiate fragments
		mFragList.add(new TreeSectionFragment(mHMSettings));
		mFragList.add(new ScpiSectionFragment(mHMSettings));
		mFragList.add(new HistorySectionFragment(mHMSettings));
		mFragList.add(new BatchSectionFragment(mHMSettings));
		mFragList.add(new DataSectionFragment(mHMSettings));
		mFragList.add(new LogSectionFragment(mHMSettings));
		
		mViewPager.setCurrentItem(SECTION_INDEX_SCPI);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
        switch (id) {
	        default:
	        	return true;
	        case R.id.menu_connectinstrument:
	        	return connectInstrument();
	        case R.id.menu_disconnectinstrument:
	        	return disconnectInstrument();
	        case R.id.menu_connectserver:
	        	return connectServer();
	        case R.id.menu_disconnectserver:
	        	return disconnectServer();
        }
	}

	private boolean disconnectServer() {
		Toast.makeText(getBaseContext(), "\"Disconnect Server\" is not available yet.", Toast.LENGTH_SHORT).show();
		return true;
	}

	private boolean connectServer() {
		Toast.makeText(getBaseContext(), "\"Connect Server\" is not available yet.", Toast.LENGTH_SHORT).show();
		return true;
	}

	private boolean disconnectInstrument() {
		Toast.makeText(getBaseContext(), "\"Disconnect Instrument\" is not available yet.", Toast.LENGTH_SHORT).show();
		mLockInstrument.lock();
		try {
			
		}
		finally {
			mLockInstrument.unlock();
		}
		return true;
	}

	private boolean connectInstrument() {
		//Toast.makeText(getBaseContext(), "\"Connect Instrument\" is not available yet.", Toast.LENGTH_SHORT).show();
		mLockInstrument.lock();
		try {
			String instrName = (String)mHMSettings.get(HMSETTINGS_INSTRUMENT_NAME);
			int instrTimeout = (Integer)mHMSettings.get(HMSETTINGS_INSTRUMENT_TIMEOUT);
			boolean instrConencted = (Boolean)mHMSettings.get(HMSETTINGS_INSTRUMENT_CONNECTED);

			View dialogView = View.inflate(MainActivity.this, R.layout.dialog_conninst, null);  
			Dialog dialog = new ConnectInstrumentDialog(MainActivity.this, R.style.dialogconninststyle);
			dialog.setContentView(dialogView);
			dialog.setCancelable(false);
			dialog.show();  
			
			/*
			if(instrName == null || instrName.isEmpty()) {
				Toast.makeText(MainActivity.this, "No instrument is selected.", Toast.LENGTH_SHORT).show();
			}
			else if(instrTimeout < DEFAULT_INSTRUMENT_TIMEOUT) {
				Toast.makeText(MainActivity.this, "Timeout(" + instrTimeout + ") for " + instrName + " should be > " + DEFAULT_INSTRUMENT_TIMEOUT, Toast.LENGTH_SHORT).show();
			}
			else if(instrConencted == true) {
				Toast.makeText(MainActivity.this, instrName + " is already connected.", Toast.LENGTH_SHORT).show();
			}
			else {
				mHMSettings.put(HMSETTINGS_INSTRUMENT_CONNECTED, true);
			}
			*/
		}
		finally {
			mLockInstrument.unlock();
		}
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if(position < 0)
				position = 0;
			if(position > SECTION_MAX_COUNT -1)
				position = SECTION_MAX_COUNT -1;
			return mFragList.get(position);
		}

		@Override
		public int getCount() {
			return SECTION_MAX_COUNT;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case SECTION_INDEX_TREE:
					return getString(R.string.title_activity_tree).toUpperCase(Locale.US);
				case SECTION_INDEX_SCPI:
					return getString(R.string.title_activity_scpi).toUpperCase(Locale.US);
				case SECTION_INDEX_HISTORY:
					return getString(R.string.title_activity_history).toUpperCase(Locale.US);
				case SECTION_INDEX_BATCH:
					return getString(R.string.title_activity_batch).toUpperCase(Locale.US);
				case SECTION_INDEX_DATA:
					return getString(R.string.title_activity_data).toUpperCase(Locale.US);
				case SECTION_INDEX_LOG:
					return getString(R.string.title_activity_log).toUpperCase(Locale.US);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return textView;
		}
	}

}
