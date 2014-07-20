package com.example.pt_assistant;

import com.example.pt_assistant.GetPatientActivity.doPatient;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Build;

public class AssessmentNotesActivity extends ActionBarActivity implements OnItemSelectedListener{
	Patient_Notes pn;
	Patient p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assessment_notes);

		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinnerInjury);
 
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
 
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("LOWER BACK INJURY LUMBAR");
        categories.add("ROTATOR CUFF TENDINITIS");
 
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
		
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

		//receive serialized patient and notes objects from previous activity
		p = (Patient)getIntent().getSerializableExtra("PatientObject");
		pn = (Patient_Notes)getIntent().getSerializableExtra("PatientNotesObject");
				
		EditText eText;
		//notes = new Patient_Notes();
		eText = (EditText) findViewById(R.id.editAssessment);
		pn.setPatient_diagnosis(eText.getText().toString());
		
		Spinner spin;
		spin = (Spinner) findViewById(R.id.spinnerInjury);
		pn.setInjury(spin.getItemAtPosition(0).toString());//probably not right need to resolve
		//need the actual position value from below in onItemSelected

		//Serialize, start next activity and send intent
		Intent intent = new Intent(this, PlanNotesActivity.class);
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
					R.layout.fragment_assessment_notes, container, false);
			return rootView;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> pn, View view, int position,
			long id) {
        String item = pn.getItemAtPosition(position).toString();
        //it seems like we want to use this string value to set to the database but
        //it doesn't let us set it from here using the adapterview
 
        // Showing selected spinner item
        //Toast.makeText(pn.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
