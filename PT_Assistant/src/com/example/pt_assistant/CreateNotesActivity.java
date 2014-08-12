package com.example.pt_assistant;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.os.Build;

public class CreateNotesActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_notes);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_notes, menu);
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

	public void startNotes(View view) {
		//receive serialized patient and notes objects from previous activity
		Patient p = (Patient)getIntent().getSerializableExtra("PatientObject");
		
		//Serialize, start next activity and send intent
		Patient_Notes pn = new Patient_Notes();
		Intent intent = new Intent(this, SubjectiveNotesActivity.class);
		intent.putExtra("PatientObject", p);
		intent.putExtra("PatientNotesObject", pn);
		startActivity(intent);
		
	}	
	public void getNotes(View view) {
		
		
		//receive serialized patient and notes objects from previous activity
		Patient p = (Patient)getIntent().getSerializableExtra("PatientObject");
		
		//Serialize, start next activity and send intent
		
		Intent intent = new Intent(this, SubjectiveNotesActivity.class);
		intent.putExtra(MainActivity.EXTRA_MESSAGE, "getnotes");
		intent.putExtra("PatientObject", p);
		startActivity(intent);
		
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
			View rootView = inflater.inflate(R.layout.fragment_create_notes,
					container, false);
			return rootView;
		}
	}

}
