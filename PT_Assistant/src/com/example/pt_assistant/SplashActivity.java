package com.example.pt_assistant;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.preference.PreferenceManager;

public class SplashActivity extends ActionBarActivity {

	// Set the display time, in milliseconds
	private final int SPLASH_DISPLAY_LENGTH = 5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		// Obtain the sharedPreference, default to true if not available
		boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);

		if (isSplashEnabled) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					// Finish the splash activity so it can't be returned to.
					SplashActivity.this.finish();
					// Create an Intent that will start the main activity.
					Intent mainIntent = new Intent(SplashActivity.this,
							MainActivity.class);
					SplashActivity.this.startActivity(mainIntent);
				}
			}, SPLASH_DISPLAY_LENGTH);
		} else {
			// if the splash is not enabled, then finish the activity
			// immediately and go to main.
			finish();
			Intent mainIntent = new Intent(SplashActivity.this,
					MainActivity.class);
			SplashActivity.this.startActivity(mainIntent);
		}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_splash,
					container, false);
			return rootView;
		}
	}
}
