package com.serionz.newsfeed.main.global_news;

import com.serionz.newsfeed.main.global_news.NewsList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public interface NewsroomAPI {
	String BASE_URL = "https://newsapi.org/v2/";

	@GET("top-headlines")
	Call<NewsList> loadNews(@Query("sources") String source);

}
