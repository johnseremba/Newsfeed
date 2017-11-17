package com.serionz.newsfeed.auth;

import android.net.Uri;

/**
 * Created by johnpaulseremba on 17/11/2017.
 */

public class User {
	private String id;
	private String displayName;
	private String email;
	private Uri profilePictureURI;

	public User(String id, String displayName, String email, Uri profilePictureURI) {
		this.id = id;
		this.displayName = displayName;
		this.email = email;
		this.profilePictureURI = profilePictureURI;
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

	public Uri getProfilePictureURI() {
		return this.profilePictureURI;
	}

}
