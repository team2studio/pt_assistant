package com.example.pt_assistant;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class TreatmentPlanActivity extends ActionBarActivity {
	Patient_Notes notes;
	Patient p;
	TreatmentPlan tp;
	private static final String TAG = "TreatmentPlanActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_treatment_plan);
		setContentView(R.layout.fragment_treatment_plan);

		/*
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		*/
		
		//receive serialized patient and notes objects from previous activity
		//p = (Patient)getIntent().getSerializableExtra("PatientObject");
		//notes = (Patient_Notes)getIntent().getSerializableExtra("PatientNotesObject");
				
		fill_treatmentPlan_fields();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.treatment_plan, menu);
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

	/*Added by Ryan
	Method to fill all the fields in the treatment plan to be triggered
	from Jamel's treatment plan logic not GUI */
	public void fill_treatmentPlan_fields() {
		int i = 0;
		String exercise1 = null;
		String exercise2 = null;
		String exercise3 = null;
		
		//create a new treatment plan object
		tp = new TreatmentPlan();
		
		ArrayList<String> exercises = new ArrayList<String>();
		
		//receive serialized patient and notes objects from previous activity
		p = (Patient)getIntent().getSerializableExtra("PatientObject");
		notes = (Patient_Notes)getIntent().getSerializableExtra("PatientNotesObject");
		
		//ADDED IN BY JAMEL...BUT WE NEED TO SET THE INJURY IN THE GUI TO MAKE THIS WORK
		//notes.setInjury("ROTATOR CUFF TENDINITIS");
		
		//generate treatment plan based off Patient's SOAP data
		i = tp.generateTreatmentPlan(notes);
		Log.i(TAG, "We selected treatment plan id: "+i);
		
		//retrieve arraylist of exercises from treatment plan description
		exercises = tp.getTreatmentDescription();
		
		if (exercises != null){
			Log.i(TAG, "Exercises were loaded properly!");
		}
		else{
			Log.i(TAG, "Exercises arraylist is null!");
		}
		
		
		TextView eText;
		eText = (TextView) findViewById(R.id.injuryName);
		
		if (eText != null){
			Log.i(TAG, "Text view for injury name is not null");
		}
		else{
			Log.i(TAG, "Text view for injury name is null");
		}
		
		eText.setText(notes.getInjury()); //
		
		eText = (TextView) findViewById(R.id.phaseName);
		eText.setText(tp.getTreatmentType());

		/*The next few aren't clear on how we want to get them from the base class yet so
		I just left the GUI tie ins and then made suggestions for getters in the base with code
		we would need for that case commented out. I know you(Jamel) have a plan with the array
		lists and your condition checks so I'll leave it up to you how to get the rest out. */
		eText = (TextView) findViewById(R.id.instructIntReps);
		eText.setText(Integer.toString(tp.getExerciseReps()));

		eText = (TextView) findViewById(R.id.instructIntMin);
		eText.setText(Integer.toString(tp.getExerciseMinTime()));

		eText = (TextView) findViewById(R.id.instructIntMax);
		eText.setText(Integer.toString(tp.getExerciseMaxTime())); 
		
		//loop through arraylist exercises
		for (int j = 0; j < exercises.size(); j++){
			if (j == 0){
				exercise1 = exercises.get(j);
			}
				
			if (j == 1){
				exercise2 = exercises.get(j);	
			}
				
			if (j == 2){
				exercise3 = exercises.get(j);	
			}
		}
		
		eText = (TextView) findViewById(R.id.exerciseOne);
		eText.setText(exercise1);
		
		eText = (TextView) findViewById(R.id.exerciseTwo);
		eText.setText(exercise2);
		
		eText = (TextView) findViewById(R.id.exerciseThree);
		eText.setText(exercise3);
		
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
			View rootView = inflater.inflate(R.layout.fragment_treatment_plan,
					container, false);
			return rootView;
		}
	}

}
