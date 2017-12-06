package com.serionz.newsfeed.di.module;

import android.app.Application;
import android.content.Context;
import com.serionz.newsfeed.BuildConfig;
import com.serionz.newsfeed.di.ApiInfo;
import com.serionz.newsfeed.di.ApplicationContext;
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

	@Provides
	@ApplicationContext
	Context provideContext() {
		return mApplication;
	}

	@Provides
	@ApplicationContext
	Application provideApplication() {
		return mApplication;
	}

	@Provides
	@ApiInfo
	String provideNewsroomApiKey() {
		return BuildConfig.NEWS_API_KEY;
	}
}
