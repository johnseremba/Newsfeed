package com.serionz.newsfeed.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.serionz.newsfeed.R;
import com.serionz.newsfeed.news.NewsfeedActivity;

public class LoginActivity extends AppCompatActivity {
	private final int RC_GOOGLE_SIGN_IN = 4001;
	private final int RC_FACEBOOK_SIGN_IN = 4002;
	private final String TAG = LoginActivity.class.getSimpleName();
	private GoogleSignInClient mGoogleSignInClient;

	@BindView(R.id.btn_google) SignInButton btnGoogleSignIn;
	@BindView(R.id.btn_facebook) LoginButton btnFacebookSignIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);

		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestEmail()
				.build();
		mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

	}

	@Override protected void onStart() {
		super.onStart();
		GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
		this.updateUI(account);
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

	@Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case RC_GOOGLE_SIGN_IN:
				Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
				this.handleGoogleSignInResult(task);
				break;
		}
	}

	private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
		try {
			GoogleSignInAccount account = completedTask.getResult(ApiException.class);
			updateUI(account);
		} catch (ApiException e) {
			Log.w(TAG, "Google Sign In failed: " + e.getStatusCode());
			updateUI(null);
		}
	}

	@OnClick(R.id.btn_google)
	public void startMainActivity() {
		Intent signInIntent = mGoogleSignInClient.getSignInIntent();
		startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
	}

	public void updateUI(GoogleSignInAccount account) {
		User currentUser = new User();
		if(account != null) {
			Log.d(TAG, account.getDisplayName() + ", " +
					account.getEmail() + ", " +
					account.getDisplayName() + ", " +
					account.getId() + ", " +
					account.getPhotoUrl()
			);
			Intent newsFeedIntent = new Intent(getApplicationContext(), NewsfeedActivity.class);
			startActivity(newsFeedIntent);
		}
	}

}
