package com.serionz.newsfeed.di.component;

import android.app.Application;
import android.content.Context;
import com.serionz.newsfeed.NewsfeedApplication;
import com.serionz.newsfeed.di.ApplicationContext;
import com.serionz.newsfeed.di.module.NewsfeedModule;
import dagger.Component;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

@Singleton
@Component(modules = { NewsfeedModule.class })
public interface NewsfeedComponent {

	void inject(NewsfeedApplication app);

	@ApplicationContext
	Context context();

	Application application();

}
