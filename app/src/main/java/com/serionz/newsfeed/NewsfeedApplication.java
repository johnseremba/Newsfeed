package com.serionz.newsfeed;

import android.app.Application;
import com.serionz.newsfeed.di.component.ApplicationComponent;
import com.serionz.newsfeed.di.component.DaggerApplicationComponent;
import com.serionz.newsfeed.di.module.ApplicationModule;

/**
 * Created by johnpaulseremba on 22/11/2017.
 */

public class NewsfeedApplication extends Application {

	private ApplicationComponent applicationComponent;

	public ApplicationComponent getApplicationComponent() {
		return applicationComponent;
	}

	public void setApplicationComponent(ApplicationComponent applicationComponent) {
		this.applicationComponent = applicationComponent;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		applicationComponent = DaggerApplicationComponent
				.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
	}
}
