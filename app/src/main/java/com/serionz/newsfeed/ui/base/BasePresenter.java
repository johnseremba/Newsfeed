package com.serionz.newsfeed.ui.base;

import javax.inject.Inject;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

	public final String TAG = BasePresenter.class.getSimpleName();
	private V mMvpView;

	@Inject
	public BasePresenter() {

	}

	@Override
	public void onAttach(V mvpView) {
		mMvpView = mvpView;
	}

	@Override
	public void onDetach() {
		mMvpView = null;
	}

	public V getmMvpView() {
		return mMvpView;
	}

	private boolean isViewAttached() {
		return mMvpView != null;
	}

	public void checkViewIsAttached() {
		if (!isViewAttached()) throw new MvpViewNotAttachedException();
	}

	public static class MvpViewNotAttachedException extends RuntimeException {
		public MvpViewNotAttachedException() {
			super("Please call Presenter.onAttach(MvpView) before requesting data to the Presenter");
		}
	}

}
