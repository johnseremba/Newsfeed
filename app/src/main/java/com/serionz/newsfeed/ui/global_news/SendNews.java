package com.serionz.newsfeed.ui.global_news;

import com.serionz.newsfeed.data.network.model.NewsList;

/**
 * Created by johnpaulseremba on 23/11/2017.
 */

public interface SendNews {
	void receivedNews(NewsList newsList);
}
