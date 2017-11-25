package com.serionz.newsfeed.main.global_news;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.serionz.newsfeed.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class GlobalNewsFragment extends Fragment implements SendNews, GlobalNewsViewAdapter.SelectedArticle {
	private static final String TAG = GlobalNewsFragment.class.getSimpleName();
	private OnFragmentInteractionListener mListener;
	private RecyclerView recyclerView;
	private Controller mController;
	private GlobalNewsViewAdapter mGlobalNewsViewAdapter;
	private List<Article> mArticleList = new ArrayList<>();
	private HashMap<String, Integer> newsSources;

	final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";
	final String websiteURL = "http://viralandroid.com/";

	CustomTabsClient mCustomTabsClient;
	CustomTabsSession mCustomTabsSession;
	CustomTabsServiceConnection mCustomTabsServiceConnection;
	CustomTabsIntent mCustomTabsIntent;

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

		newsSources = new HashMap<String, Integer>(){
			{
				put("bbc-news", R.drawable.bbc_logo);
				put("cnn", R.drawable.cnn_logo);
				put("al-jazeera-english", R.drawable.aljazeera_logo);
				put("bloomberg", R.drawable.bloomberg_logo);
				put("business-insider", R.drawable.business_logo);
				put("buzzfeed", R.drawable.buzzfeed_logo);
			}
		};

		recyclerView = (RecyclerView) view.findViewById(R.id.news_list);

		mGlobalNewsViewAdapter = new GlobalNewsViewAdapter(this, mArticleList, newsSources);
		recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(mGlobalNewsViewAdapter);

		mController = new Controller(getContext());
		mController.fetchGlobalNews(this, newsSources);
		mGlobalNewsViewAdapter.notifyDataSetChanged();

		mCustomTabsServiceConnection = new CustomTabsServiceConnection() {
			@Override public void onServiceDisconnected(ComponentName componentName) {
				mCustomTabsClient= null;
			}

			@Override
			public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
				mCustomTabsClient = client;
				mCustomTabsClient.warmup(0L);
				mCustomTabsSession = mCustomTabsClient.newSession(null);
			}
		};

		CustomTabsClient.bindCustomTabsService(getContext(), CUSTOM_TAB_PACKAGE_NAME, mCustomTabsServiceConnection);
		return view;
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

	@Override public void receivedNews(NewsList newsList) {
		ArrayList<Article> oldArticles = new ArrayList<>();
		oldArticles.addAll(mArticleList);
		oldArticles.addAll(newsList.getArticles());

		Collections.sort(oldArticles, Article.ArticleComparator);
		mArticleList.clear();
		mArticleList.addAll(oldArticles);

		this.mGlobalNewsViewAdapter.notifyDataSetChanged();
	}

	@Override public void OnArticleClick(Uri url) {
		mCustomTabsIntent = new CustomTabsIntent.Builder(mCustomTabsSession)
				.setShowTitle(true)
				.setToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))
				.setSecondaryToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark))
				.build();
		mCustomTabsIntent.launchUrl(getContext(), url);
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction(Uri uri);
	}
}
