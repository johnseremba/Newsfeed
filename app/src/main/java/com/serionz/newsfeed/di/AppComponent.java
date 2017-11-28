package com.serionz.newsfeed.di;

import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by johnpaulseremba on 22/11/2017.
 */

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {

}
