package com.serionz.newsfeed.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import com.serionz.newsfeed.di.ActivityContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by johnpaulseremba on 28/11/2017.
 */

@Module
public class ActivityModule {

	private AppCompatActivity mActivity;

	public ActivityModule(AppCompatActivity activity) {
		this.mActivity = activity;
	}

	@Provides
	@ActivityContext
	Context provideContext() {
		return mActivity;
	}

	@Provides
	AppCompatActivity provideActivity() {
		return mActivity;
	}

}
