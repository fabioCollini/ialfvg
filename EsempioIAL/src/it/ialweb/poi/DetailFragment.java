package it.ialweb.poi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetailFragment extends Fragment {
	public static final String STATION = "station";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.station_detail, container, false);
		TextView textView = (TextView) view.findViewById(R.id.text);
		ImageView image = (ImageView) view.findViewById(R.id.map);

		Station station = getActivity().getIntent().getParcelableExtra(STATION);

		textView.setText(station.getName());
		Glide.with(getActivity()).load(station.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);

		return view;
	}
}
