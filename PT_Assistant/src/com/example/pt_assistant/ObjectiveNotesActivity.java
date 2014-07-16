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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.os.Build;

public class ObjectiveNotesActivity extends ActionBarActivity {
	Patient_Notes notes;
	 private SeekBar seekBar;
	 private TextView textViewCtr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_objective_notes);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
			}
			  seekBar = (SeekBar) findViewById(R.id.seekBarROM);
			  textViewCtr = (TextView) findViewById(R.id.textViewROMCount);
			  // Initialize the textview with '0'
			  textViewCtr.setText(seekBar.getProgress() + "/" + seekBar.getMax());
			  seekBar.setOnSeekBarChangeListener(

			  new OnSeekBarChangeListener() {
			    int progress = 0;
			        @Override
			      public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
			        progress = progresValue;
			      }

			      @Override
			      public void onStartTrackingTouch(SeekBar seekBar) {
			       
			      }

			      @Override
			      public void onStopTrackingTouch(SeekBar seekBar) {
			        // Display the value
			        textViewCtr.setText(progress + "/" + seekBar.getMax());
			      }
			  }); 
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.objective_notes, menu);
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

	public void setObjective(View view) {

		EditText eText;
		notes = new Patient_Notes();
		eText = (EditText) findViewById(R.id.editInjuryLookup);
		notes.getInjury();
		/*not sure what we want to do here. will figure out when we do injuries
		We need to get the list of injuries here, we don't want to actually set it yet until Assessment*/
		
		SeekBar seek;
		seek = (SeekBar) findViewById(R.id.seekBarROM);
		notes.setRangeOfMotion(seek.getProgress());
		
		seek = (SeekBar) findViewById(R.id.seekBarStrength);
		notes.setStrength(seek.getProgress());
		
		seek = (SeekBar) findViewById(R.id.seekBarJointMob);
		notes.setJointMobilization(seek.getProgress());
		
		seek = (SeekBar) findViewById(R.id.seekBarPain);
		notes.setPain(seek.getProgress());

		seek = (SeekBar) findViewById(R.id.seekBarPalpation);
		notes.setPalpation(seek.getProgress());
		
		seek = (SeekBar) findViewById(R.id.seekBarSpecialTest);
		notes.setSpecialTest(seek.getProgress());
		
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
			View rootView = inflater.inflate(R.layout.fragment_objective_notes,
					container, false);
			return rootView;
		}
	}

}
