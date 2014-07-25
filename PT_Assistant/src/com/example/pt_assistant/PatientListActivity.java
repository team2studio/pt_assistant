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

import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PatientListActivity extends ActionBarActivity {
	SimpleAdapter simpleAdpt;
	PT_SQLiteHelper pt_db = new PT_SQLiteHelper(this);
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	public final static String EXTRA_MESSAGE = "com.example.pt_assistant.MESSAGE";
	private static final String LOAD_PATIENTS_URL = "http://199.255.250.71/load_patients.php";
	private static final String PID_NAME = "pid_name";
	// TODO maybe just locl to method
	private String pidname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_list_view);

		// get the patient list from the web server
		new loadPatients().execute();
	}

	// This function gets the patient list from a local DB
	List<Map> GetPatientList() {

		String pid_name;
		List<Patient> patientList = pt_db.getAllPatients();
		List<Map> patlist = new ArrayList<Map>();
		// List<Map> patlist = new ArrayList<Map>();
		Patient thispat;
		Iterator<Patient> iterator = patientList.iterator();
		while (iterator.hasNext()) {
			Map map = new HashMap();
			thispat = iterator.next();

			thispat.getName();
			pid_name = Integer.toString(thispat.getPatientID()) + "  "
					+ thispat.getName();

			map.put("pid_name", pid_name);

			patlist.add(map);

		}

		return patlist;
		// ******************************************************************

	}

	void FillPatientList(List<Map> patientList) {
		// We get the ListView component from the layout
		ListView lv = (ListView) findViewById(R.id.patientlist);

		// This is a simple adapter that accepts as parameter
		// Context
		// Data list
		// The row layout that is used during the row creation
		// The keys used to retrieve the data
		// The View id used to show the data. The key number and the view id
		// must match

		// TODO should make a two column list view this is one columns its a
		// hack for demo. Sergio
		simpleAdpt = new SimpleAdapter(this,
				(List<? extends Map<String, ?>>) patientList,
				android.R.layout.simple_list_item_1, new String[] { PID_NAME },
				new int[] { android.R.id.text1 });

		lv.setAdapter(simpleAdpt);
		// React to user clicks on item
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parentAdapter, View view,
					int position, long id) {

				// We know the View is a TextView so we can cast it
				TextView clickedView = (TextView) view;

				Toast.makeText(
						PatientListActivity.this,
						"Item with id [" + id + "] - Position [" + position
								+ "] - Patient [" + clickedView.getText() + "]",
						Toast.LENGTH_SHORT).show();

			}
		});
		registerForContextMenu(lv);

	}

	// We want to create a context Menu when the user long click on an item
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;

		// We know that each row in the adapter is a Map
		HashMap map = (HashMap) simpleAdpt.getItem(aInfo.position);
		pidname = (String) map.get(PID_NAME);

		int pos = pidname.indexOf(" ");
		String name = pidname.substring(pos, pidname.length());
		menu.setHeaderTitle("Options for " + name);
		menu.add(1, 1, 1, "Details");
		menu.add(1, 2, 2, "Delete");

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		// /this is a hack for the demo
		if (itemId == 1) {
			// Do something in response to button
			Intent intent = new Intent(this, GetPatientActivity.class);

			int pos = pidname.indexOf(" ");
			String pid = pidname.substring(0, pos);
			intent.putExtra(EXTRA_MESSAGE, pid);
			startActivity(intent);
		}
		// Implements our logic
		// Toast.makeText(this, "Item id [" + itemId + "]", Toast.LENGTH_SHORT)
		// .show();
		return true;
	}

	// This task
	class loadPatients extends AsyncTask<String, String, String> {
		boolean failure = false;
		List<Map> patientList;

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			pDialog = new ProgressDialog(PatientListActivity.this);
			pDialog.setMessage("Loading patients...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			String result;
			// TODO Auto-generated method stub
			// Check for success tag

			result = load_patients_json();

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
				FillPatientList(patientList);
				Context context = getApplicationContext();
				CharSequence text = "Patients loaded!";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
			if (file_url != null) {
				Toast.makeText(PatientListActivity.this, file_url,
						Toast.LENGTH_LONG).show();
			}

		}

		private String load_patients_json() {

			// JSON element ids from repsonse of php script:sergio
			String TAG_SUCCESS = "success";
			String TAG_MESSAGE = "message";
			String TAG_PATIENTS = "patients";
			String TAG_PID = "pid";
			String TAG_NAME = "pname";
			String p_id, name, pidName;
			int success;
			try {

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.getJSONFromUrl(LOAD_PATIENTS_URL);

				// check your log for json response
				Log.d("patient get attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					patientList = new ArrayList<Map>();
					Log.d("Patients loaded!", json.toString());
					JSONArray jsonPatList = new JSONArray();
					jsonPatList = json.getJSONArray(TAG_PATIENTS);
					for (int i = 0; i < jsonPatList.length(); i++) {
						Map map = new HashMap();
						JSONObject c = jsonPatList.getJSONObject(i);
						p_id = c.getString(TAG_PID);
						name = c.getString(TAG_NAME);
						pidName = p_id + "  " + name;
						map.put(PID_NAME, pidName);
						patientList.add(map);
					}

					Log.d("done", "finished getting");

					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("Patients did not load!", json.getString(TAG_MESSAGE));
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
