package com.serionz.newsfeed.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.serionz.newsfeed.NewsfeedApplication;
import com.serionz.newsfeed.di.component.ActivityComponent;
import com.serionz.newsfeed.di.component.DaggerActivityComponent;
import com.serionz.newsfeed.di.module.ActivityModule;
import com.serionz.newsfeed.utils.CommonUtils;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

public class BaseActivity extends AppCompatActivity implements MvpView, BaseFragment.Callback {
	private ProgressDialog mProgressDialog;

	private ActivityComponent mActivityComponent;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivityComponent = DaggerActivityComponent.builder()
				.activityModule(new ActivityModule(this))
				.newsfeedComponent(((NewsfeedApplication) getApplication()).getNewsfeedComponent())
				.build();
	}

	public ActivityComponent getActivityComponent() {
		return mActivityComponent;
	}

	@TargetApi(Build.VERSION_CODES.M)
	public void requestPermissionsSafely(String[] permissions, int requestCode) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			requestPermissions(permissions, requestCode);
		}
	}

	@TargetApi(Build.VERSION_CODES.M)
	public boolean hasPermission(String permission) {
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
				checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
	}

	@Override public void showLoading() {
		hideLoading();
		mProgressDialog = CommonUtils.showDialogLoading(this);
	}

	@Override public void hideLoading() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.cancel();
		}
	}

	@Override public void showMessage(String msg) {
		if (msg != null) {
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Some error occurred!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override public void showMessage(int resId) {
		showMessage(getString(resId));
	}

	@Override public boolean networkConnected() {
		ConnectivityManager connectivityManager =
				(ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
		assert connectivityManager != null;
		NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
		return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
	}

	@Override public void onFragmentAttached() {

	}

	@Override public void onFragmentDetached() {

	}
}
