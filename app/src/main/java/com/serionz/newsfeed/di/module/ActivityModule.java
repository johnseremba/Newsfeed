package com.serionz.newsfeed.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import com.serionz.newsfeed.BuildConfig;
import com.serionz.newsfeed.R;
import com.serionz.newsfeed.di.ActivityContext;
import com.serionz.newsfeed.utils.AppConstants;
import com.serionz.newsfeed.utils.NewsUtils;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johnpaulseremba on 28/11/2017.
 */

@Module
public class ActivityModule {

	private AppCompatActivity mActivity;

	public ActivityModule(AppCompatActivity activity) {
		this.mActivity = activity;
	}

	@Provides
	@ActivityContext
	Context provideContext() {
		return mActivity;
	}

	@Provides
	AppCompatActivity provideActivity() {
		return mActivity;
	}

}
