package com.serionz.newsfeed.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.serionz.newsfeed.R;
import java.util.HashMap;

/**
 * Created by johnpaulseremba on 26/11/2017.
 */

public class ArticleMenuAdapter extends RecyclerView.Adapter<ArticleMenuAdapter.ViewHolder> {
	private HashMap<String, Integer> menuItems = new HashMap<>();

	public ArticleMenuAdapter(HashMap<String, Integer> menuItems) {
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
