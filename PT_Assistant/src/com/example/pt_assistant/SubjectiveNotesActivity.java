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
import android.widget.Spinner;
import android.os.Build;

public class SubjectiveNotesActivity extends ActionBarActivity {
	Patient_Notes pn;
	Patient p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subjective_notes);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.subjective_notes, menu);
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

	public void setSubjective(View view) {

		//receive serialized patient and notes objects from previous activity
		p = (Patient)getIntent().getSerializableExtra("PatientObject");
		pn = (Patient_Notes)getIntent().getSerializableExtra("PatientNotesObject");
				
		EditText eText;
		//notes = new Patient_Notes();
		eText = (EditText) findViewById(R.id.editPastDiag);
		pn.setPastDiagnosis(eText.getText().toString());
		
		eText = (EditText) findViewById(R.id.editMedications);
		pn.setMedications(eText.getText().toString());
		
		eText = (EditText) findViewById(R.id.editOther);
		pn.setOther_PatientHistory(eText.getText().toString());
		
		eText = (EditText) findViewById(R.id.editGoals);
		pn.setGoals(eText.getText().toString());
		
		eText = (EditText) findViewById(R.id.editReason);
		pn.setReasons(eText.getText().toString());

		//Serialize, start next activity and send intent
		Intent intent = new Intent(this, ObjectiveNotesActivity.class);
		intent.putExtra("PatientObject", p);
		intent.putExtra("PatientNotesObject", pn);
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
			View rootView = inflater.inflate(
					R.layout.fragment_subjective_notes, container, false);
			return rootView;
		}
	}

}
