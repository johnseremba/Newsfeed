package com.serionz.newsfeed.data.network.model;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public class Source {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override public String toString() {
		return "{ id: " + this.getId() + " name: " + this.getName() + " }";
	}
}
