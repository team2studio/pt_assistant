package com.example.pt_assistant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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
	private static final String JSON_PAT_NAME =        "name";
	private static final String JSON_ROM =             "range_of_motion";
	private static final String JSON_EVAL_DATE =       "evaldate";
	private static final String JSON_INJURY_NAME =     "injury_name";
	private static final String JSON_THERAPIST_NAME =  "therapist_name";
	private static final String JSON_STRENGTH =        "strength";
	private static final String JSON_PAIN =            "pain";
	String KEY_PATNAME =   "1";
	String KEY_ROM =       "2";
	String KEY_EVAL_DATE = "3";
	String KEY_INURY_NAME = "4";
	String KEY_THERAPIST = "5";
	String KEY_STRENT_LVL = "6";
	String KEY_PAIN_LVL = "7";
	
	
	private static final String GET_INDEX_URL =   "http://199.255.250.71/get_patient_trend_data.php";
	List<Map> patientTrendList;
	Map<String, String> patTrendmap;

	public void get_patient_trend_data() { // For remote DB
		new doReportTrendAsyncTask().execute();
		
		//for (int i = 0; .)
		//Iterator it = patientTrendList.get(i)
	}

	// NOTE when ever going to network you must run it in a task
	public class doReportTrendAsyncTask extends AsyncTask<String, Void, String> {
		
		JSONParser jsonParser  = new JSONParser();
		String TAG_SUCCESS = "success";
		
		
		
		@Override
		protected String doInBackground(String... args) {
		
			report_async_get_trend_data();
			return "Executed";
		}

		@Override
		protected void onPostExecute(String result) {
			
			Iterator<Map> it = patientTrendList.iterator();
			
			Map map = new HashMap();
			
			while(it.hasNext()){
				
				map = it.next();
				
				Log.d("patient name: ", (String )map.get(KEY_PATNAME));
				Log.d("eval date: ", (String )map.get(KEY_EVAL_DATE));
				Log.d("rom: ", (String )map.get(KEY_ROM));
				Log.d("therapist name: ", (String )map.get(KEY_THERAPIST));
				Log.d("strength: ", (String )map.get(KEY_STRENT_LVL));
				Log.d("pain level: ", (String )map.get(KEY_PAIN_LVL));
				Log.d("injury name: ", (String )map.get(KEY_INURY_NAME));
				Log.d("record ", "next record");
				
				
				
				
			}

		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
		
		private void report_async_get_trend_data()
		{
			
			
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("patient_id", "1007"));
			params.add(new BasicNameValuePair("injuryName", "LOWER BACK INJURY"));
			try {
				String temp1,temp2,temp3,temp4,temp5,temp6,temp7,temp8;
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(GET_INDEX_URL,
						"GET", params);
				// json success tag
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					patientTrendList =  new ArrayList<Map>();
					JSONArray jsonPatTrendList = new JSONArray();
					jsonPatTrendList = json.getJSONArray("trend_data");
					
					for (int i= 0;i<jsonPatTrendList.length();i++){
						
						JSONObject c = jsonPatTrendList.getJSONObject(i);
						patTrendmap = new HashMap<String, String>();
						patTrendmap.put(KEY_PATNAME, c.getString(JSON_PAT_NAME));
						patTrendmap.put(KEY_ROM, c.getString(JSON_ROM));
						patTrendmap.put(KEY_EVAL_DATE, c.getString(JSON_EVAL_DATE));
						patTrendmap.put(KEY_INURY_NAME, c.getString(JSON_INJURY_NAME));
						patTrendmap.put(KEY_THERAPIST, c.getString(JSON_THERAPIST_NAME));
						patTrendmap.put(KEY_STRENT_LVL, c.getString(JSON_STRENGTH));
						patTrendmap.put(KEY_PAIN_LVL, c.getString(JSON_PAIN));
						patientTrendList.add(patTrendmap);
						
						
						
//						 temp1 =   c.getString(PAT_NAME);
//						 temp2 =  c.getString(ROM);
//						 temp3 = c.getString(EVAL_DATE);
//						 temp4 = c.getString(INJURY_NAME);
//						 temp5 = c.getString(THERAPIST_NAME);
//						 temp6 = c.getString(STRENGTH);
//						 temp7 = c.getString(PAIN);
					  
					
					}
					

				}

			} catch (JSONException e) {
				Thread.interrupted();
			}
			
		}
		
		
		
	}

}
