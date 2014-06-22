package com.example.pt_assistant;

import com.example.pt_assistant.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class GetPatientActivity extends ActionBarActivity {
	PT_SQLiteHelper pt_db = new PT_SQLiteHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String Sex;
		setContentView(R.layout.fragment_get_patient);
		Patient pt = new Patient();
		// Get the message from the intent
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

		// pt.get_patient(message);

		pt = pt_db.getPatient(Integer.parseInt(message));

		EditText eText;
		eText = (EditText) findViewById(R.id.editname);
		eText.setText(pt.getName());

		eText = (EditText) findViewById(R.id.editpid);
		eText.setText(Integer.toString(pt.getPatientID()));

		eText = (EditText) findViewById(R.id.editDOB);
		eText.setText(pt.getDOB());

		eText = (EditText) findViewById(R.id.editage);
		eText.setText(Integer.toString(pt.getAge()));

		eText = (EditText) findViewById(R.id.editinId);
		eText.setText(Integer.toString(pt.getInjury()));

		RadioButton but;// = (RadioButton)findViewById(R.id.radioFemale);
		if (pt.getSex() == 0) {
			but = (RadioButton) findViewById(R.id.radiomale);
			but.setChecked(true);
		} else {
			but = (RadioButton) findViewById(R.id.radiofem);
			but.setChecked(true);
		}

		// Create the text view

		/*
		 * textView.setTextSize(40); textView.setText(pt.getName());
		 * textView.setText( "Name: " + pt.getName() + "\r\n"+ "ID :" +
		 * pt.getPatientID()+ "\r\n" + "DOB:" + pt.DOB + "\r\n" + "Age:" +
		 * pt.age + "\r\n" + "Sex:" + Sex + "\r\n" + "Injury code: " +
		 * pt.getInjury());
		 * 
		 * 
		 * setContentView(textView);
		 */
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	//
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.display_message, menu);
	// return true;
	// }

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

	public void updatePatient(View view) {

		EditText eText;
		Patient existPat = new Patient();
		eText = (EditText) findViewById(R.id.editname);
		existPat.setName(eText.getText().toString());

		eText = (EditText) findViewById(R.id.editpid);
		existPat.setPatientID(Integer.parseInt(eText.getText().toString()));

		eText = (EditText) findViewById(R.id.editinId);
		existPat.setInjury(Integer.parseInt(eText.getText().toString()));

		eText = (EditText) findViewById(R.id.editDOB);
		existPat.setDOB(eText.getText().toString());

		eText = (EditText) findViewById(R.id.editage);
		existPat.setAge(Integer.parseInt(eText.getText().toString()));

		RadioGroup rg = (RadioGroup) findViewById(R.id.radGrpSex2);
		int radioButtonID = rg.getCheckedRadioButtonId();
		View radioButton = rg.findViewById(radioButtonID);
		existPat.setSex(rg.indexOfChild(radioButton));

		pt_db.updatePatient(existPat);

		Context context = getApplicationContext();
		CharSequence text = "Update Successfull!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		// go back to main activity
		finish();
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
			View rootView = inflater.inflate(R.layout.fragment_get_patient,
					container, false);
			return rootView;
		}
	}

}
