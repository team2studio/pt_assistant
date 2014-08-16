package com.example.pt_assistant;
//This class creates or registers a new patient
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CreatePatientActivity extends ActionBarActivity {
	/**
	 * 
	 */
	Patient newPat;

	// Progress Dialog
	private ProgressDialog pDialog;
	// For remote DB
	JSONParser jsonParser = new JSONParser();
	// For local DB
	PT_SQLiteHelper pt_db = new PT_SQLiteHelper(this);

	// php login script location for remote DB

	// this is the script used for creating a new patient
	private static final String CREATE_PATIENT_URL = "http://199.255.250.71/register_patient.php";

	// JSON element ids from repsonse of php script:sergio
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_patient);
		// Patient pat = new Patient();
		// Get he message from the intent
		// Intent intent = getIntent();

		/******
		 * DO not delete this code yet ******* Sergio pat = (Patient)
		 * intent.getSerializableExtra("Patient");
		 * 
		 * //this is the local DB pt_db.addPatient(pat); TextView textView = new
		 * TextView(this); textView.setTextSize(20);
		 * textView.setText(pat.getName()); textView.setText( "Name: " +
		 * pat.getName() + "\r\n"+ "PID :" + pat.getPatientID() + "\r\n" +
		 * "Injury ID: " + pat.getInjury());
		 * 
		 * setContentView(textView); //this is the remote DB paname =
		 * pat.getName(); pid = pat.getPatientID(); iid = pat.getInjury();
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

	public void Register(View view) {

		EditText eText;
		newPat = new Patient();
		eText = (EditText) findViewById(R.id.editText_name);
		newPat.setName(eText.getText().toString());

		eText = (EditText) findViewById(R.id.editText_pid);
		newPat.setPatientID(Integer.parseInt(eText.getText().toString()));

		eText = (EditText) findViewById(R.id.editText_injurycode);
		newPat.setInjury(Integer.parseInt(eText.getText().toString()));

		eText = (EditText) findViewById(R.id.editText_dob);
		newPat.setDOB(eText.getText().toString());

		eText = (EditText) findViewById(R.id.editText_age);
		newPat.setAge(Integer.parseInt(eText.getText().toString()));

		RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupSex);
		int radioButtonID = rg.getCheckedRadioButtonId();
		View radioButton = rg.findViewById(radioButtonID);
		newPat.setSex(rg.indexOfChild(radioButton));
		//
		// *******This line updates the local DB
		//
		// pt_db.addPatient(newPat);
		//
		// this line updates the remote DB
		//
		new RegisterPatient().execute();

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
	// Async tasks are necessary for retrieving data from the remote DB. 
	// You cannot do this in the main activity
	class RegisterPatient extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(CreatePatientActivity.this);
			pDialog.setMessage("Attempting patient create...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			String patient_name = newPat.getName();
			String patient_id = String.valueOf(newPat.getPatientID());
			String injury_id = String.valueOf(newPat.getInjury());
			String birth_date = newPat.getDOB();
			String age = String.valueOf(newPat.getAge());
			String sex = String.valueOf(newPat.getSex());

			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("patient_id", patient_id));
				params.add(new BasicNameValuePair("patient_name", patient_name));
				params.add(new BasicNameValuePair("b_date", birth_date));
				params.add(new BasicNameValuePair("patient_age", age));
				params.add(new BasicNameValuePair("patient_sex", sex));
				params.add(new BasicNameValuePair("injury_id", injury_id));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(
						CREATE_PATIENT_URL, "POST", params);

				// check your log for json response
				Log.d("Cregeoate patient attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("patient created Successful!", json.toString());

					return json.getString(TAG_MESSAGE);

				} else {
					Log.d("create patient Failure!",
							json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			if (pDialog != null) {
				if (pDialog.isShowing()) {
					pDialog.dismiss();
					// creates the toast
					Context context = getApplicationContext();
					CharSequence text = "Registration Successfull!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
					finish();
				}
			}
			if (file_url != null) {
				Toast.makeText(CreatePatientActivity.this, file_url,
						Toast.LENGTH_LONG).show();
			}

		}

	}

}
