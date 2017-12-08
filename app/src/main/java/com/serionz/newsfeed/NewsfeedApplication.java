package com.serionz.newsfeed;

import android.app.Application;
import com.serionz.newsfeed.di.component.DaggerNewsfeedComponent;
import com.serionz.newsfeed.di.component.NewsfeedComponent;
import com.serionz.newsfeed.di.module.NewsfeedModule;

/**
 * Created by johnpaulseremba on 22/11/2017.
 */

public class NewsfeedApplication extends Application {

	private NewsfeedComponent mNewsfeedComponent;

	public NewsfeedComponent getNewsfeedComponent() {
		return mNewsfeedComponent;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mNewsfeedComponent = DaggerNewsfeedComponent
				.builder()
				.newsfeedModule(new NewsfeedModule(this))
				.build();
		mNewsfeedComponent.inject(this);
	}
}
