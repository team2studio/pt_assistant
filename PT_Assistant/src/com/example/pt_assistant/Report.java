package com.example.pt_assistant;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pt_assistant.GetPatientActivity.doPatient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Report {
	
	private static final String GET_INDEX_URL = "http://199.255.250.71/get_index_scores.php";

	public void get_index_scores() { // For remote DB
		new doReportAsyncTask().execute();
	}
     // NOTE when ever going to network you must run it in a task
	private class doReportAsyncTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... args) {
			String patient_name, bodypart, evaldate, injury_type, therapist_name;
			
				

				String TAG_SUCCESS = "success";
				JSONParser jsonParser = new JSONParser();
				String patient_id = "1007";
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("patient_id", patient_id));
				// params.add(new BasicNameValuePair("patient_name",
				// patient_name));
				try {
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(GET_INDEX_URL,
						"GET", params);
				// json success tag
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					patient_name = json.getString("name");
					bodypart = json.getString("bodypart");
					evaldate = json.getString("evaldate");
					injury_type = json.getString("injury_type");
					therapist_name = json.getString("therapist_name");

				}

			} catch (JSONException  e) {
				Thread.interrupted();
			}

			return "Executed";
		}

		@Override
		protected void onPostExecute(String result) {
			
			
			

		}

		@Override
		protected void onPreExecute() {
			
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}

}
