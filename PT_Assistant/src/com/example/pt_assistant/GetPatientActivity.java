package com.example.pt_assistant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pt_assistant.R;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



public class GetPatientActivity extends ActionBarActivity {
	// for local DB
	PT_SQLiteHelper pt_db = new PT_SQLiteHelper(this);
	// For remote DB
	JSONParser jsonParser = new JSONParser();
	// Progress Dialog
	private ProgressDialog pDialog;
	Patient existPat;

	//url scripts for getting and updating a patient
	private static final String UPDATE_PATIENT_URL = "http://199.255.250.71/update_patient.php";
	private static final String GET_PATIENT_URL = "http://199.255.250.71/get_patient.php";
	private String GET_PATIENT = "get patient";
	private String UPDATE_PATIENT = "update patient";

	public void fill_patient_fields(Patient pat) {
		EditText eText;
		eText = (EditText) findViewById(R.id.editname);
		eText.setText(pat.getName());

		eText = (EditText) findViewById(R.id.editpid);
		eText.setText(Integer.toString(pat.getPatientID()));

		eText = (EditText) findViewById(R.id.editDOB);
		eText.setText(pat.getDOB());

		eText = (EditText) findViewById(R.id.editage);
		eText.setText(Integer.toString(pat.getAge()));

		eText = (EditText) findViewById(R.id.editinId);
		eText.setText(Integer.toString(pat.getInjury()));

		RadioButton but;// = (RadioButton)findViewById(R.id.radioFemale);
		if (pat.getSex() == 0) {
			but = (RadioButton) findViewById(R.id.radiomale);
			but.setChecked(true);
		} else {
			but = (RadioButton) findViewById(R.id.radiofem);
			but.setChecked(true);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_get_patient);

		// Get the message from the intent
		Intent intent = getIntent();
		String patid = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

		// local DB
		// pt = pt_db.getPatient(Integer.parseInt(message));
		// fill_patient_fields(pt);
		// remote DB
		new doPatient(GET_PATIENT).execute(patid);

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
		existPat = new Patient();
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

		// pt_db.updatePatient(existPat);
		new doPatient(UPDATE_PATIENT).execute();

	}

	public void gotoNotes(View view) {
		EditText eText;
		existPat = new Patient();

		eText = (EditText) findViewById(R.id.editpid);
		existPat.setPatientID(Integer.parseInt(eText.getText().toString()));

		// Serialize, start next activity and send intent
		// Patient p = new Patient();
		Intent intent = new Intent(this, CreateNotesActivity.class);
		intent.putExtra("PatientObject", existPat);
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
			View rootView = inflater.inflate(R.layout.fragment_get_patient,
					container, false);
			return rootView;
		}
	}

	// JSON element ids from repsonse of php script:sergio
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
    // Async tasks are necessary for retrieving data from the remote DB. 
	// You cannot do this in the main activity
	class doPatient extends AsyncTask<String, String, String> {
		private String doWhat;

		public doPatient(String do_what) {
			super();
			doWhat = do_what;
			// do stuff
		}

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		boolean failure = false;

		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			pDialog = new ProgressDialog(GetPatientActivity.this);
			pDialog.setMessage("Attempting  " + doWhat + "...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			String result;
			// TODO Auto-generated method stub
			// Check for success tag
			// get the patient info from the DB server
			if (doWhat.equals(GET_PATIENT)) {
				result = get_patient_json(args[0]);
			} else if (doWhat.equals(UPDATE_PATIENT)) {
				result = update_patient_json();
			} else
				result = null;
			if (result != null)
				return result;
			else
				return null;

		}

		// ///////////
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {

			pDialog.dismiss();
			if (failure == false) {
				// fill in the GUI
				fill_patient_fields(existPat);
				Context context = getApplicationContext();
				CharSequence text = doWhat + " Successfull!";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
			if (file_url != null) {
				Toast.makeText(GetPatientActivity.this, file_url,
						Toast.LENGTH_LONG).show();
			}

		}

		private String get_patient_json(String patient_id) {
			int success;
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("patient_id", patient_id));
				// params.add(new BasicNameValuePair("patient_name",
				// patient_name));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(GET_PATIENT_URL,
						"GET", params);

				// check your log for json response
				Log.d("patient get attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					existPat = new Patient();
					Log.d("patient get Successful!", json.toString());
					JSONArray patientlist = new JSONArray();
					patientlist = json.getJSONArray("thispatient");
					for (int i = 0; i < patientlist.length(); i++) {
						JSONObject c = patientlist.getJSONObject(i);
						existPat.setPatientID(Integer.parseInt(c
								.getString("p_id")));
						existPat.setName(c.getString("name"));
						existPat.setDOB(c.getString("dob"));
						existPat.setAge(Integer.parseInt(c.getString("age")));
						existPat.setSex(Integer.parseInt(c.getString("sex")));
						existPat.setInjury(Integer.parseInt(c
								.getString("injury_code")));
					}

					Log.d("done", "finished getting");

					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("get patient Failure!", json.getString(TAG_MESSAGE));
					failure = true;
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;

		}

		private String update_patient_json() {
			int success;
			if (existPat != null) {
				try {
					// Building Parameters
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("patient_id", Integer
							.toString(existPat.getPatientID())));
					params.add(new BasicNameValuePair("patient_name", existPat
							.getName()));
					params.add(new BasicNameValuePair("b_date", existPat
							.getDOB()));
					params.add(new BasicNameValuePair("patient_age", Integer
							.toString(existPat.getAge())));
					params.add(new BasicNameValuePair("patient_sex", Integer
							.toString(existPat.getSex())));
					params.add(new BasicNameValuePair("injury_id", Integer
							.toString(existPat.getInjury())));

					Log.d("request!", "starting");
					// getting product details by making HTTP request
					JSONObject json = jsonParser.makeHttpRequest(
							UPDATE_PATIENT_URL, "POST", params);

					// check your log for json response
					Log.d("update patient attempt", json.toString());

					// json success tag
					success = json.getInt(TAG_SUCCESS);

					if (success == 1) {

						Log.d("patient update Successful!", json.toString());
						String msg = json.getString(TAG_MESSAGE);
						return msg;
					} else {
						Log.d("patient update Failure!",
								json.getString(TAG_MESSAGE));
						failure = true;
						return json.getString(TAG_MESSAGE);

					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;

		}

	}

}
