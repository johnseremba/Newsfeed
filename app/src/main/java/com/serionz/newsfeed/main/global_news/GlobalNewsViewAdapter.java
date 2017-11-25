package com.serionz.newsfeed.main.global_news;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.serionz.newsfeed.R;
import com.serionz.newsfeed.glide.GlideApp;
import java.util.HashMap;
import java.util.List;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public class GlobalNewsViewAdapter extends
		RecyclerView.Adapter<GlobalNewsViewAdapter.ViewHolder> {
	private List<Article> data;
	private HashMap<String, Integer> newsSources;
	private SelectedArticle selectedArticle;

	public GlobalNewsViewAdapter(SelectedArticle selectedArticle, List<Article> data, HashMap<String, Integer> newsSources) {
		this.selectedArticle = selectedArticle;
		this.data = data;
		this.newsSources = newsSources;
	}

	public interface SelectedArticle {
		void OnArticleClick(Uri url);
	}

	@Override
	public GlobalNewsViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v;
		v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fragment_news_layout, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(GlobalNewsViewAdapter.ViewHolder holder, int position) {
		Article articles = ((Article) data.get(position));
		holder.txtTitle.setText(articles.getTitle());
		holder.txtDesc.setText(articles.getDescription());
		holder.source.setText(articles.getSource().getName());
		holder.articleDate.setText(articles.getPublishedAt());
		holder.author.setText(articles.getAuthor());
		holder.position = position;

		if (articles.getUrlToImage() != null) {
			GlideApp.with(holder.itemView)
					.load(Uri.parse(articles.getUrlToImage()))
					.placeholder(R.drawable.bg)
					.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
					.into(holder.coverImage);
		}

		GlideApp.with(holder.itemView)
				.load(this.newsSources.get(articles.getSource().getId()))
				.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
				.into(holder.newsIcon);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private Integer position;

		@BindView(R.id.news_icon) ImageView newsIcon;
		@BindView(R.id.source) TextView source;
		@BindView(R.id.author) TextView author;
		@BindView(R.id.date) TextView articleDate;
		@BindView(R.id.cover_picture) ImageView coverImage;
		@BindView(R.id.txt_title) TextView txtTitle;
		@BindView(R.id.txt_desc) TextView txtDesc;

		private ViewHolder(View v) {
			super(v);
			ButterKnife.bind(this, v);
			txtTitle.setOnClickListener(this);
		}

		@Override public void onClick(View view) {
			Uri myUrl = Uri.parse(data.get(this.position).getUrl());
			selectedArticle.OnArticleClick(myUrl);
		}
	}

}
