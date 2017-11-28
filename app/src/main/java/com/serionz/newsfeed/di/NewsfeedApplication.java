package com.serionz.newsfeed.di;

import android.app.Application;

/**
 * Created by johnpaulseremba on 22/11/2017.
 */

public class NewsfeedApplication extends Application {

	private AppComponent appComponent;

	public AppComponent getAppComponent() {
		return appComponent;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		appComponent = initDagger(this);
	}

	protected AppComponent initDagger(NewsfeedApplication newsfeedApplication) {
		return DaggerAppComponent.builder().appModule(new AppModule(newsfeedApplication)).build();
	}
}
