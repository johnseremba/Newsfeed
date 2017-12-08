package com.serionz.newsfeed.ui.base;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.Unbinder;
import com.serionz.newsfeed.di.component.ActivityComponent;
import com.serionz.newsfeed.utils.CommonUtils;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

	private BaseActivity mActivity;
	private ProgressDialog mProgressDialog;
	private Unbinder mUnbinder;
	private Context mContext;

	@Override public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);
	}

	@Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setUp(view);
	}

	protected abstract void setUp(View view);

	@Override public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof BaseActivity) {
			BaseActivity activity = (BaseActivity) context;
			this.mActivity = activity;
			activity.onFragmentAttached();
			mContext = context;
		}
	}

	@Override public void showLoading() {
		hideLoading();
		mProgressDialog = CommonUtils.showDialogLoading(mContext);
	}

	@Override public void hideLoading() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.cancel();
		}
	}

	@Override public void showMessage(String msg) {
		if (mActivity != null) {
			mActivity.showMessage(msg);
		}
	}

	@Override public void showMessage(int resId) {
		if (mActivity != null) {
			mActivity.showMessage(resId);
		}
	}

	@Override public boolean networkConnected() {
		if (mActivity != null) {
			return mActivity.networkConnected();
		}
		return false;
	}

	@Override public void onDetach() {
		mActivity = null;
		super.onDetach();
	}

	@Override public void onDestroy() {
		if (mUnbinder != null) {
			mUnbinder.unbind();
		}
		super.onDestroy();
	}

	public ActivityComponent getActivityComponent() {
		if (mActivity != null) {
			return mActivity.getActivityComponent();
		}
		return null;
	}

	public BaseActivity getBaseActivity() {
		return mActivity;
	}

	public void setUnbinder(Unbinder unbinder) {
		mUnbinder = unbinder;
	}

	public interface Callback {

		void onFragmentAttached();

		void onFragmentDetached(String tag);

	}
}
