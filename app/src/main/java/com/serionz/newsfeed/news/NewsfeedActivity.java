package com.serionz.newsfeed.news;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import com.google.firebase.auth.FirebaseAuth;
import com.serionz.newsfeed.R;
import com.serionz.newsfeed.auth.LoginActivity;

public class NewsfeedActivity extends AppCompatActivity implements
		TabLayout.OnTabSelectedListener,
		GlobalNewsFragment.OnFragmentInteractionListener,
		SportsNewsFragment.OnFragmentInteractionListener,
		TechnologyNewsFragment.OnFragmentInteractionListener{

	private NewsfeedAdapter mNewsfeedAdapter;

	//@BindView(R.id.container) ViewPager mViewPager;
	//@BindView(R.id.tab_layout) TabLayout mTablayout;
	private TabLayout mTablayout;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsfeed);

		mTablayout = (TabLayout) findViewById(R.id.tab_layout);
		mViewPager = (ViewPager) findViewById(R.id.pager);

		mTablayout.addTab(mTablayout.newTab().setText("Global News"));
		mTablayout.addTab(mTablayout.newTab().setText("Sports News"));
		mTablayout.addTab(mTablayout.newTab().setText("Technology News"));

		mNewsfeedAdapter = new NewsfeedAdapter(getSupportFragmentManager(), mTablayout.getTabCount());
		mViewPager.setAdapter(mNewsfeedAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_newsfeed, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_logout) {
			FirebaseAuth.getInstance().signOut();
			Intent loginIntent = new Intent(this, LoginActivity.class);
			startActivity(loginIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override public void onTabSelected(TabLayout.Tab tab) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override public void onTabUnselected(TabLayout.Tab tab) {

	}

	@Override public void onTabReselected(TabLayout.Tab tab) {

	}

	@Override public void onFragmentInteraction(Uri uri) {
		
	}
}
