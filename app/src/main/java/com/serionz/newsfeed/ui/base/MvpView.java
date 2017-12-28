package com.serionz.newsfeed.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

public interface MvpView {

	void showLoading();

	void hideLoading();

	void showMessage(String msg);

	void showMessage(@StringRes int resId);

	boolean networkConnected();

}
