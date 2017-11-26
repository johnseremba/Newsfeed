package com.serionz.newsfeed.main;

/**
 * Created by johnpaulseremba on 26/11/2017.
 */

public class ArticleMenu {
	private String menuTitle;
	private Integer menuIcon;

	public ArticleMenu(String menuTitle, Integer menuIcon) {
		this.menuTitle = menuTitle;
		this.menuIcon = menuIcon;
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
