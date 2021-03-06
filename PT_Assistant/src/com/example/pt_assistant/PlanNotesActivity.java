package com.example.pt_assistant;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pt_assistant.GetPatientActivity.doPatient;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.os.Build;

public class PlanNotesActivity extends ActionBarActivity {
	Patient_Notes pn;
	Patient p;
	JSONParser jsonParser = new JSONParser();
	boolean plan_get_notes = false;
	// Progress Dialog
	private ProgressDialog pDialog;
	private static final String CREATE_PATIENT_NOTES_URL = "http://199.255.250.71/patient_create_notes.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_notes);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		if (savedInstanceState == null) {
			Intent intent = getIntent();
			String getnotes = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
			plan_get_notes = false;
			if (getnotes != null && getnotes.equals("getnotes")) {
				plan_get_notes = true;
				pn = (Patient_Notes) getIntent().getSerializableExtra(
						"PatientNotesObject");
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plan_notes, menu);
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

	public void setPlanNotes(View view) {
		// receive serialized patient and notes objects from previous activity
		p = (Patient) getIntent().getSerializableExtra("PatientObject");

		if (plan_get_notes == false) {
			pn = (Patient_Notes) getIntent().getSerializableExtra(
					"PatientNotesObject");
		}

		EditText eText;
		// pn = new Patient_Notes();
		eText = (EditText) findViewById(R.id.editPlanNotes);
		pn.setAdditionalPlanNotes(eText.getText().toString());

		// Serialize, start next activity and send intent
		Intent intent = new Intent(this, PlanNotesActivity.class);

		if (plan_get_notes) {
			intent.putExtra(MainActivity.EXTRA_MESSAGE, "getnotes");
		}
		intent.putExtra("PatientObject", p);
		intent.putExtra("PatientNotesObject", pn);
		startActivity(intent);

		new EnterSessionNotes().execute();

	}

	public void generateTreatmentPlan(View view) {
		// receive serialized patient and notes objects from previous activity
		p = (Patient) getIntent().getSerializableExtra("PatientObject");
		pn = (Patient_Notes) getIntent().getSerializableExtra(
				"PatientNotesObject");

		// Serialize, start next activity and send intent
		Intent intent = new Intent(this, TreatmentPlanActivity.class);
		intent.putExtra("PatientObject", p);
		intent.putExtra("PatientNotesObject", pn);
		startActivity(intent);

	}

	public void backHome(View view) {
		// receive serialized patient and notes objects from previous activity
		p = (Patient) getIntent().getSerializableExtra("PatientObject");
		pn = (Patient_Notes) getIntent().getSerializableExtra(
				"PatientNotesObject");

		// Serialize, start next activity and send intent
		Intent intent = new Intent(this, MainActivity.class);
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
			View rootView = inflater.inflate(R.layout.fragment_plan_notes,
					container, false);
			return rootView;
		}
	}
	// Async tasks are necessary for retrieving data from the remote DB. 
	// You cannot do this in the  activity window
	class EnterSessionNotes extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PlanNotesActivity.this);
			pDialog.setMessage("Attempting to add patient notes...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// Auto-generated method stub

			// Check for success tag
			int success;
			String patient_id = String.valueOf(p.getPatientID());
			String past_diagnosis = pn.getPastDiagnosis();
			String other = pn.getOther_PatientHistory();
			String medications = pn.getMedications();
			String goals = pn.getGoals();
			String reasons = pn.getReasons();
			String range_of_motion = String.valueOf(pn.getRangeOfMotion());
			String strength = String.valueOf(pn.getStrength());
			String joint_mobilization = String.valueOf(pn
					.getJointMobilization());
			String pain = String.valueOf(pn.getPain());
			String palpation = String.valueOf(pn.getPalpation());
			String special_test = String.valueOf(pn.getSpecialTest());
			String injury_name = pn.getInjury();
			String diagnosis = pn.getPatient_diagnosis();
			String additional_plan_notes = pn.getAdditionalPlanNotes();

			try {

				// build parameters sent in the http request
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("patient_id", patient_id));
				params.add(new BasicNameValuePair("past_diagnosis",
						past_diagnosis));
				params.add(new BasicNameValuePair("other", other));
				params.add(new BasicNameValuePair("medications", medications));
				params.add(new BasicNameValuePair("goals", goals));
				params.add(new BasicNameValuePair("reasons", reasons));
				params.add(new BasicNameValuePair("range_of_motion",
						range_of_motion));
				params.add(new BasicNameValuePair("strength", strength));
				params.add(new BasicNameValuePair("joint_mobilization",
						joint_mobilization));
				params.add(new BasicNameValuePair("pain", pain));
				params.add(new BasicNameValuePair("palpation", palpation));
				params.add(new BasicNameValuePair("special_test", special_test));
				params.add(new BasicNameValuePair("injury_name", injury_name));
				params.add(new BasicNameValuePair("diagnosis", diagnosis));
				params.add(new BasicNameValuePair("additional_plan_notes",
						additional_plan_notes));

				// make http request
				JSONObject json = jsonParser.makeHttpRequest(
						CREATE_PATIENT_NOTES_URL, "POST", params);

				// check your log for json response
				Log.d("Creating patient notes", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Patient notes created successfully!",
							json.toString());

					return json.getString(TAG_MESSAGE);

				} else {
					Log.d("Creating patient notes failed!",
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
					CharSequence text = "Successfully added Patient Notes!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
					finish();
				}
			}
			if (file_url != null) {
				Toast.makeText(PlanNotesActivity.this, file_url,
						Toast.LENGTH_LONG).show();
			}

		}

	}

}
