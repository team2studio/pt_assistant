package com.example.pt_assistant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class InjuryLookupActivity extends ActionBarActivity{
	SimpleAdapter simpleAdpt;
	Patient_Notes pn;
	Patient p;
	private static final String INJURY_NAME = "injury_Name";
	private static final String LOAD_INJURIES_URL = "http://199.255.250.71/load_injuries.php";
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_injury_lookup);
		
		//issue a web service call to retrieve all injuries
		new loadInjuries().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.injury_lookup, menu);
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
	
	void displayInjuryList(List<Map> injuryList) {
		
		// We get the ListView component from the layout
		ListView lv = (ListView) findViewById(R.id.injuryList);

		simpleAdpt = new SimpleAdapter(this,
				(List<? extends Map<String, ?>>) injuryList,
				android.R.layout.simple_list_item_1,
				new String[] { INJURY_NAME }, new int[] { android.R.id.text1 });
		
//not sure what goes here yet so I left text1 in, still figuring some of this out
		lv.setAdapter(simpleAdpt);
		// React to user clicks on item
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parentAdapter, View view,
					int position, long id) {

				// We know the View is a TextView so we can cast it
				TextView clickedView = (TextView) view;

				Toast.makeText(
						InjuryLookupActivity.this,
						"Item with id [" + id + "] - Position [" + position
								+ "] - Injury [" + clickedView.getText() + "]",
						Toast.LENGTH_SHORT).show();

			}
		});
		registerForContextMenu(lv);

	}
	
	public void backObjective(View view) {
		//call this when your activity is done and needs to close
		//this will simulate the back button for us
		finish();
	}
	
	// This task
	class loadInjuries extends AsyncTask<String, String, String> {
		boolean failure = false;
		List<Map> injuryList;

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			pDialog = new ProgressDialog(InjuryLookupActivity.this);
			pDialog.setMessage("Loading injuries from database...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			String result;
			// TODO Auto-generated method stub
			// Check for success tag

			result = retrieve_all_injuries_json();

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
				
				// display injuries in the GUI
				displayInjuryList(injuryList);
				Context context = getApplicationContext();
				CharSequence text = "Injuries loaded successfully!";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
			if (file_url != null) {
				Toast.makeText(InjuryLookupActivity.this, file_url,
						Toast.LENGTH_LONG).show();
			}

		}

		private String retrieve_all_injuries_json() {

			// JSON element ids from response of php script
			String TAG_INJURIES = "injuries";
			String TAG_INJURY_NAME = "injuryName";
			String TAG_INJURY_DESCRIPTION = "injuryDescription";		
			String TAG_SUCCESS = "success";
			String TAG_MESSAGE = "message";
			String name = null;
			String description = null;
			String injuryRecord = null;
			int success;
			
			try {

				//load injuries from the database
				JSONObject json = jsonParser.getJSONFromUrl(LOAD_INJURIES_URL);

				// check your log for json response
				Log.d("Load injuries attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					injuryList = new ArrayList<Map>();
					JSONArray jsonInjuryList = new JSONArray();
					jsonInjuryList = json.getJSONArray(TAG_INJURIES);
					for (int j = 0; j < jsonInjuryList.length(); j++) {
						Map map = new HashMap();
						JSONObject i = jsonInjuryList.getJSONObject(j);
						name = i.getString(TAG_INJURY_NAME);
						description = i.getString(TAG_INJURY_DESCRIPTION);
						injuryRecord = name + " - " + description;
						map.put(INJURY_NAME, injuryRecord);	//add key value pair
						injuryList.add(map);	//add map of injuries to arraylist
					}

					Log.d("Successful: ", json.getString(TAG_MESSAGE));
						return json.getString(TAG_MESSAGE);
				} else {
						Log.d("Failure: ", json.getString(TAG_MESSAGE));
						failure = true;
						return json.getString(TAG_MESSAGE);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			return null;

		}
	}
	
}
