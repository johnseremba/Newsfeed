package com.serionz.newsfeed.main.global_news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.serionz.newsfeed.main.global_news.Article;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public class NewsList {
	private String status;
	private List<Article> articles = new ArrayList<>();
	private Integer newsIcon;

	public Integer getNewsIcon() {
		return newsIcon;
	}

	public void setNewsIcon(Integer newsIcon) {
		this.newsIcon = newsIcon;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public String getStatus() {
		return status;
	}

	public int size() {
		return articles.size();
	}

	@Override
	public String toString() {
		return "{ " + getArticles() + ", status = " + status + "}";
	}

}
