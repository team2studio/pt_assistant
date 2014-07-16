package com.example.pt_assistant;

import com.example.pt_assistant.GetPatientActivity.doPatient;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.os.Build;

public class AssessmentNotesActivity extends ActionBarActivity {
	Patient_Notes notes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assessment_notes);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.assessment_notes, menu);
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

	public void setAssessment(View view) {

		EditText eText;
		notes = new Patient_Notes();
		eText = (EditText) findViewById(R.id.editAssessment);
		notes.setPatient_diagnosis(eText.getText().toString());
		
		//Spinner spin;
		//spin = (Spinner) findViewById(R.id.spinnerInjury);
		//notes.setInjury(spin).getText().toString());
		/*will implement a spinner dropdown menu for Injury once we implement the Injury data
		 May need to change setInjury method to spinner type rather than string */

		//need database update here
		// pt_db.updatePatient(existPat);
		//new doPatient(UPDATE_PATIENT).execute();
		
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
					R.layout.fragment_assessment_notes, container, false);
			return rootView;
		}
	}

}
