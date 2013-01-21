package com.instrument.scpisuite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private final static int SECTION_MAX_COUNT = 6;
	private final static int SECTION_INDEX_TREE = 0;
	private final static int SECTION_INDEX_SCPI= 1;
	private final static int SECTION_INDEX_HISTORY = 2;
	private final static int SECTION_INDEX_BATCH = 3;
	private final static int SECTION_INDEX_DATA = 4;
	private final static int SECTION_INDEX_LOG = 5;

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

		mFragList.add(new TreeSectionFragment(mHMSettings));
		mFragList.add(new ScpiSectionFragment(mHMSettings));
		mFragList.add(new HistorySectionFragment(mHMSettings));
		mFragList.add(new BatchSectionFragment(mHMSettings));
		mFragList.add(new DataSectionFragment(mHMSettings));
		mFragList.add(new LogSectionFragment(mHMSettings));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
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
