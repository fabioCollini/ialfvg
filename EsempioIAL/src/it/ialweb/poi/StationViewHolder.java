package it.ialweb.poi;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class StationViewHolder extends ViewHolder {

	private RequestManager glide;
	private TextView textView;
	private ImageView image;
	private Station station;

	public StationViewHolder(View itemView, RequestManager glide, final OpenDetailListener openDetailListener) {
		super(itemView);
		this.glide = glide;
		textView = (TextView) itemView.findViewById(R.id.text);
		image = (ImageView) itemView.findViewById(R.id.map);
		final View mapLayout = itemView.findViewById(R.id.map_layout);
		itemView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openDetailListener.openDetail(station, mapLayout);
			}
		});
	}

	public void populate(Station station) {
		this.station = station;
		textView.setText(station.getName());
		glide.load(station.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
	}
}
