package com.serionz.newsfeed.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.serionz.newsfeed.R;
import java.util.List;

/**
 * Created by johnpaulseremba on 21/11/2017.
 */

public class GlobalNewsViewAdapter extends
		RecyclerView.Adapter<GlobalNewsViewAdapter.ViewHolder> {
	private List<News> data;

	public class ViewHolder extends RecyclerView.ViewHolder {
		public TextView txtTitle;
		public TextView txtDesc;

		public ViewHolder(View v) {
			super(v);
			txtTitle = (TextView) v.findViewById(R.id.txt_title);
			txtDesc = (TextView) v.findViewById(R.id.txt_desc);
		}
	}

	public GlobalNewsViewAdapter(List<News> data) {
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
		News news = ((News) data.get(position));
		holder.txtTitle.setText(news.title);
		holder.txtDesc.setText(news.description);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

}
