package com.serionz.newsfeed.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.common.SignInButton;
import com.serionz.newsfeed.R;
import com.serionz.newsfeed.news.NewsfeedActivity;

public class LoginActivity extends AppCompatActivity {
	private SignInButton btnSignIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		btnSignIn = (SignInButton) findViewById(R.id.btn_google);
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View view) {
				Intent newsFeedIntent = new Intent(getApplicationContext(), NewsfeedActivity.class);
				startActivity(newsFeedIntent);
			}
		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	@Override public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		View decorView = getWindow().getDecorView();
		if (hasFocus) {
			decorView.setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			);
		}
	}
}
