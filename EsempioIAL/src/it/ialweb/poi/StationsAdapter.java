package it.ialweb.poi;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

public final class StationsAdapter extends RecyclerView.Adapter<StationViewHolder> {

	private RequestManager glide;

	private final ArrayList<Station> list;

	private Activity activity;

	private OpenDetailListener openDetailListener;

	public StationsAdapter(Activity activity, ArrayList<Station> list, OpenDetailListener openDetailListener) {
		this.activity = activity;
		this.list = list;
		this.openDetailListener = openDetailListener;
		glide = Glide.with(activity);
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public void onBindViewHolder(StationViewHolder holder, int pos) {
		holder.populate(list.get(pos));
	}

	@Override
	public StationViewHolder onCreateViewHolder(ViewGroup parent, int type) {
		View v = activity.getLayoutInflater().inflate(R.layout.station_row, parent, false);
		return new StationViewHolder(v, glide, openDetailListener);
	}

	public ArrayList<Station> getList() {
		return list;
	}
}