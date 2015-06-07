package it.ialweb.poi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
		recycler.setLayoutManager(new LinearLayoutManager(this));
		recycler.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {

			@Override
			public int getItemCount() {
				return 20;
			}

			@Override
			public void onBindViewHolder(ViewHolder holder, int pos) {
				TextView textView = (TextView) holder.itemView;
				textView.setText("Item " + pos);
			}

			@Override
			public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
				View v = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
				return new ViewHolder(v) {
				};
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
