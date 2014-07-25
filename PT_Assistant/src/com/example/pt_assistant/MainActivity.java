package com.example.pt_assistant;

import org.json.JSONException;

import com.example.pt_assistant.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	public final static String EXTRA_MESSAGE = "com.example.pt_assistant.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		setContentView(R.layout.activity_get_patient);

		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// If your minSdkVersion is 11 or higher, instead use:
		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_actions, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == R.id.action_search) {
			openSearch();
			return true;
		} else if (itemId == R.id.action_settings) {
			openSettings();
			return true;
		} else if (itemId == R.id.action_register_patient) {
			openRegisterPatient();
			return true;

		} else {
			return super.onOptionsItemSelected(item);
		}

	}

	private void openRegisterPatient() {
		// TODO Auto-generated method stub

		// keep this code here for reference on sending a class object to
		// another activity
		Intent intent = new Intent(this, CreatePatientActivity.class);
		intent.putExtra(EXTRA_MESSAGE, "test");
		startActivity(intent);
	}

	private void openSettings() {
		// TODO Auto-generated method stub

	}

	private void openSearch() {
		// TODO Auto-generated method stub

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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, GetPatientActivity.class);
		EditText editText = (EditText) findViewById(R.id.patient_ID);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
	//Test code for reporting
	public void doReportStuff(View view)
	{
	   Report testReport = new Report();
	   testReport.get_patient_trend_data();
	
	}
	public void createPatient(View view) {

		openRegisterPatient();
		// ****** DO not delete this code yet ******* Sergio
		// DO NOT DELETE this code used for reference on sending a class object
		// to another activity
		// EditText eText;
		/*
		 * Intent intent = new Intent(this, CreatePatientActivity.class);
		 * Patient newPat = new Patient(); eText = (EditText)
		 * findViewById(R.id.new_patient_name );
		 * newPat.setName(eText.getText().toString());
		 * 
		 * eText = (EditText) findViewById(R.id.new_pID ); newPat.setPatientID(
		 * Integer.parseInt(eText.getText().toString()) );
		 * 
		 * eText = (EditText) findViewById(R.id.new_IID ); newPat.setInjury(
		 * Integer.parseInt(eText.getText().toString()));
		 * 
		 * //String message = editText.getText().toString();
		 * 
		 * //puts the message in patient and intent.putExtra("Patient", newPat);
		 * startActivity(intent);
		 */
	}
	public void getPatientList(View view){
		// another activity
		Intent intent = new Intent(this, PatientListActivity.class);
		intent.putExtra(EXTRA_MESSAGE, "test");
		startActivity(intent);
		
	}

}
