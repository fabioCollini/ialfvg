package it.ialweb.poi;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
		mainFragment.setOpenDetailListener(new OpenDetailListener() {
			@Override
			public void openDetail(Station station, View image) {
				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				intent.putExtra(DetailFragment.STATION, station);

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					openUsingAnimation(image, intent);
				} else {
					startActivity(intent);
				}
			}
		});
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void openUsingAnimation(View image, Intent intent) {
		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, image,
				"map");
		startActivity(intent, options.toBundle());
	}
}
