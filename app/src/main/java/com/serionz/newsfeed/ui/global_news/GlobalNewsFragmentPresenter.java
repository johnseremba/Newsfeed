package com.serionz.newsfeed.ui.global_news;

/**
 * Created by johnpaulseremba on 28/11/2017.
 */

public class GlobalNewsFragmentPresenter implements GlobalNewsFragmentContract.Presenter {

	private static final String TAG = GlobalNewsFragmentPresenter.class.getSimpleName();
	private GlobalNewsFragmentContract.View view;

	public GlobalNewsFragmentPresenter(GlobalNewsFragmentContract.View view) {
		this.view = view;
	}


}
