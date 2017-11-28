package com.serionz.newsfeed.di.component;

import com.serionz.newsfeed.di.module.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by johnpaulseremba on 22/11/2017.
 */

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {

}
