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
	private CollapsingToolbarLayout collapsingToolbar;
	private ImageView image;
	private View text;
	private View gallery;
	private FloatingActionButton fabButton;
	private Station station;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.station_detail_fragment, container, false);
		image = (ImageView) view.findViewById(R.id.map);
		text = view.findViewById(R.id.detail_text);
		gallery = view.findViewById(R.id.detail_gallery);

		fabButton = (FloatingActionButton) view.findViewById(R.id.fab_button);
		fabButton.setImageDrawable(new IconDrawable(getActivity(), IconValue.fa_share_alt));
		fabButton.setVisibility(View.INVISIBLE);

		AppCompatActivity activity = (AppCompatActivity) getActivity();
		Station station = activity.getIntent().getParcelableExtra(STATION);

		final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
		if (toolbar != null) {
			activity.setSupportActionBar(toolbar);
			activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

			collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
		}

		if (station != null) {
			populate(station);
		}

		return view;
	}

	public void populate(Station station) {
		this.station = station;
		if (collapsingToolbar != null) {
			collapsingToolbar.setTitle(station.getName());
		}

		text.setVisibility(View.VISIBLE);
		gallery.setVisibility(View.VISIBLE);
		fabButton.setVisibility(View.VISIBLE);

		Glide.with(getActivity()).load(station.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (station != null) {
			outState.putParcelable(STATION, station);
		}
	}
}
