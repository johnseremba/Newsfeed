package com.serionz.newsfeed.dagger;

import com.serionz.newsfeed.main.global_news.GlobalNewsFragment;
import dagger.Component;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;
import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Created by johnpaulseremba on 22/11/2017.
 */

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {

}
