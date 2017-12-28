package com.serionz.newsfeed.data.network.model;

import java.util.ArrayList;
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
