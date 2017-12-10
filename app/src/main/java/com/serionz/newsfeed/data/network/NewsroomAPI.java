package com.serionz.newsfeed.data.network;

import com.serionz.newsfeed.data.network.model.NewsList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public interface NewsroomAPI {

	@GET("top-headlines")
	Call<NewsList> loadNews(@Query("sources") String source);

}
