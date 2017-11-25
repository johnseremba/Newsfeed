package com.serionz.newsfeed.main.global_news;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Comparator;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public class Article {
	private Source source;
	private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImage;
	private String publishedAt;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Source getSource() {
		return source;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	@Override public String toString() {
		return "{ " + this.getTitle() + " source: " + this.source + " }";
	}

	public static Comparator<Article> ArticleComparator = new Comparator<Article>() {

		@Override public int compare(Article article, Article t1) {
			String article1 = article.getPublishedAt() != null ? article.getPublishedAt().toUpperCase() : "";
			String article2 = t1.getPublishedAt() != null ? t1.getPublishedAt().toUpperCase() : "";
			return article1.compareTo(article2);
		}
	};

}
