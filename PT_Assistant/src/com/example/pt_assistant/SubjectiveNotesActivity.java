package com.example.pt_assistant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pt_assistant.PlanNotesActivity.EnterSessionNotes;

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
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Build;

public class SubjectiveNotesActivity extends ActionBarActivity {
	Patient_Notes pn;
	
	Patient p;
	JSONParser jsonParser = new JSONParser();
    boolean get_notes;
	// Progress Dialog
	private ProgressDialog pDialog;
	private static final String GET_PATIENT_NOTES_URL = "http://199.255.250.71/get_patient_notes.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	Map<String, String> patNotesdmap;
	
	
	//private static final String JSONPATID =           "patid";
	private static final String JSON_PAST_DIAG =      "pastdiag";
	private static final String JSON_OTHER =          "patother";
	private static final String JSON_MED =            "patmed";
	private static final String JSON_GOALS =          "patggoals";
	private static final String JSON_REASONS =        "patreasons";
	private static final String JSON_ROM =            "patrom";
	private static final String JSON_STRENGTH =       "patstrength";
	private static final String JSON_JOINT =          "patjoint";
	private static final String JSON_PAIN =           "patpain";
	private static final String JSON_PALP =           "patpalp";
	private static final String JSON_SPEC_TEST =      "patspectst";
	private static final String JSON_INJURY_NAME =    "patinjname";
	private static final String JSON_DIAG =           "patdiag";
	private static final String JSON_ADD_NOTES =      "pataddnotes";
	private static final String JSON_ENTRY_DATE =     "patentrydate";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subjective_notes);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Intent intent = getIntent();
		String getnotes = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		get_notes = false;
		if (savedInstanceState == null){
			if (getnotes!= null && getnotes.equals("getnotes")){
			   get_notes = true;
			   p = (Patient) getIntent().getSerializableExtra("PatientObject");
			   new GetSessionNotes().execute();
			}
		}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.subjective_notes, menu);
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

	public void setSubjective(View view) {

		// receive serialized patient and notes objects from previous activity
		p = (Patient) getIntent().getSerializableExtra("PatientObject");
		if (get_notes == false)
		{
		    pn = (Patient_Notes) getIntent().getSerializableExtra(
				"PatientNotesObject");
		}

		EditText eText;
		// notes = new Patient_Notes();
		eText = (EditText) findViewById(R.id.editPastDiag);
		pn.setPastDiagnosis(eText.getText().toString());

		eText = (EditText) findViewById(R.id.editMedications);
		pn.setMedications(eText.getText().toString());

		eText = (EditText) findViewById(R.id.editOther);
		pn.setOther_PatientHistory(eText.getText().toString());

		eText = (EditText) findViewById(R.id.editGoals);
		pn.setGoals(eText.getText().toString());

		eText = (EditText) findViewById(R.id.editReason);
		pn.setReasons(eText.getText().toString());

		// Serialize, start next activity and send intent
		Intent intent = new Intent(this, ObjectiveNotesActivity.class);
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
					R.layout.fragment_subjective_notes, container, false);
			return rootView;
		}
	}

	class GetSessionNotes extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SubjectiveNotesActivity.this);
			pDialog.setMessage("Attempting to add patient notes...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			String temp = Integer.toString(p.getPatientID());
			params.add(new BasicNameValuePair("patient_id",temp  ));
			
			try {
				
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(GET_PATIENT_NOTES_URL,
						"GET", params);
				// json success tag
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					pn = new Patient_Notes();
					JSONArray jsonPatNotesList = new JSONArray();
					jsonPatNotesList = json.getJSONArray("pat_notes");
					
					for (int i = 0; i < jsonPatNotesList.length(); i++) {

						JSONObject c = jsonPatNotesList.getJSONObject(i);
						//patTrendmap = new HashMap<String, String>();
						pn.setPastDiagnosis(c.getString(JSON_PAST_DIAG));
						pn.setOther_PatientHistory(c.getString(JSON_OTHER));
						pn.setMedications(c.getString(JSON_MED));
						pn.setGoals(c.getString(JSON_GOALS));
						pn.setReasons(c.getString(JSON_REASONS));
						pn.setRangeOfMotion(Integer.parseInt(c.getString(JSON_ROM)));
						pn.setStrength(Integer.parseInt(c.getString(JSON_STRENGTH)));
						pn.setJointMobilization(Integer.parseInt(c.getString(JSON_JOINT)));
						pn.setPain(Integer.parseInt(c.getString(JSON_PAIN)));
						pn.setPalpation(Integer.parseInt(c.getString(JSON_PALP)));
						pn.setSpecialTest(Integer.parseInt(c.getString(JSON_SPEC_TEST)));
						pn.setInjury(c.getString(JSON_INJURY_NAME));
						pn.setPatient_diagnosis(c.getString(JSON_DIAG));
						pn.setAdditionalPlanNotes(c.getString(JSON_ADD_NOTES));
						
						break;

					}

				}

			} catch (JSONException e) {
				Thread.interrupted();
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
					CharSequence text = "Successfully got  Patient Notes!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
					
					EditText eText;
					// notes = new Patient_Notes();
					eText = (EditText) findViewById(R.id.editPastDiag);
					eText.setText(pn.getPastDiagnosis());

					eText = (EditText) findViewById(R.id.editMedications);
					eText.setText (pn.getMedications());

					eText = (EditText) findViewById(R.id.editOther);
					eText.setText(pn.getOther_PatientHistory());
//
					eText = (EditText) findViewById(R.id.editGoals);
					eText.setText(pn.getGoals());
//
					eText = (EditText) findViewById(R.id.editReason);
					eText.setText (pn.getReasons());
					
				}
			}
			if (file_url != null) {
				Toast.makeText(SubjectiveNotesActivity.this, file_url,
						Toast.LENGTH_LONG).show();
			}

		}
	}

}
