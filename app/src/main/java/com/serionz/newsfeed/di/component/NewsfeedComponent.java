package com.serionz.newsfeed.di.component;

import android.app.Application;
import android.content.Context;
import com.serionz.newsfeed.di.module.NewsfeedModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

@Singleton
@Component(modules = { NewsfeedModule.class })
public interface NewsfeedComponent {

	Context context();

	Application application();

}
