package it.ialweb.poi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
		mainFragment.setOpenDetailListener(new OpenDetailListener() {
			@Override
			public void openDetail(Station station) {
				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				intent.putExtra(DetailFragment.STATION, station);
				startActivity(intent);
			}
		});
	}
}
