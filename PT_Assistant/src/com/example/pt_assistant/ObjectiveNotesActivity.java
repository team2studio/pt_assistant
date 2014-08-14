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
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.os.Build;

public class ObjectiveNotesActivity extends ActionBarActivity {
	Patient_Notes pn;
	Patient p;
	private SeekBar seekROM;
	private TextView textViewROMCtr;

	private SeekBar seekStren;
	private TextView textViewStrenCtr;

	private SeekBar seekJoint;
	private TextView textViewJointCtr;

	private SeekBar seekPain;
	private TextView textViewPainCtr;

	private SeekBar seekPalp;
	private TextView textViewPalp;

	private SeekBar seekSpec;
	private TextView textViewSpecCtr;

	private boolean obj_get_notes = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_objective_notes);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		if (savedInstanceState == null) {
			Intent intent = getIntent();
			String getnotes = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
			obj_get_notes = false;
			if (getnotes != null && getnotes.equals("getnotes")) {
				obj_get_notes = true;
				pn = (Patient_Notes) getIntent().getSerializableExtra("PatientNotesObject");
				SeekBar seek;
				seek = (SeekBar) findViewById(R.id.seekBarROM);
				seek.setProgress(pn.getRangeOfMotion());

				seek = (SeekBar) findViewById(R.id.seekBarStrength);
				seek.setProgress(pn.getStrength());

				seek = (SeekBar) findViewById(R.id.seekBarJointMob);
				seek.setProgress(pn.getJointMobilization());

				seek = (SeekBar) findViewById(R.id.seekBarPain);
				seek.setProgress(pn.getPain());

				seek = (SeekBar) findViewById(R.id.seekBarPalpation);
				seek.setProgress(pn.getPalpation());

				seek = (SeekBar) findViewById(R.id.seekBarSpecialTest);
				seek.setProgress(pn.getSpecialTest());
			}
		}

		// /////////////////Seek ROM 
		seekROM = (SeekBar) findViewById(R.id.seekBarROM);
		textViewROMCtr = (TextView) findViewById(R.id.textViewRom);
		// Initialize the textview with '0'
		// textViewCtr.setText(seekBarROM.getProgress() + "/" +
		// seekBarROM.getMax());
		seekROM.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// int progress = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textViewROMCtr.setText("ROM: " + progresValue);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Display the value
				// textViewCtr.setText(progress + "/" + seekBar.getMax());
			}
		});
		// ///////////////Seek Strength///////////////////

		seekStren = (SeekBar) findViewById(R.id.seekBarStrength);
		textViewStrenCtr = (TextView) findViewById(R.id.textViewStrength);
		// Initialize the textview with '0'
		// textViewCtr.setText(seekBarROM.getProgress() + "/" +
		// seekBarROM.getMax());
		seekStren.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// int progress = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textViewStrenCtr.setText("Strength: " + progresValue);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Display the value
				// textViewCtr.setText(progress + "/" + seekBar.getMax());
			}
		});
		// /////////////////////Seek Joint///////////////////////////////////////////
	
		seekJoint = (SeekBar) findViewById(R.id.seekBarJointMob);
		textViewJointCtr = (TextView) findViewById(R.id.textViewJointMob);
		// Initialize the textview with '0'
		// textViewCtr.setText(seekBarROM.getProgress() + "/" +
		// seekBarROM.getMax());
		seekJoint.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// int progress = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textViewJointCtr.setText("Joint Mobs: " + progresValue);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Display the value
				// textViewCtr.setText(progress + "/" + seekBar.getMax());
			}
		});
		// ////////////////////////Seek Pain/////////////////////////////////////
	
		
		
		seekPain = (SeekBar) findViewById(R.id.seekBarPain);
		textViewPainCtr = (TextView) findViewById(R.id.textViewPain);
		// Initialize the textview with '0'
		// textViewCtr.setText(seekBarROM.getProgress() + "/" +
		// seekBarROM.getMax());
		seekPain.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// int progress = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textViewPainCtr.setText("Pain: " + progresValue);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Display the value
				// textViewCtr.setText(progress + "/" + seekBar.getMax());
			}
		});
		// ///////////////////////////////Seek Palp////////////////////////////////////
		
		seekPalp = (SeekBar) findViewById(R.id.seekBarPalpation);
		textViewPalp = (TextView) findViewById(R.id.textViewPalpation);
		// Initialize the textview with '0'
		// textViewCtr.setText(seekBarROM.getProgress() + "/" +
		// seekBarROM.getMax());
		seekPalp.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// int progress = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textViewPalp.setText("Palpation: " + progresValue);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Display the value
				// textViewCtr.setText(progress + "/" + seekBar.getMax());
			}
		});
		// //////////////////////////Seek Spec/////////////////////////////////////
		
		seekSpec = (SeekBar) findViewById(R.id.seekBarSpecialTest);
		textViewSpecCtr = (TextView) findViewById(R.id.textViewSpecialTest);
		// Initialize the textview with '0'
		// textViewCtr.setText(seekBarROM.getProgress() + "/" +
		// seekBarROM.getMax());
		seekSpec.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// int progress = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textViewSpecCtr.setText("Spec Test:" + progresValue);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Display the value
				// textViewCtr.setText(progress + "/" + seekBar.getMax());
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

		// receive serialized patient and notes objects from previous activity
		p = (Patient) getIntent().getSerializableExtra("PatientObject");

		if (obj_get_notes == false)
		{
		    pn = (Patient_Notes) getIntent().getSerializableExtra("PatientNotesObject");
		}
		
		// EditText eText;
		// notes = new Patient_Notes();
		// eText = (EditText) findViewById(R.id.editInjuryLookup);
		// pn.getInjury();

		SeekBar seek;
		seek = (SeekBar) findViewById(R.id.seekBarROM);
		pn.setRangeOfMotion(seek.getProgress());

		seek = (SeekBar) findViewById(R.id.seekBarStrength);
		pn.setStrength(seek.getProgress());

		seek = (SeekBar) findViewById(R.id.seekBarJointMob);
		pn.setJointMobilization(seek.getProgress());

		seek = (SeekBar) findViewById(R.id.seekBarPain);
		pn.setPain(seek.getProgress());

		seek = (SeekBar) findViewById(R.id.seekBarPalpation);
		pn.setPalpation(seek.getProgress());

		seek = (SeekBar) findViewById(R.id.seekBarSpecialTest);
		pn.setSpecialTest(seek.getProgress());

		// Serialize, start next activity and send intent
		Intent intent = new Intent(this, AssessmentNotesActivity.class);
		if (obj_get_notes) {
			intent.putExtra(MainActivity.EXTRA_MESSAGE, "getnotes");
		}
		intent.putExtra("PatientObject", p);
		intent.putExtra("PatientNotesObject", pn);
		startActivity(intent);

	}

	public void lookupInjury(View view) {
		// start next activity and send intent
		Intent intent = new Intent(this, InjuryLookupActivity.class);
		startActivity(intent);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		// @Override
		// public View onCreateView(LayoutInflater inflater, ViewGroup
		// container,
		// Bundle savedInstanceState) {
		// View rootView = inflater.inflate(R.layout.fragment_objective_notes,
		// container, false);
		// return rootView;
		// }
	}

}
