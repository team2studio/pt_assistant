package com.example.pt_assistant;

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
import android.widget.RadioButton;
import android.os.Build;

public class TreatmentPlanActivity extends ActionBarActivity {
	Patient_Notes notes;
	Patient p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_treatment_plan);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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
	public void fill_treatmentPlan_fields(TreatmentPlan tp) {
		//receive serialized patient and notes objects from previous activity
		p = (Patient)getIntent().getSerializableExtra("PatientObject");
		notes = (Patient_Notes)getIntent().getSerializableExtra("PatientNotesObject");
		
		EditText eText;
		eText = (EditText) findViewById(R.id.injuryName);
		eText.setText(notes.getInjury()); //
		
		eText = (EditText) findViewById(R.id.phaseName);
		eText.setText(tp.getTreatmentType());

		/*The next few aren't clear on how we want to get them from the base class yet so
		I just left the GUI tie ins and then made suggestions for getters in the base with code
		we would need for that case commented out. I know you(Jamel) have a plan with the array
		lists and your condition checks so I'll leave it up to you how to get the rest out. */
		eText = (EditText) findViewById(R.id.instructIntReps);
		//eText.setText(Integer.toString(tp.getExerciseReps()));

		eText = (EditText) findViewById(R.id.instructIntMin);
		//eText.setText(Integer.toString(tp.getTimeMin()));

		eText = (EditText) findViewById(R.id.instructIntMax);
		//eText.setText(Integer.toString(tp.getTimeMax())); 

		eText = (EditText) findViewById(R.id.exerciseOne);
		//eText.setText(tp.getExerciseOne());
		
		eText = (EditText) findViewById(R.id.exerciseTwo);
		//eText.setText(tp.getExerciseTwo());
		
		eText = (EditText) findViewById(R.id.exerciseThree);
		//eText.setText(tp.getExerciseThree());
		
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
