package com.serionz.newsfeed.di.module;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

@Module
public class NewsfeedModule {

	public final Application mApplication;

	public NewsfeedModule(Application application) {
		this.mApplication = application;
	}

	@Provides Context provideContext() {
		return mApplication;
	}

	@Provides Application provideApplication() {
		return mApplication;
	}

}
