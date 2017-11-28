package com.serionz.newsfeed.di.component;

import android.app.Application;
import android.content.Context;
import com.serionz.newsfeed.NewsfeedApplication;
import com.serionz.newsfeed.di.ApplicationContext;
import com.serionz.newsfeed.di.module.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by johnpaulseremba on 22/11/2017.
 */

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {

	void inject(NewsfeedApplication newsfeedApplication);

	@ApplicationContext
	Context context();

	Application application();

}
