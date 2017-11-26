package com.serionz.newsfeed.main.global_news;

import android.net.Uri;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

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
		void OnArticleMenuClick(Article article);
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
		Article article = ((Article) data.get(position));
		holder.txtTitle.setText(article.getTitle());
		holder.txtDesc.setText(article.getDescription());
		holder.source.setText(article.getSource().getName());
		holder.author.setText(article.getAuthor());
		holder.position = position;

		//holder.articleDate.setText(articles.getPublishedAt());
		try {
			if (article.getPublishedAt() != null) {
				DateFormat utcFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm");
				utcFormat.setTimeZone(TimeZone.getDefault());

				Date publishedAt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
						.parse(article.getPublishedAt());

				Date currentDate = new Date();

				long diff = currentDate.getTime() - publishedAt.getTime();
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000);
				int diffInDays =
						(int) ((currentDate.getTime() - publishedAt.getTime()) / (1000 * 60 * 60 * 24));
				String publishedDate;

				if (diffInDays > 1) {
					if (diffInDays > 2) {
						publishedDate = utcFormat.format(publishedAt);
					} else {
						publishedDate = diffInDays + " days ago";
					}
				} else if (diffHours > 24) {
					publishedDate = diffHours + " hrs ago";
				} else {
					publishedDate = diffMinutes + " mins ago";
				}

				holder.articleDate.setText(publishedDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (article.getUrlToImage() != null) {
			GlideApp.with(holder.itemView)
					.load(Uri.parse(article.getUrlToImage()))
					.placeholder(R.drawable.bg)
					.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
					.into(holder.coverImage);
		}

		GlideApp.with(holder.itemView)
				.load(this.newsSources.get(article.getSource().getId()))
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
		@BindView(R.id.article_menu) ImageView articleMenu;

		private ViewHolder(View v) {
			super(v);
			ButterKnife.bind(this, v);
			txtTitle.setOnClickListener(this);
			txtDesc.setOnClickListener(this);
			coverImage.setOnClickListener(this);

			articleMenu.setOnClickListener(this.bottomSheet);
			source.setOnClickListener(this.bottomSheet);
		}

		private View.OnClickListener bottomSheet = new View.OnClickListener() {
			@Override public void onClick(View view) {
				selectedArticle.OnArticleMenuClick(data.get(position));
			}
		};

		@Override public void onClick(View view) {
			Uri myUrl = Uri.parse(data.get(this.position).getUrl());
			selectedArticle.OnArticleClick(myUrl);
		}
	}

}
