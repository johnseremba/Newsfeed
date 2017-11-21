package com.serionz.newsfeed.main;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public class NewsSource {
	public int id;
	public String name;

	@Override public String toString() {
		return this.id + " - " + this.name;
	}

	//public NewsSource(int id, String name) {
	//	this.id = id;
	//	this.name = name;
	//}
	//
	//public int getId() {
	//	return this.id;
	//}
	//
	//public String getName() {
	//	return this.name;
	//}
}
