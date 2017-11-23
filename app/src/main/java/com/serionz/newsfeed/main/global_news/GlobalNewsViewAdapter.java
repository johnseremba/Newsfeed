package com.serionz.newsfeed.main.global_news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.serionz.newsfeed.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public class GlobalNewsViewAdapter extends
		RecyclerView.Adapter<GlobalNewsViewAdapter.ViewHolder> {
	private ArrayList<Article> data;

	public GlobalNewsViewAdapter(ArrayList<Article> data) {
		this.data = data;
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
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public TextView txtTitle;
		public TextView txtDesc;

		public ViewHolder(View v) {
			super(v);
			txtTitle = (TextView) v.findViewById(R.id.txt_title);
			txtDesc = (TextView) v.findViewById(R.id.txt_desc);
		}
	}

}
