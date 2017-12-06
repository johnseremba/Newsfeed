package com.serionz.newsfeed.ui.base;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

public interface MvpPresenter<V extends MvpView> {

	void onAttach(V mvpView);

	void onDetach();

}
