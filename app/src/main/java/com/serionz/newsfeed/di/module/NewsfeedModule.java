package com.serionz.newsfeed.di.module;

import android.app.Application;
import android.content.Context;
import com.serionz.newsfeed.BuildConfig;
import com.serionz.newsfeed.data.network.NewsroomAPI;
import com.serionz.newsfeed.di.ApiInfo;
import com.serionz.newsfeed.di.ApplicationContext;
import com.serionz.newsfeed.utils.AppConstants;
import com.serionz.newsfeed.utils.NewsUtils;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
