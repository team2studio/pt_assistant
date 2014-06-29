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
	String patid;
	// localhost :
	// testing on your device
	// put your local ip instead, on windows, run CMD > ipconfig
	// or in mac's terminal type ifconfig and look for the ip under en0 or en1
	// private static final String CREATE_PATIENT_URL =
	// "http://xxx.xxx.x.x:1234/webservice/login.php";

	// testing on Emulator:
	private static final String GET_PATIENT_URL = "http://192.168.1.8/webservice/get_patient.php";

	// testing from a real server:
	// private static final String CREATE_PATIENT_URL =
	// "http://www.yourdomain.com/webservice/login.php";
    public void fill_patient_fields(Patient pt)
    {
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
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String Sex;
		setContentView(R.layout.fragment_get_patient);
		Patient pt = new Patient();
		// Get the message from the intent
		Intent intent = getIntent();
		patid = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

		//local DB
		//pt = pt_db.getPatient(Integer.parseInt(message));
		//fill_patient_fields(pt);
		//remote DB
		new GetPatient().execute();
		
		



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
	// JSON element ids from repsonse of php script:sergio
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	class GetPatient extends AsyncTask<String, String, String> {
		Patient pt;
		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GetPatientActivity.this);
			pDialog.setMessage("Attempting patient get...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			
			//TEMP TEST CODE
			//String patient_name = "bree";//existPat.getName();
			String patient_id = patid;

			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("patient_id", patient_id));
				//params.add(new BasicNameValuePair("patient_name", patient_name));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(
						GET_PATIENT_URL, "GET", params);

				// check your log for json response
				Log.d("getting patient attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				
				
				
				if (success == 1) {
					pt = new Patient();
					Log.d("patient got Successful!", json.toString());
					JSONArray patientlist = new JSONArray() ;
					patientlist = json.getJSONArray("thispatient");
					for (int i= 0; i< patientlist.length();i++)
					{   
						JSONObject c = patientlist.getJSONObject(i);
						pt.setPatientID( Integer.parseInt(c.getString("p_id")));
						pt.setName(c.getString("name"));
						pt.setDOB(c.getString("dob"));
						pt.setAge(Integer.parseInt(c.getString("age")));
						pt.setSex(Integer.parseInt(c.getString("sex")));
						pt.setInjury(Integer.parseInt(c.getString("injury_code")));
					}
					
					Log.d("done", "finished getting");
					//finish();
					// startActivity(i);
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("get patient Failure!",
							json.getString(TAG_MESSAGE));
					failure = true;
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
			if (failure == false){
				fill_patient_fields(pt);
			}
			pDialog.dismiss();
			if (file_url != null) {
				Toast.makeText(GetPatientActivity.this, file_url,
						Toast.LENGTH_LONG).show();
			}

		}

	}

}
