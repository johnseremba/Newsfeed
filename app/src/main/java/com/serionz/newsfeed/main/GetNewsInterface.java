package com.serionz.newsfeed.main;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public interface GetNewsInterface {

	@GET("top-headlines")
	Call<List<News>> loadNews(@Query("sources") String source);

}
