package com.serionz.newsfeed.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import com.serionz.newsfeed.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalNewsFragment extends Fragment {
	private static final String TAG = GlobalNewsFragment.class.getSimpleName();
	private OnFragmentInteractionListener mListener;
	private RecyclerView recyclerView;
	private NewsroomAPI newsRoomAPI;

	public GlobalNewsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_global_news, container, false);
		recyclerView = (RecyclerView) view.findViewById(R.id.news_list);
		recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
		createNewsFeedAPI();
		return view;
	}

	OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
		@Override public okhttp3.Response intercept(Chain chain) throws IOException {
			Request originalRequest = chain.request();
			Request.Builder builder = originalRequest.newBuilder().header("Authorization",
					getString(R.string.newsroom_api_key));
			Request newRequest = builder.build();
			return chain.proceed(newRequest);
		}
	}).build();

	private void createNewsFeedAPI() {
		Retrofit.Builder builder =
				new Retrofit.Builder()
						.baseUrl(NewsroomAPI.BASE_URL)
						.addConverterFactory(GsonConverterFactory.create());

		Retrofit retrofit = builder.client(okHttpClient).build();
		NewsroomAPI getNewsAPI = retrofit.create(NewsroomAPI.class);

		Call<NewsList> call = getNewsAPI.loadNews("bbc-news");
		call.enqueue(new Callback<NewsList>() {
			@Override public void onResponse(Call<NewsList> call, Response<NewsList> response) {
				if (response.isSuccessful()) {
					NewsList newslist = response.body();
					Log.w(TAG, "Article result: " + newslist);
				} else {
					Toast.makeText(getContext(), "Some error occurred while fetching results!",
							Toast.LENGTH_SHORT).show();
				}
			}

			@Override public void onFailure(Call<NewsList> call, Throwable t) {
				Log.w(TAG, "Failed! ", t);
			}
		});
	}

	@Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction(Uri uri);
	}
}
