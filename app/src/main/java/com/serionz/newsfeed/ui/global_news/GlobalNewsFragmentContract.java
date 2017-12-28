package com.serionz.newsfeed.ui.global_news;

import com.serionz.newsfeed.data.network.NewsroomAPI;
import com.serionz.newsfeed.data.network.model.NewsList;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

public interface GlobalNewsFragmentContract {

	interface View {
		void createShareIntent();
		void initializeNewsData();
		void receivedNews(NewsList newsList);
	}

	interface Presenter {
		void loadArticles(NewsroomAPI getNewsAPI);
	}

}
