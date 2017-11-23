package com.serionz.newsfeed.main.global_news;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serionz.newsfeed.R;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johnpaulseremba on 22/11/2017.
 */

public class Controller {

	private static final String TAG = Controller.class.getSimpleName();
	@Inject View view;

	private HttpLoggingInterceptor getLoggingInterceptor() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return loggingInterceptor;
	}

	OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
			.addInterceptor(getLoggingInterceptor())
			.addInterceptor(new Interceptor() {
				@Override public okhttp3.Response intercept(Chain chain) throws IOException {
					Request originalRequest = chain.request();
					Request.Builder builder = originalRequest.newBuilder().header("Authorization",
							view.getContext().getString(R.string.newsroom_api_key));
					Request newRequest = builder.build();
					return chain.proceed(newRequest);
				}
			})
			.build();

	private void createNewsFeedAPI() {
		Gson gson = new GsonBuilder().create();
		Retrofit.Builder builder =
				new Retrofit.Builder()
						.baseUrl(NewsroomAPI.BASE_URL)
						.addConverterFactory(GsonConverterFactory.create(gson));

		Retrofit retrofit = builder.client(okHttpClient).build();
		NewsroomAPI getNewsAPI = retrofit.create(NewsroomAPI.class);

		Call<NewsList> call = getNewsAPI.loadNews("bbc-news");
		call.enqueue(new Callback<NewsList>() {
			@Override public void onResponse(Call<NewsList> call, Response<NewsList> response) {
				if (response.isSuccessful()) {
					NewsList newslist = response.body();
					Log.w(TAG, "Article result: " + new Gson().toJson(newslist));
				} else {
					Toast.makeText(view.getContext(), "Some error occurred while fetching results!",
							Toast.LENGTH_SHORT).show();
				}
			}

			@Override public void onFailure(Call<NewsList> call, Throwable t) {
				Log.w(TAG, "Failed! ", t);
			}
		});
	}

}
