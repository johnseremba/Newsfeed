package com.serionz.newsfeed.auth;

/**
 * Created by johnpaulseremba on 17/11/2017.
 */

public class User {
	private String id;
	private String displayName;
	private String email;
	private String profilePicture;

	public User(String id, String displayName, String email, String profilePicture) {
		this.id = id;
		this.displayName = displayName;
		this.email = email;
		this.profilePicture = profilePicture;
	}

	public String getId() {
		return this.id;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getProfilePicture() {
		return this.profilePicture;
	}

}
