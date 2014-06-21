package com.example.pt_assistant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
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

	public final static String EXTRA_MESSAGE = "com.example.pt_assistant.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_list_view);

		List<Map> patientList = GePatientList();
		// We get the ListView component from the layout
		ListView lv = (ListView) findViewById(R.id.patientlist);

		// This is a simple adapter that accepts as parameter
		// Context
		// Data list
		// The row layout that is used during the row creation
		// The keys used to retrieve the data
		// The View id used to show the data. The key number and the view id
		// must match
		
// TODO should make a two column list view this is one columns its a hack for demo. Sergio
		simpleAdpt = new SimpleAdapter(this,
				(List<? extends Map<String, ?>>) patientList,
				android.R.layout.simple_list_item_1,
				new String[] { "pid_name" }, new int[] { android.R.id.text1 });

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
	
	List<Map> GePatientList() {
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
	}

	private String pidname;

	// We want to create a context Menu when the user long click on an item
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;

		// We know that each row in the adapter is a Map
		HashMap map = (HashMap) simpleAdpt.getItem(aInfo.position);
		pidname = (String) map.get("pid_name");

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

}
