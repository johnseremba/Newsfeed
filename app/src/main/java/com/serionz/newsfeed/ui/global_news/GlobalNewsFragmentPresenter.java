package com.serionz.newsfeed.ui.global_news;

import android.util.Log;
import com.serionz.newsfeed.data.network.NewsroomAPI;
import com.serionz.newsfeed.data.network.model.NewsList;
import com.serionz.newsfeed.utils.NewsUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by johnpaulseremba on 28/11/2017.
 */

public class GlobalNewsFragmentPresenter implements GlobalNewsFragmentContract.Presenter {

	private static final String TAG = GlobalNewsFragmentPresenter.class.getSimpleName();
	private GlobalNewsFragmentContract.View view;

	public GlobalNewsFragmentPresenter(GlobalNewsFragmentContract.View view) {
		this.view = view;
	}

	@Override public void loadArticles(NewsroomAPI getNewsAPI) {

		for(String source: NewsUtils.NEWS_SOURCES.keySet()){
			Call<NewsList> call = getNewsAPI.loadNews(source);
			call.enqueue(new Callback<NewsList>() {
				@Override public void onResponse(Call<NewsList> call, Response<NewsList> response) {
					if (response.isSuccessful()) {
						NewsList newslist = response.body();
						view.receivedNews(newslist);
					}
				}
				@Override public void onFailure(Call<NewsList> call, Throwable t) {
					Log.w(TAG, "Failed! ", t);
				}
			});
		}

	}
}
