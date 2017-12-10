package com.serionz.newsfeed.utils;

import com.serionz.newsfeed.R;
import com.serionz.newsfeed.data.db.model.ArticleMenu;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by johnpaulseremba on 08/12/2017.
 */

public final class NewsUtils {

	private NewsUtils() { }

	public static final HashMap<String, Integer> NEWS_SOURCES = new HashMap<String, Integer>(){
		{
			put("bbc-news", R.drawable.bbc_logo);
			put("cnn", R.drawable.cnn_logo);
			put("al-jazeera-english", R.drawable.aljazeera_logo);
			put("bloomberg", R.drawable.bloomberg_logo);
			put("business-insider", R.drawable.business_logo);
			put("buzzfeed", R.drawable.buzzfeed_logo);
		}
	};

	public static final ArrayList<ArticleMenu> ARTICLE_MENU = new ArrayList<ArticleMenu>(){
		{
			add(new ArticleMenu("share", "Share", R.drawable.ic_share_black_24dp));
			add(new ArticleMenu("hide", "Hide", R.drawable.ic_visibility_off_black_24dp));
			add(new ArticleMenu("notInterested", "Not interested in", R.drawable.ic_close_black_24dp));
			add(new ArticleMenu("report", "Report Story", R.drawable.ic_report_black_24dp));
			add(new ArticleMenu("customize", "Customize Stories", R.drawable.ic_settings_black_24dp));
		}
	};

	public static final String BASE_URL = "https://newsapi.org/v2/";

}
