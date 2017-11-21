package com.serionz.newsfeed.main;

import android.net.Uri;
import java.util.Date;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public class News {
	public NewsSource source;
	public String author;
	public String title;
	public String description;
	public Uri url;
	public Uri urlToImage;
	public Date publishedAt;

	@Override public String toString() {
		return source.toString() + " - " + this.title + " ~ " + this.description;
	}

	//public News (NewsSource source, String author, String title, String description, Uri url, Uri urlToImage, Date publishedAt) {
	//	this.source = source;
	//	this.author = author;
	//	this.title = title;
	//	this.description = description;
	//	this.url = url;
	//	this.urlToImage = urlToImage;
	//	this.publishedAt = publishedAt;
	//}
	//
	//public void setSource(NewsSource source) {
	//	this.source = source;
	//}
	//
	//public void setAuthor(String author) {
	//	this.author = author;
	//}
	//
	//public void setTitle(String title) {
	//	this.title = title;
	//}
	//
	//public void setDescription(String description) {
	//	this.description = description;
	//}
	//
	//public void setUrl(Uri url) {
	//	this.url = url;
	//}
	//
	//public void setUrlToImage(Uri urlToImage) {
	//	this.urlToImage = urlToImage;
	//}
	//
	//public void setPublishedAt(Date publishedAt) {
	//	this.publishedAt = publishedAt;
	//}

	public String getTile () {
		return this.title;
	}
}
