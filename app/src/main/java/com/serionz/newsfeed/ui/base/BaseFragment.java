package com.serionz.newsfeed.ui.base;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.serionz.newsfeed.NewsfeedApplication;
import com.serionz.newsfeed.di.component.ActivityComponent;
import com.serionz.newsfeed.di.component.DaggerActivityComponent;
import com.serionz.newsfeed.di.module.ActivityModule;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

	@Override public void showLoading() {

	}

	@Override public void hideLoading() {

	}

	@Override public void showMessage(String msg) {

	}

	@Override public void showMessage(int resId) {

	}

	@Override public boolean networkConnected() {
		return false;
	}

	public interface Callback {

		void onFragmentAttached();

		void onFragmentDetached();

	}
}
