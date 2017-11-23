package com.serionz.newsfeed.main.global_news;

import android.support.v7.widget.RecyclerView;
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

	public GlobalNewsViewAdapter(List<Article> data, HashMap<String, Integer> newsSources) {
		this.data = data;
		this.newsSources = newsSources;
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

		int imageWidth = holder.coverImage.getMeasuredWidth();
		int imageHeight = holder.coverImage.getMeasuredHeight();
		GlideApp.with(holder.itemView)
				.load(articles.getUrlToImage())
				.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
				.override(344, 167)
				.into(holder.coverImage);

		GlideApp.with(holder.itemView)
				.load(this.newsSources.get(articles.getSource().getId()))
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(holder.newsIcon);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
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
		}
	}

}
