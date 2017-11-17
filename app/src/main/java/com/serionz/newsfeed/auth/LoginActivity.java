package com.serionz.newsfeed.auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.serionz.newsfeed.R;
import com.serionz.newsfeed.news.NewsfeedActivity;

public class LoginActivity extends AppCompatActivity {
	private final int RC_GOOGLE_SIGN_IN = 4001;
	private final int RC_FACEBOOK_SIGN_IN = 4002;
	private final String TAG = LoginActivity.class.getSimpleName();
	private GoogleSignInClient mGoogleSignInClient;
	private FirebaseAuth mAuth;
	private CallbackManager mCallbackManager;

	@BindView(R.id.login_progress_bar) ProgressBar progressBar;
	@BindView(R.id.btn_facebook) LoginButton btnFacebookSignIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);

		mAuth = FirebaseAuth.getInstance();
		progressBar.setVisibility(View.INVISIBLE);

		// Google login implementation
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestIdToken(getString(R.string.default_web_client_id))
				.requestEmail()
				.build();
		mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

		// Facebook login implementation
		mCallbackManager = CallbackManager.Factory.create();
		btnFacebookSignIn.setReadPermissions("email", "public_profile");
		btnFacebookSignIn.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
			@Override public void onSuccess(LoginResult loginResult) {
				progressBar.setVisibility(View.VISIBLE);
				handleFacebookAccessToken(loginResult.getAccessToken());
			}

			@Override public void onCancel() {
				Log.d(TAG, "Facebook login cancelled!");
				Toast.makeText(LoginActivity.this, "Facebook authentication canceled!",
						Toast.LENGTH_SHORT).show();
			}

			@Override public void onError(FacebookException error) {
				Log.w(TAG, "Facebook login error!", error);
				Toast.makeText(LoginActivity.this, "Facebook authentication failed!",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override protected void onStart() {
		super.onStart();
		FirebaseUser account = mAuth.getCurrentUser();
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
			default:
				Log.d(TAG, "Facebook RequestCode: " + requestCode);
				mCallbackManager.onActivityResult(requestCode, resultCode, data);
				break;
		}
	}

	@OnClick(R.id.btn_google)
	public void signInWithGoogle() {
		progressBar.setVisibility(View.VISIBLE);
		Intent signInIntent = mGoogleSignInClient.getSignInIntent();
		startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
	}

	public void handleGoogleSignInResult(Task<GoogleSignInAccount> task) {
		try {
			GoogleSignInAccount account = task.getResult(ApiException.class);
			AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
			mAuth.signInWithCredential(credential)
					.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
						@Override public void onComplete(@NonNull Task<AuthResult> task) {
							if (task.isSuccessful()) {
								FirebaseUser user = mAuth.getCurrentUser();
								updateUI(user);
							} else {
								Log.w(TAG, "Google sign in failed: " + task.getException());
								Toast.makeText(getApplicationContext(), "Authentication failed!",
										Toast.LENGTH_SHORT).show();
								updateUI(null);
							}
						}
					});
		} catch (ApiException e) {
			Log.w(TAG, "Google sign in failed!");
			updateUI(null);
		}
	}

	private void handleFacebookAccessToken(AccessToken token) {
		AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
		mAuth.signInWithCredential(credential)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							FirebaseUser user = mAuth.getCurrentUser();
							updateUI(user);
						} else {
							Log.w(TAG, "facebook sign in failure:", task.getException());
							Toast.makeText(LoginActivity.this, "Facebook authentication failed!",
									Toast.LENGTH_SHORT).show();
							updateUI(null);
						}
					}
				});
	}

	private void updateUI(FirebaseUser account) {
		if(account != null) {
			User currentUser = new User(
					account.getUid(),
					account.getDisplayName(),
					account.getEmail(),
					account.getPhotoUrl()
			);
			progressBar.setVisibility(View.INVISIBLE);
			Intent newsFeedIntent = new Intent(getApplicationContext(), NewsfeedActivity.class);
			startActivity(newsFeedIntent);
		}
	}

}
