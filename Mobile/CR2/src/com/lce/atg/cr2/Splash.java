package com.lce.atg.cr2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class Splash extends FragmentActivity {

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

	static ExpandableListView mExpandableList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		//mExpandableList = (ExpandableListView) findViewById(R.id.expandable_list);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		/**
		 * @param fm
		 */
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
		 */
		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.support.v4.view.PagerAdapter#getPageTitle(int)
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.title_loading).toUpperCase();
			case 1:
				return getString(R.string.title_soldier).toUpperCase();
			case 2:
				return getString(R.string.title_supporter).toUpperCase();
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

			// // Create a new TextView and set its text to the fragment's
			// section
			// // number argument value.
			// TextView textView = new TextView(getActivity());
			// textView.setGravity(Gravity.CENTER);
			// textView.setText(Integer.toString(getArguments().getInt(
			// ARG_SECTION_NUMBER)));

			int position = getArguments().getInt(ARG_SECTION_NUMBER);

			View pageView = null;

			switch (position) {
			case 1:
				if (pageView == null) {
					pageView = inflater.inflate(R.layout.loading_layout,
							container, false);
				}
				return pageView;
			case 2:
				if (pageView == null) {
					pageView = inflater.inflate(R.layout.soldier_layout,
							container, false);
				}
				return pageView;
			case 3:
				if (pageView == null) {
					// pageView = inflater.inflate(R.layout.supporter_layout,
					// container, false);

					/*
					 * ExpandableListView
					 */
					mExpandableList = (ExpandableListView) getActivity().findViewById(R.id.expandable_list);

					// Create ArrayLists
					ArrayList<ParentItem> arrayParents = new ArrayList<ParentItem>();
					ArrayList<String> arrayChildren = new ArrayList<String>();

					// Set the parents and the children
					for (int i = 0; i < 10; i++) {
						// For each "i" create a new Parent object to set the
						// title and the
						// children
						ParentItem parent = new ParentItem();
						parent.setTitle("Parent " + i);
						arrayChildren.add("Child " + i);
						parent.setArrayChildren(arrayChildren);

						// Add the Parent object. Use the arrayParents at the
						// setAdapter
						arrayParents.add(parent);
					}

					// sets the adapter that provides data to the list.
					mExpandableList.setAdapter(new CustomExpandableListAdapter(
							getActivity().getBaseContext(), arrayParents));

					pageView = inflater.inflate(R.layout.supporter_layout,
							container, false);

				}
				return pageView;
			default:
				return null;
			}
		}
	}

}
