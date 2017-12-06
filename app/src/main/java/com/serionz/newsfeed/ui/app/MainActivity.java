package com.serionz.newsfeed.ui.app;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.serionz.newsfeed.R;
import com.serionz.newsfeed.glide.GlideApp;
import com.serionz.newsfeed.ui.auth.LoginActivity;
import com.serionz.newsfeed.ui.global_news.GlobalNewsFragment;
import com.serionz.newsfeed.ui.sports_news.SportsNewsFragment;
import com.serionz.newsfeed.ui.tech_news.TechnologyNewsFragment;

public class MainActivity extends AppCompatActivity implements
		NavigationView.OnNavigationItemSelectedListener,
		GlobalNewsFragment.OnFragmentInteractionListener,
		SportsNewsFragment.OnFragmentInteractionListener,
		TechnologyNewsFragment.OnFragmentInteractionListener {

	private NewsfeedAdapter mNewsfeedAdapter;
	private FirebaseUser currentUser;

	@BindView(R.id.container) ViewPager mViewPager;

	private TextView userEmail;
	private TextView userName;
	private ImageView profilePic;
	private TabLayout tabLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tabLayout = (TabLayout) findViewById(R.id.tabs);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		View header = navigationView.getHeaderView(0);
		profilePic = (ImageView) header.findViewById(R.id.profile_pic);
		userName = (TextView) header.findViewById(R.id.user_name);
		userEmail = (TextView) header.findViewById(R.id.user_email);
		mViewPager = (ViewPager) findViewById(R.id.container);

		this.initializeTabs();

		currentUser = FirebaseAuth.getInstance().getCurrentUser();
		mNewsfeedAdapter = new NewsfeedAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
		mViewPager.setAdapter(mNewsfeedAdapter);
		mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

		setSupportActionBar(toolbar);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();


		this.initializeUserProfile();
	}

	private void initializeTabs() {
		String tabs[] = { "Global Article", "Sports", "Technology" };
		for(String tab: tabs) {
			tabLayout.addTab(tabLayout.newTab().setText(tab));
		}
	}

	private void initializeUserProfile() {
		userEmail.setText(currentUser.getEmail());
		userName.setText(currentUser.getDisplayName());
		Uri url = currentUser.getPhotoUrl();
		GlideApp.with(MainActivity.this).load(url).into(profilePic);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.logout:
				FirebaseAuth.getInstance().signOut();
				Intent loginIntent = new Intent(this, LoginActivity.class);
				startActivity(loginIntent);
				break;
		}
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override public void onFragmentInteraction(Uri uri) {

	}
}
