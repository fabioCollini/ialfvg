package it.ialweb.poi;

import java.util.ArrayList;

import org.apache.http.Header;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class DownloaderFragment extends Fragment {

	private DownloadListener listener;

	private AsyncHttpClient client;

	private boolean isRunning;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	public DownloaderFragment() {
		System.out.print(123);
	}

	public static DownloaderFragment getOrCreateFragment(FragmentManager fragmentManager, String tag) {
		DownloaderFragment fragment = (DownloaderFragment) fragmentManager.findFragmentByTag(tag);
		if (fragment == null) {
			fragment = new DownloaderFragment();
			fragmentManager.beginTransaction().add(fragment, tag).commit();
		}
		return fragment;
	}

	public interface DownloadListener {
		void onLoad(ArrayList<Station> result);

		void onError(int statusCode);
	}

	public void attachOrLoadData(DownloadListener listener) {
		this.listener = listener;
		client = new AsyncHttpClient();
		client.addHeader("X-Parse-Application-Id", "uFkr2W3mPXkLrtAHU0ufa4cm3WgZ0RbTUInOnnL3");
		client.addHeader("X-Parse-REST-API-Key", "uraOQm290ETfGMaDuv5AQivchIjosj3xL6bmPTMx");

		isRunning = true;
		client.get("https://api.parse.com/1/classes/stations/", new AsyncHttpResponseHandler() {

			@Override
			public void onStart() {
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response) {
				StationList responseList = new Gson().fromJson(new String(response), StationList.class);
				DownloaderFragment.this.listener.onLoad(responseList.getList());
				isRunning = false;
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
				DownloaderFragment.this.listener.onError(statusCode);
				isRunning = false;
			}

			@Override
			public void onRetry(int retryNo) {
			}
		});
	}

	public boolean isTaskRunning() {
		return isRunning;
	}

	public void attach(DownloadListener listener) {
		this.listener = listener;
	}

	public void stopTask() {
		client.cancelAllRequests(true);
	}
}
