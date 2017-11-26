package com.serionz.newsfeed.main;

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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by johnpaulseremba on 26/11/2017.
 */

public class ArticleMenuAdapter extends RecyclerView.Adapter<ArticleMenuAdapter.ViewHolder> {
	private ArrayList<ArticleMenu> menuItems = new ArrayList<>();

	public ArticleMenuAdapter(ArrayList<ArticleMenu> menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v;
		v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.recyclerview_article_menu, parent, false);
		return new ViewHolder(v);
	}

	@Override public void onBindViewHolder(ViewHolder holder, int position) {
		holder.position = position;
		holder.menuText.setText(menuItems.get(position).getMenuTitle());
		GlideApp.with(holder.itemView)
				.load(menuItems.get(position).getMenuIcon())
				.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
				.into(holder.menuIcon);
	}

	@Override public int getItemCount() {
		return this.menuItems.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		private Integer position;

		@BindView(R.id.article_menu_text) TextView menuText;
		@BindView(R.id.article_menu_icon) ImageView menuIcon;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
