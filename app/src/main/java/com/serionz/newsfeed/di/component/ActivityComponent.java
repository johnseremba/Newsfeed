package com.serionz.newsfeed.di.component;

import com.serionz.newsfeed.di.PerActivity;
import com.serionz.newsfeed.di.module.ActivityModule;
import com.serionz.newsfeed.ui.app.MainActivity;
import com.serionz.newsfeed.ui.auth.LoginActivity;
import com.serionz.newsfeed.ui.global_news.GlobalNewsFragment;
import com.serionz.newsfeed.ui.sports_news.SportsNewsFragment;
import com.serionz.newsfeed.ui.tech_news.TechnologyNewsFragment;
import dagger.Component;

/**
 * Created by johnpaulseremba on 28/11/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

	void inject(MainActivity activity);

	void inject(LoginActivity activity);

	void inject(GlobalNewsFragment fragment);

	void inject(SportsNewsFragment fragment);

	void inject(TechnologyNewsFragment fragment);

}
