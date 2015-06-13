package it.ialweb.poi;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public final class StationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private RequestManager glide;

	private final ArrayList<Station> list;

	private Activity activity;

	public StationsAdapter(Activity activity, ArrayList<Station> list) {
		this.activity = activity;
		this.list = list;
		glide = Glide.with(activity);
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int pos) {
		TextView textView = (TextView) holder.itemView.findViewById(R.id.text);
		ImageView image = (ImageView) holder.itemView.findViewById(R.id.map);
		Station station = list.get(pos);
		textView.setText(station.getName());
		glide.load(station.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
		View v = activity.getLayoutInflater().inflate(R.layout.station_row, parent, false);
		return new ViewHolder(v) {
		};
	}

	public ArrayList<Station> getList() {
		return list;
	}
}