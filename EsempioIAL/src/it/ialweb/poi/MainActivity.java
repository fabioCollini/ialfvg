package it.ialweb.poi;

import java.util.List;

import org.apache.http.Header;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainActivity extends AppCompatActivity {

	private RequestManager glide;

	private View errorLayout;

	private View loadingLayout;

	private RecyclerView recycler;

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

		glide = Glide.with(this);

		Fragment fragment = getSupportFragmentManager().findFragmentByTag("stationLoader");
		if (fragment == null) {
			loadData();
		}
	}

	private void loadData() {
		loadingLayout.setVisibility(View.VISIBLE);
		errorLayout.setVisibility(View.GONE);
		recycler.setVisibility(View.GONE);
		AsyncHttpClient client = new AsyncHttpClient();
		client.addHeader("X-Parse-Application-Id", "uFkr2W3mPXkLrtAHU0ufa4cm3WgZ0RbTUInOnnL3");
		client.addHeader("X-Parse-REST-API-Key", "uraOQm290ETfGMaDuv5AQivchIjosj3xL6bmPTMx");

		client.get("https://api.parse.com/1/classes/stations/", new AsyncHttpResponseHandler() {

			@Override
			public void onStart() {
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response) {
				StationList responseList = new Gson().fromJson(new String(response), StationList.class);
				populateView(responseList.getList());
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
				errorLayout.setVisibility(View.VISIBLE);
				loadingLayout.setVisibility(View.GONE);
			}

			@Override
			public void onRetry(int retryNo) {
			}
		});
	}

	protected void populateView(final List<Station> list) {
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
		recycler.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
				glide.load(getImageUrl(station)).into(image);
			}

			@Override
			public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
				View v = getLayoutInflater().inflate(R.layout.station_row, parent, false);
				return new ViewHolder(v) {
				};
			}
		});
	}

	private String getImageUrl(Station station) {
		return "https://maps.googleapis.com/maps/api/staticmap?zoom=15&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:S%7C"
				+ station.getLat() + "," + station.getLon();
	}
}
