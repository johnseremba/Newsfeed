package com.serionz.newsfeed.main;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public class Controller implements Callback<List<News>> {

	static final String BASE_URL = "https://newsapi.org/v2/";
	static final String TAG = Controller.class.getSimpleName();

	public void start() {
		Gson gson = new GsonBuilder().setLenient().create();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		GetNewsInterface getNewsAPI = retrofit.create(GetNewsInterface.class);
		Call<List<News>> call = getNewsAPI.loadNews("bbc-news");
		call.enqueue(this);
	}

	@Override public void onResponse(Call<List<News>> call, Response<List<News>> response) {
		if(response.isSuccessful()) {
			List<News> newsList = response.body();
			for(News news: newsList) {
				Log.d(TAG, "News: " + news.getTile());
			}
		} else {
			Log.w(TAG, response.errorBody().toString());
		}
	}

	@Override public void onFailure(Call<List<News>> call, Throwable t) {
		t.printStackTrace();
	}
}
