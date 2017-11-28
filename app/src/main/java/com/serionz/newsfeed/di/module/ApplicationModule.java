package com.serionz.newsfeed.di.module;

import android.app.Application;
import android.content.Context;
import com.serionz.newsfeed.di.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by johnpaulseremba on 22/11/2017.
 */

@Module
public class ApplicationModule {

	public final Application mApplication;

	public ApplicationModule(Application application) {
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

}
