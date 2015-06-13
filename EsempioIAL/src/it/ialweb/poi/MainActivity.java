package it.ialweb.poi;

import it.ialweb.poi.DownloaderFragment.DownloadListener;

import java.util.ArrayList;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	private View errorLayout;

	private View loadingLayout;

	private RecyclerView recycler;

	private DownloaderFragment fragment;

	private StationsAdapter adapter;

	private DownloadListener listener = new DownloadListener() {

		@Override
		public void onLoad(ArrayList<Station> result) {
			populateView(result);
		}

		@Override
		public void onError(int statusCode) {
			errorLayout.setVisibility(View.VISIBLE);
			loadingLayout.setVisibility(View.GONE);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		errorLayout = findViewById(R.id.error_layout);
		loadingLayout = findViewById(R.id.loading_layout);
		recycler = (RecyclerView) findViewById(R.id.recycler);

		findViewById(R.id.retry).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadData();
			}
		});

		fragment = DownloaderFragment.getOrCreateFragment(this, "stationLoader");

		if (fragment.isTaskRunning()) {
			fragment.attach(listener);
		} else {
			ArrayList<Station> savedList = null;
			if (savedInstanceState != null) {
				savedList = savedInstanceState.getParcelableArrayList("stations");
			}

			if (savedList != null) {
				populateView(savedList);
			} else {
				loadData();
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelableArrayList("stations", adapter.getList());
	}

	private void loadData() {
		loadingLayout.setVisibility(View.VISIBLE);
		errorLayout.setVisibility(View.GONE);
		recycler.setVisibility(View.GONE);
		fragment.attachOrLoadData(listener);
	}

	protected void populateView(final ArrayList<Station> list) {
		loadingLayout.setVisibility(View.GONE);
		recycler.setVisibility(View.VISIBLE);
		recycler.setLayoutManager(new LinearLayoutManager(this));
		recycler.addItemDecoration(new ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
				super.getItemOffsets(outRect, view, parent, state);
				outRect.left = 10;
				outRect.right = 10;
				outRect.bottom = 5;
				outRect.top = 5;
			}
		});
		adapter = new StationsAdapter(this, list);
		recycler.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (!isChangingConfigurations()) {
			fragment.stopTask();
		}
	}
}
