package com.example.pt_assistant;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

public class CreatePatientActivity extends ActionBarActivity     {
	/**
	 * 
	 */
	private String paname;
	private int pid, iid;
	 // Progress Dialog
    private ProgressDialog pDialog;
	//For remote DB 	
	JSONParser jsonParser = new JSONParser();
    //For local DB
	PT_SQLiteHelper pt_db =   new PT_SQLiteHelper(this);
	
	
	//php login script location for remote DB

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
    // private static final String CREATE_PATIENT_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";

    //testing on Emulator:
    private static final String CREATE_PATIENT_URL = "http://192.168.1.8/webservice/create_patient2.php";
    
    //testing from a real server:
    //private static final String CREATE_PATIENT_URL = "http://www.yourdomain.com/webservice/login.php";

    //JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 
		 super.onCreate(savedInstanceState);
		    Patient pat = new Patient();
		    // Get he message from the intent
		    Intent intent = getIntent();
		    pat = (Patient) intent.getSerializableExtra("Patient");
		   
		    //this is the local DB
		    pt_db.addPatient(pat);
		    TextView textView = new TextView(this);
		    textView.setTextSize(20);
		    textView.setText(pat.getName());
		    textView.setText( "Name: " + pat.getName() + "\r\n"+
		    		          "PID :" + pat.getPatientID() + "\r\n" +
		    		          "Injury ID: " + pat.getInjury());
		    
		    setContentView(textView);
		    //this is the remote DB
		    paname = pat.getName();
		    pid = pat.getPatientID();
		    iid = pat.getInjury();
		    new CreatePatient().execute();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.display_message, menu);
//		return true;
//	}

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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_display_message,
					container, false);
			return rootView;
		}
	}
	
	
	class CreatePatient extends AsyncTask<String, String, String> {

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
           String patientname = paname;
           String patientid = String.valueOf(pid);
		   String injuryid  = String.valueOf(iid);
           try {
               // Building Parameters
               List<NameValuePair> params = new ArrayList<NameValuePair>();
               params.add(new BasicNameValuePair("patientname", patientname));
               params.add(new BasicNameValuePair("patientid", patientid));
				params.add(new BasicNameValuePair("injuryid", injuryid));

               Log.d("request!", "starting");
               // getting product details by making HTTP request
               JSONObject json = jsonParser.makeHttpRequest(
                      CREATE_PATIENT_URL, "POST", params);

               // check your log for json response
               Log.d("Create patient attempt", json.toString());

               // json success tag
               success = json.getInt(TAG_SUCCESS);
               if (success == 1) {
               	Log.d("patient created Successful!", json.toString());
               	//Intent i = new Intent(Login.this, ReadComments.class);
               	finish();
   				//startActivity(i);
               	return json.getString(TAG_MESSAGE);
               }else{
               	Log.d("create patient Failure!", json.getString(TAG_MESSAGE));
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
           pDialog.dismiss();
           if (file_url != null){
           	Toast.makeText(CreatePatientActivity.this, file_url, Toast.LENGTH_LONG).show();
           }

       }

	}

}


