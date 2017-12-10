package com.serionz.newsfeed.data.db.model;

/**
 * Created by johnpaulseremba on 26/11/2017.
 */

public class ArticleMenu {
	private String tag;
	private String menuTitle;
	private Integer menuIcon;

	public ArticleMenu(String tag, String menuTitle, Integer menuIcon) {
		this.tag = tag;
		this.menuTitle = menuTitle;
		this.menuIcon = menuIcon;
	}

	public String getTag() {
		return tag;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public Integer getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(Integer menuIcon) {
		this.menuIcon = menuIcon;
	}

	@Override public String toString() {
		return "{ title: " + this.menuTitle + " icon: " + this.menuIcon + " }";
	}
}
