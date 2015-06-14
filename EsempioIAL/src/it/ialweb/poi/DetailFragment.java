package it.ialweb.poi;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify.IconValue;

public class DetailFragment extends Fragment {
	public static final String STATION = "station";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.station_detail, container, false);
		ImageView image = (ImageView) view.findViewById(R.id.map);

		FloatingActionButton fabButton = (FloatingActionButton) view.findViewById(R.id.fab_button);
		fabButton.setImageDrawable(new IconDrawable(getActivity(), IconValue.fa_share_alt));

		AppCompatActivity activity = (AppCompatActivity) getActivity();
		Station station = activity.getIntent().getParcelableExtra(STATION);

		final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
		activity.setSupportActionBar(toolbar);
		activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) view
				.findViewById(R.id.collapsing_toolbar);
		collapsingToolbar.setTitle(station.getName());

		Glide.with(activity).load(station.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);

		return view;
	}
}
