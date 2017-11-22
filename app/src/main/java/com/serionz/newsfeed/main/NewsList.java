package com.serionz.newsfeed.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public class NewsList {
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("articles")
	@Expose
	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClassPojo [articles = " + articles + ", status = " + status + "]";
	}

}
